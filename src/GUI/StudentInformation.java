/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import bulletinenpdf.Bulletin;
import graphique.DiagrammeBatonResultatEleve;
import notesElevesProfesseurs.Eleve;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paul BENETEAU & Marc-Antoine Bock
 */
public class StudentInformation extends javax.swing.JFrame {

    private javax.swing.JTable evalsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;

    /**
     * Création d'un form Swing qui affiche des informations non modifiables sur l'élève
     *
     * @param e
     */
    StudentInformation(Eleve e) {
        initComponents();
        if (e == null)
            e = Globals.selectedStudent;

        if (e != null) {
            StudentOperation operations = new StudentOperation();
            operations.displayStudentsEvaluations(e, evalsTable);
        }
        jLabel1.setText(jLabel1.getText() + " " + e.getPrenom());//prénom
        jLabel2.setText(jLabel2.getText() + " " + e.getId());//id
        jLabel3.setText(jLabel3.getText() + " " + e.getNom());//nom
        jLabel4.setText(jLabel4.getText() + " " + e.getDateNaissance());//date de naissance
        jLabel6.setText(jLabel6.getText() + " " + e.getPromotion().getNom());//Promotion


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ChoixProfMatiere.initSwingUIManager();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        evalsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JButton jButton1 = new JButton();
        JLabel jLabel5 = new JLabel();
        jLabel6 = new javax.swing.JLabel();
        JButton jButton2 = new JButton();
        JLabel jLabel7 = new JLabel();
        JButton statsDiagEleve = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        evalsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{
                        "Note", "Matière", "Correcteur", "Type", "Statistiques"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                    true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        evalsTable.setRowHeight(50);
        jScrollPane1.setViewportView(evalsTable);
        if (evalsTable.getColumnModel().getColumnCount() > 0) {
            evalsTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("Prénom : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setText("Identifiant : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setText("Nom : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel4.setText("Date de naissance : ");

        jButton1.setText("Retour");
        jButton1.addActionListener(evt -> jButton1ActionPerformed());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel5.setText("Liste des évaluations");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel6.setText("Promotion : ");

        jButton2.setText("Télécharger le bulletin en PDF");
        jButton2.addActionListener(evt -> downloadBullBClick());

        jLabel7.setText("Diagramme de résultats");

        statsDiagEleve.setText("Afficher");
        statsDiagEleve.addActionListener(evt -> statsDiagEleveActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(jLabel1))
                                                        .addComponent(jLabel5))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(statsDiagEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(154, 154, 154)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(349, 349, 349)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6))
                                .addContainerGap(418, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel3)
                                        .addContainerGap(850, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jButton2)
                                        .addContainerGap(700, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel2)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(statsDiagEleve)
                                        .addComponent(jLabel7))
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(jLabel3)
                                        .addContainerGap(513, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(577, Short.MAX_VALUE)
                                        .addComponent(jButton2)
                                        .addGap(9, 9, 9)))
        );

        pack();
    }

    private void jButton1ActionPerformed() {
        PromotionManager gestionnairePromo = new PromotionManager();
        gestionnairePromo.setVisible(true);
        dispose();
    }

    private void downloadBullBClick() {

        Bulletin bul = new Bulletin(Globals.selectedStudent);
        try {
            bul.creerBulletin();
            JOptionPane.showMessageDialog(null, "Le Bulletin en PDF a été crée dans le dossier Ressources !");
        } catch (IOException ex) {
            Logger.getLogger(StudentInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void statsDiagEleveActionPerformed() {
        new DiagrammeBatonResultatEleve(Globals.selectedStudent);
    }
}
