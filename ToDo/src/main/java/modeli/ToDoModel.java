/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import db.DatabaseConnection;
import modeli.KorisnikModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ToDoModel {

    public int ID;
    public String naslov;
    public String Opis;
    public String Stanje;
    public int userID;
    public Date datumKreiranja;
    public Date rokDo;

    public ToDoModel(int ID, String naslov, String Opis, String Stanje, Date datumKreiranja, Date rokDo, int userID) {
        this.ID = ID;
        this.naslov = naslov;
        this.Opis = Opis;
        this.Stanje = Stanje;
        this.datumKreiranja = datumKreiranja;
        this.rokDo = rokDo;
        this.userID = userID;
    }

    public ToDoModel() {
        this.ID = 1;
        this.naslov = "Kupi namirnice";
        this.Opis = "Kupi hleb i jaja";
        this.Stanje = "Nije uradjeno";
        this.userID = 0;
        this.datumKreiranja = new Date();
        this.rokDo = new Date();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String Opis) {
        this.Opis = Opis;
    }

    public String getStanje() {
        return Stanje;
    }

    public void setStanje(String Stanje) {
        this.Stanje = Stanje;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Date getRokDo() {
        return rokDo;
    }

    public void setRokDo(Date rokDo) {
        this.rokDo = rokDo;
    }

    // Helper method to trim time from Date
    public static Date trimTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // Metoda za čuvanje u bazu i dodavanje u bazu
    public static void KreirajToDo(String naslov, String Opis, String Stanje, Date datumKreiranja, Date rokDo, int userID) {
        String sql = "INSERT INTO todo (naslov, opis, stanje, datumKreiranja, rokDO, userID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Trim time from dates
            Date trimmedDatumKreiranja = trimTime(datumKreiranja);
            Date trimmedRokDo = trimTime(rokDo);

            pstmt.setString(1, naslov);
            pstmt.setString(2, Opis);
            pstmt.setString(3, Stanje);
            pstmt.setDate(4, new java.sql.Date(trimmedDatumKreiranja.getTime()));
            pstmt.setDate(5, new java.sql.Date(trimmedRokDo.getTime()));
            pstmt.setInt(6, userID);
            pstmt.executeUpdate();

            System.out.println("ToDo uspešno dodata!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metoda za brisanje iz baze
    public static void ObrisiToDo(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("ToDo obrisana!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<ToDoModel> PrikaziToDos() {
        List<ToDoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM todo WHERE userID = ?";

        KorisnikModel korisnik = Sesija.getUlogovaniKorisnik();
        if (korisnik == null) {
            System.out.println("Nema ulogovanog korisnika.");
            return lista;
        }

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, korisnik.ID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Date datumKreiranja = trimTime(new Date(rs.getDate("datumKreiranja").getTime()));
                    Date rokDo = trimTime(new Date(rs.getDate("rokDo").getTime()));
                    
                    ToDoModel todo = new ToDoModel(
                            rs.getInt("id"),
                            rs.getString("naslov"),
                            rs.getString("opis"),
                            rs.getString("stanje"),
                            datumKreiranja,
                            rokDo,
                            rs.getInt("userID")
                    );
                    lista.add(todo);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    // Add this method after the existing CRUD methods
    public static void azurirajToDo(int id, String naslov, String Opis, String Stanje, Date rokDo) {
        String sql = "UPDATE todo SET naslov=?, opis=?, stanje=?, rokDo=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Trim time from date

            Date trimmedRokDo = trimTime(rokDo);

            pstmt.setString(1, naslov);
            pstmt.setString(2, Opis);
            pstmt.setString(3, Stanje);
            pstmt.setDate(4, new java.sql.Date(trimmedRokDo.getTime()));
            pstmt.setInt(5, id);
            pstmt.executeUpdate();

            System.out.println("ToDo uspešno ažurirana!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<ToDoModel> PronadjiToDo(String Pretraga) {
        String sql = "SELECT * FROM todo WHERE (Naslov LIKE ? OR Stanje LIKE ? OR Opis LIKE ?) AND userid = ?";

        List<ToDoModel> lista = new ArrayList<>();

        int userId = Sesija.getUlogovaniKorisnik().ID;
        System.out.println(userId);
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Pretraga);
            pstmt.setString(2, Pretraga);
            pstmt.setString(3, Pretraga);
            pstmt.setInt(4, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date datumKreiranja = trimTime(new Date(rs.getDate("datumKreiranja").getTime()));
                Date rokDo = trimTime(new Date(rs.getDate("rokDo").getTime()));
                
                ToDoModel todo = new ToDoModel(
                        rs.getInt("id"),
                        rs.getString("naslov"),
                        rs.getString("opis"),
                        rs.getString("stanje"),
                        datumKreiranja,
                        rokDo,
                        rs.getInt("userID")
                );
                lista.add(todo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(Pretraga);
        }
        return lista;
    }

    public static List<ToDoModel> SortirajToDoPoDatumu() {
        List<ToDoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM todo order by datumKreiranja";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date datumKreiranja = trimTime(new Date(rs.getDate("datumKreiranja").getTime()));
                Date rokDo = trimTime(new Date(rs.getDate("rokDo").getTime()));
                
                ToDoModel todo = new ToDoModel(
                        rs.getInt("id"),
                        rs.getString("naslov"),
                        rs.getString("opis"),
                        rs.getString("stanje"),
                        datumKreiranja,
                        rokDo,
                        rs.getInt("userID")
                );
                lista.add(todo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public static List<ToDoModel> SortirajToDoPoStanju() {
        List<ToDoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM todo order by stanje ";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date datumKreiranja = trimTime(new Date(rs.getDate("datumKreiranja").getTime()));
                Date rokDo = trimTime(new Date(rs.getDate("rokDo").getTime()));
                
                ToDoModel todo = new ToDoModel(
                        rs.getInt("id"),
                        rs.getString("naslov"),
                        rs.getString("opis"),
                        rs.getString("stanje"),
                        datumKreiranja,
                        rokDo,
                        rs.getInt("userID")
                );
                lista.add(todo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    // Metoda za štampanje detalja o ToDo stavci u novom dijalogu ?????
    public void stampajToDo() {
        // Kreiraj novi dijalog
        JDialog dialog = new JDialog(new JFrame(), "Detalji ToDo stavke", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        
        // Format datuma
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        // Kreiraj glavni panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Dodaj naslov
        JLabel lblNaslov = new JLabel("Naslov: " + this.naslov);
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblNaslov);
        mainPanel.add(new JLabel(" "));
        
        // Dodaj ID
        mainPanel.add(new JLabel("ID: " + this.ID));
        mainPanel.add(new JLabel(" "));
        
        // Dodaj stanje
        mainPanel.add(new JLabel("Stanje: " + this.Stanje));
        mainPanel.add(new JLabel(" "));
        
        // Dodaj datume
        mainPanel.add(new JLabel("Datum kreiranja: " + sdf.format(this.datumKreiranja)));
        mainPanel.add(new JLabel("Rok do: " + sdf.format(this.rokDo)));
        mainPanel.add(new JLabel(" "));
        
        // Dodaj korisnik ID
        mainPanel.add(new JLabel("Korisnik ID: " + this.userID));
        mainPanel.add(new JLabel(" "));
        
        // Dodaj opis u text area
        mainPanel.add(new JLabel("Opis:"));
        JTextArea txtOpis = new JTextArea(this.Opis);
        txtOpis.setEditable(false);
        txtOpis.setLineWrap(true);
        txtOpis.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtOpis);
        scrollPane.setPreferredSize(new Dimension(450, 150));
        mainPanel.add(scrollPane);
        
        // Dodaj panel u dijalog
        dialog.add(mainPanel, BorderLayout.CENTER);
        
        // Prikaži dijalog
        dialog.setVisible(true);
    }

    // Metoda za generisanje izveštaja za ToDo stavke po stanju
    public static void generisiReportZaStanje(String stanje) {
        try {
            // 1. Dohvati samo ToDo stavke sa zadatim stanjem za ulogovanog korisnika
            List<ToDoModel> todoList = new ArrayList<>();
            String sql = "SELECT * FROM todo WHERE stanje = ? AND userID = ?";
            
            KorisnikModel korisnik = Sesija.getUlogovaniKorisnik();
            if (korisnik == null) {
                System.out.println("Nema ulogovanog korisnika.");
                return;
            }
            
            try (Connection conn = DatabaseConnection.getConnection(); 
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, stanje);
                pstmt.setInt(2, korisnik.ID);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Date datumKreiranja = trimTime(new Date(rs.getDate("datumKreiranja").getTime()));
                        Date rokDo = trimTime(new Date(rs.getDate("rokDo").getTime()));
                        
                        ToDoModel todo = new ToDoModel(
                                rs.getInt("id"),
                                rs.getString("naslov"),
                                rs.getString("opis"),
                                rs.getString("stanje"),
                                datumKreiranja,
                                rokDo,
                                rs.getInt("userID")
                        );
                        todoList.add(todo);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return;
            }
            
            // 2. Kreiraj izvor podataka za JasperReports
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(todoList);
            
            // 3. Inicijalizuj parametre
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("stanje", stanje);
            parameters.put("naslovIzvestaja", "Izveštaj ToDo stavki - Stanje: " + stanje);
            
            // 4. Učitaj JasperDesign iz .jrxml fajla
            String reportPath = "src/main/resources/reports/TodoReport.jrxml";
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            
            // 5. Kompiliraj JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // 6. Popuni izveštaj podacima
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            
            // 7. Prikaži izveštaj
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greška pri generisanju izveštaja: " + e.getMessage());
        }
    }
}
