/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import db.DatabaseConnection;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author PC
 */
public class KorisnikModel {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Date getKreiranDatuma() {
        return kreiranDatuma;
    }

    public void setKreiranDatuma(Date kreiranDatuma) {
        this.kreiranDatuma = kreiranDatuma;
    }

    public Date getPoslednjiPutUlogovan() {
        return poslednjiPutUlogovan;
    }

    public void setPoslednjiPutUlogovan(Date poslednjiPutUlogovan) {
        this.poslednjiPutUlogovan = poslednjiPutUlogovan;
    }
    public int ID;
    public String korisnickoIme;
    public String email;
    public String sifra;
    public Date kreiranDatuma;
    public Date poslednjiPutUlogovan;

    public KorisnikModel(int ID, String korisnickoIme, String email, String sifra, Date kreiranDatuma, Date poslednjiPutUlogovan) {
        this.ID = ID;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.sifra = sifra;
        this.kreiranDatuma = kreiranDatuma;
        this.poslednjiPutUlogovan = poslednjiPutUlogovan;
    }

    public KorisnikModel() {
        this.ID = 1;
        this.korisnickoIme = "Damir01";
        this.email = "damir2001@gmail.com";
        this.sifra = "";
        this.kreiranDatuma = new Date();
        this.poslednjiPutUlogovan = new Date();
    }

    public static String hashSifra(String sifra) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sifra.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return null;
        }
    }

    public static KorisnikModel Login(String korisnickoIme, String sifra) {
        String sql = "SELECT * FROM korisnik WHERE korisnickoime = ? AND sifra = ?";

        String hesiranaSifra = hashSifra(sifra);
        if (hesiranaSifra == null) {
            return null;
        }

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, korisnickoIme);
            pstmt.setString(2, hesiranaSifra);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Uspešna prijava!");

                    int id = rs.getInt("id");
                    String korisnickoImeDB = rs.getString("korisnickoime");
                    String email = rs.getString("email");
                    String sifraDB = rs.getString("sifra");
                    Date kreiranDatuma = new Date(rs.getDate("kreiranDatuma").getTime());
                    Date poslednjiPutUlogovan = new Date(rs.getDate("poslednjiPutUlogovan").getTime());

                    return new KorisnikModel(id, korisnickoImeDB, email, sifraDB, kreiranDatuma, poslednjiPutUlogovan);
                } else {
                    System.out.println("Neuspešna prijava.");
                    return null;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Boolean Registar(String korisnickoIme, String email, String sifra, Date kreiranDatuma, Date poslednjiPutUlogovan) {
        String sql = "INSERT INTO korisnik (korisnickoime, email, sifra, kreirandatuma, poslednjiputulogovan) VALUES (?, ?, ?, ?, ?)";

        String hesiranaSifra = hashSifra(sifra);
        if (hesiranaSifra == null) {
            return false;
        }

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, korisnickoIme);
            pstmt.setString(2, email);
            pstmt.setString(3, hesiranaSifra);
            pstmt.setDate(4, new java.sql.Date(kreiranDatuma.getTime()));
            pstmt.setDate(5, new java.sql.Date(poslednjiPutUlogovan.getTime()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Registracija uspešna!");
                return true;
            } else {
                System.out.println("Registracija neuspešna.");
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
