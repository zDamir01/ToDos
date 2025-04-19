/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.todo.UI;

import modeli.KorisnikModel;
import modeli.Sesija;
import modeli.ToDoModel;
import modeli.ToDoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;

/**
 *
 * @author PC
 */
public class ToDoFrame extends javax.swing.JFrame {

    /**
     * Creates new form ToDoFrame
     */
    public ToDoFrame() {
        initComponents();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Naslov", "Opis", "Datum Kreiranja", "Rok Do", "Stanje"}, 0);
        jTable1.setModel(model);
        // popunavanje polja na osnovu selektovanog polja iz tabelete
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        txtNaslov.setText(jTable1.getValueAt(selectedRow, 1).toString());
                        txtOpis.setText(jTable1.getValueAt(selectedRow, 2).toString());
                    }
                }
            }
        });
        
        // Add header click listener for sorting
        jTable1.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int columnIndex = jTable1.columnAtPoint(evt.getPoint());
                String columnName = jTable1.getColumnName(columnIndex);
                
                if (columnName == "Datum Kreiranja") {
                    // Sort by date
                    ToDoModel tdm = new ToDoModel();
                    List<ToDoModel> zadaci = tdm.SortirajToDoPoDatumu();
                    updateTableWithData(zadaci);
                } else if (columnName == "Stanje") {
                    // Sort by state
                    ToDoModel tdm = new ToDoModel();
                    List<ToDoModel> zadaci = tdm.SortirajToDoPoStanju();
                    updateTableWithData(zadaci);
                }
            }
        });

        btnPrikaziActionPerformed(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNaslov = new javax.swing.JTextField();
        txtOpis = new javax.swing.JTextField();
        btnSacuvaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnAzuriraj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnPrikazi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPretraga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateRokDo = new com.toedter.calendar.JDateChooser();
        selectStanjeZaReport = new javax.swing.JComboBox<>();
        btnLogout = new javax.swing.JButton();
        selectStanje1 = new javax.swing.JComboBox<>();
        btnGenerisiReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Unesi Naslov ToDo-a");

        jLabel2.setText("Unesi Opis ToDo-a");

        jLabel3.setText("Unesi Stanje ToDo-a");

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnAzuriraj.setText("Azuriraj");
        btnAzuriraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAzurirajActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Naslov", "Opis", "Datum Kreiranja", "Rok Do", "Stanje"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnPrikazi.setText("Prikaz");
        btnPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrikaziActionPerformed(evt);
            }
        });

        jLabel4.setText("Tekst za pretragu :");

        jButton1.setText("Pretrazi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jLabel6.setText("Rok Do");

        dateRokDo.setDateFormatString("yyyy-MM-dd");
        dateRokDo.setMinSelectableDate(new java.util.Date(-62135769523000L));

        selectStanjeZaReport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nije zapoceto", "Zapoceto", "U toku", "Uradjeno" }));
        selectStanjeZaReport.setSelectedIndex(1);
        selectStanjeZaReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStanjeZaReportActionPerformed(evt);
            }
        });

        btnLogout.setText("Izloguj se");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        selectStanje1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nije zapoceto", "Zapoceto", "U toku", "Uradjeno" }));
        selectStanje1.setSelectedIndex(1);

        btnGenerisiReport.setText("Generisi Report");
        btnGenerisiReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerisiReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnSacuvaj)
                .addGap(33, 33, 33)
                .addComponent(btnObrisi)
                .addGap(43, 43, 43)
                .addComponent(btnAzuriraj)
                .addGap(35, 35, 35)
                .addComponent(btnPrikazi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateRokDo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNaslov, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtOpis)
                                    .addComponent(selectStanje1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(selectStanjeZaReport, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnGenerisiReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNaslov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(selectStanje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dateRokDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectStanjeZaReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerisiReport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSacuvaj)
                    .addComponent(btnObrisi)
                    .addComponent(btnAzuriraj)
                    .addComponent(btnPrikazi)
                    .addComponent(btnLogout))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {
        String naslov = txtNaslov.getText();
        String Opis = txtOpis.getText();
        String Stanje = selectStanje1.getSelectedItem().toString();

        // Get current date without time
        java.util.Date today = new java.util.Date();
        java.util.Date datumKreiranja = new java.sql.Date(today.getTime());

        java.util.Date utilDate = dateRokDo.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Molimo unesite datum!");
            return;
        }

        // Convert to date without time AHMED
        java.util.Date rokDo = new java.sql.Date(utilDate.getTime());

        if (naslov.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Greška pri unosu podataka! Ime ne sme biti prazno.");
            return;
        }
        if (Opis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Greška pri unosu podataka! Opis ne sme biti prazan.");
            return;
        }
        if (Stanje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Greška pri unosu podataka! Stanje ne sme biti prazno.");
            return;
        }

        Sesija sesija = new Sesija();
        KorisnikModel km = sesija.getUlogovaniKorisnik();
        int ID = km.ID;

        ToDoModel tdm = new ToDoModel();
        tdm.KreirajToDo(naslov, Opis, Stanje, datumKreiranja, rokDo, ID);

        btnPrikaziActionPerformed(null);
        txtNaslov.setText("");
        txtOpis.setText("");
        dateRokDo.setDate(null);
    }

    private void btnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziActionPerformed
        // TODO add your handling code here:
        ToDoModel tdm = new ToDoModel();
        List<ToDoModel> zadaci = tdm.PrikaziToDos();
        updateTableWithData(zadaci);
        txtNaslov.setText("");  //ovde
        txtOpis.setText("");
        dateRokDo.setDate(null);
    }//GEN-LAST:event_btnPrikaziActionPerformed

    private void btnAzurirajActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int ID = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
            String Naslov = txtNaslov.getText();
            String Opis = txtOpis.getText();
            String Stanje = String.valueOf(selectStanje1.getSelectedItem());
            System.out.println(Stanje);

            java.util.Date utilDate = dateRokDo.getDate();
            java.util.Date rokDo;

            if (utilDate == null) {
                rokDo = new java.util.Date(); // današnji datum ako nije izabran
            } else {
                rokDo = new java.sql.Date(utilDate.getTime()); // trimTime možeš ubaciti ako želiš
            }

            ToDoModel tdm = new ToDoModel();
            tdm.azurirajToDo(ID, Naslov, Opis, Stanje, rokDo);
        }

        txtNaslov.setText("");
        txtOpis.setText("");
        btnPrikaziActionPerformed(null);
        dateRokDo.setDate(null);

    }

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int ID = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
            ToDoModel novimodel = new ToDoModel();
            novimodel.ObrisiToDo(ID);
        }
        txtNaslov.setText("");  // OVDE SAM RADIO 1 ZAD
        txtOpis.setText("");
        btnPrikaziActionPerformed(null);
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String UnetiTekst = '%' + txtPretraga.getText().toString() + '%';
        ToDoModel tdm = new ToDoModel();
        List<ToDoModel> RezultatPretrage = tdm.PronadjiToDo(UnetiTekst);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (int i = 0; i < RezultatPretrage.size(); i++) {
            Object[] row = {
                RezultatPretrage.get(i).ID,
                RezultatPretrage.get(i).naslov,
                RezultatPretrage.get(i).Opis,
                sdf.format(RezultatPretrage.get(i).datumKreiranja).toString(),
                sdf.format(RezultatPretrage.get(i).rokDo).toString(),
                RezultatPretrage.get(i).Stanje,};
            model.addRow(row);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void selectStanjeZaReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStanjeZaReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectStanjeZaReportActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        int odluka = JOptionPane.showConfirmDialog(this, "Da li zelite da se izlogujete.", "Odjava", JOptionPane.YES_NO_OPTION);

        if (odluka == JOptionPane.YES_OPTION) {
            System.out.println("Korisnik je odabrao DA");
        // 1. Isprazni sesiju

            Sesija.setUlogovaniKorisnik(null);

            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            this.dispose();
        }
        if (odluka == JOptionPane.NO_OPTION) {
            System.out.println("Korisnik je odabrao NE");
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnGenerisiReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerisiReportActionPerformed
        // Dobijanje odabranog stanja iz ComboBox-a
        String odabranoStanje = selectStanjeZaReport.getSelectedItem().toString();

        // Poziv metode za generisanje izveštaja
        ToDoModel.generisiReportZaStanje(odabranoStanje);
    }//GEN-LAST:event_btnGenerisiReportActionPerformed
// KADA OBRISES I AZURIRAS DA SE OCISTE SVA POLJA I DA SE AJUTOMATSKI AZURIRA U TABELU .
    // dodati posebpo polje koje pretrazuje samo po stanju             sve novo   okk

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ToDoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToDoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToDoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToDoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToDoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAzuriraj;
    private javax.swing.JButton btnGenerisiReport;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPrikazi;
    private javax.swing.JButton btnSacuvaj;
    private com.toedter.calendar.JDateChooser dateRokDo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> selectStanje1;
    private javax.swing.JComboBox<String> selectStanjeZaReport;
    private javax.swing.JTextField txtNaslov;
    private javax.swing.JTextField txtOpis;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration//GEN-END:variables

    private void updateTableWithData(List<ToDoModel> zadaci) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for (int i = 0; i < zadaci.size(); i++) {
            Object[] row = {
                zadaci.get(i).ID,
                zadaci.get(i).naslov,
                zadaci.get(i).Opis,
                sdf.format(zadaci.get(i).datumKreiranja).toString(),
                sdf.format(zadaci.get(i).rokDo).toString(),
                zadaci.get(i).Stanje
            };
            model.addRow(row);
        }
    }
}
