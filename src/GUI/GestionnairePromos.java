/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import graphique.CamembertAppreciationPromo;
import graphique.CamembertNombreEleveParPromo;
import graphique.DiagrammeBatonMoyennePromotion;
import graphique.DiagrammeBatonStatPromotion;
import notesElevesProfesseurs.CSV_Loader;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;

import javax.swing.table.DefaultTableModel;

/**
 * Le gestionnaire des promotions permet d'afficher des promotions, les trier et proposer des options pour des élèves (ajouter,supprimer,modifier)
 *
 * @author franc
 */
public class GestionnairePromos extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barreDeRecherche;
    private javax.swing.JButton creerEleve;
    private javax.swing.JButton diagAppreciationB;
    private javax.swing.JButton diagMoyB;
    private javax.swing.JButton diagNbEleve;
    private javax.swing.JButton diagStatsPromoB;
    private javax.swing.JTable elevesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    /*
    private void majEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerEleveActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_creerEleveActionPerformed
*/
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifEleveB;
    private javax.swing.JComboBox<String> promotionCombobox;
    private javax.swing.JButton retourB;
    private javax.swing.JButton supprEleveB;
    private javax.swing.JToggleButton toggleSensTriB;
    private javax.swing.JComboBox<String> triCombobox;
    /**
     * Constructeur qui active de nombreuses options d'analyse sur la fenêtre et aussi des événements
     */
    public GestionnairePromos() {
        initComponents();

        if (Globals.promoActuelle == null)
            Globals.promoActuelle = Promotion.getListePromos().get(0);

        if (Globals.promoActuelle != null) {
            GestionnairePromosOperations operations = new GestionnairePromosOperations();
            operations.afficherElevesPromo(Globals.promoActuelle, elevesTable);
            operations.genererComboboxPromotions(promotionCombobox, elevesTable);
            operations.activerLaBarreDeRecherche(barreDeRecherche, elevesTable, modifEleveB);
            operations.genererComboboxTri(triCombobox, elevesTable);
            operations.relierBoutonModifierTable(modifEleveB, elevesTable);
            operations.montrerInfosEleveSurDoubleClick(elevesTable);
        }
        int total = 0;
        for (Promotion p : Promotion.getListePromos())
            total += p.getEleves().size();

        setTitle("Liste des élèves (" + total + " au total ) ");
        elevesTable.setDefaultEditor(Object.class, null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(GestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GestionnairePromos().setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        elevesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        promotionCombobox = new javax.swing.JComboBox<>();
        barreDeRecherche = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        retourB = new javax.swing.JButton();
        creerEleve = new javax.swing.JButton();
        triCombobox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        toggleSensTriB = new javax.swing.JToggleButton();
        supprEleveB = new javax.swing.JButton();
        modifEleveB = new javax.swing.JButton();
        diagMoyB = new javax.swing.JButton();
        diagNbEleve = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        diagStatsPromoB = new javax.swing.JButton();
        diagAppreciationB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Liste des élèves");

        elevesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String[]{
                        "Identifiant", "Nom", "Prénom", "Promotion", "Nombre évaluations", "Nombre correcteurs"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Short.class, java.lang.Short.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        elevesTable.setToolTipText("");
        elevesTable.setRowHeight(30);
        jScrollPane1.setViewportView(elevesTable);

        jLabel1.setText("Mode de Tri");

        promotionCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        promotionCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promotionComboboxActionPerformed(evt);
            }
        });

        barreDeRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barreDeRechercheActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Rechercher un identifiant");

        retourB.setText("Retour");
        retourB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourBClick(evt);
            }
        });

        creerEleve.setText("Ajouter un nouvel élève");
        creerEleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerEleveActionPerformed(evt);
            }
        });

        triCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jLabel3.setText("Promotion Sélectionnée  ");

        toggleSensTriB.setText("Ordre : croissant ");
        toggleSensTriB.setToolTipText("");
        toggleSensTriB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleSensTriBActionPerformed(evt);
            }
        });

        supprEleveB.setText("Supprimer un élève");
        supprEleveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprEleveBActionPerformed(evt);
            }
        });

        modifEleveB.setText("Modifier un élève");
        modifEleveB.setEnabled(false);
        modifEleveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifEleveBClick(evt);
            }
        });

        diagMoyB.setText("Moyenne");
        diagMoyB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagMoyBActionPerformed(evt);
            }
        });

        diagNbEleve.setText("Nombre d'élèves");
        diagNbEleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagNbEleveActionPerformed(evt);
            }
        });

        jLabel4.setText("Toutes promotions");

        jLabel5.setText("Diagrammes pour cet promotion");

        diagStatsPromoB.setText("Statistiques");
        diagStatsPromoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagStatsPromoBActionPerformed(evt);
            }
        });

        diagAppreciationB.setText("Appréciations");
        diagAppreciationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagAppreciationBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(triCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(promotionCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(creerEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(toggleSensTriB)
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel2))
                                        .addComponent(supprEleveB, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(barreDeRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)
                                                .addComponent(retourB, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(modifEleveB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(diagAppreciationB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(diagNbEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(diagMoyB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(diagStatsPromoB, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(barreDeRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(retourB)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(promotionCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(triCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(toggleSensTriB))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(modifEleveB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(supprEleveB, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addComponent(creerEleve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(diagMoyB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(diagNbEleve, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(diagStatsPromoB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(diagAppreciationB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13))
        );

        getAccessibleContext().setAccessibleName("Fenêtre");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void promotionComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promotionComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_promotionComboboxActionPerformed

    private void barreDeRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barreDeRechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barreDeRechercheActionPerformed

    /**
     * Fermeture de cette fenêtre en cliquant sur le bouton retour
     *
     * @param evt
     */
    private void retourBClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourBClick

        if (!MenuPrincipal.menu.isVisible()) {
            MenuPrincipal.menu.setVisible(true);
        }

        // ferme cette fenêtre en libérant les ressources
        this.dispose();

    }//GEN-LAST:event_retourBClick

    /**
     * Gestion du code lorsque le bouton d'ajout d'un nouvel élève est cliqué
     *
     * @param evt
     */
    private void creerEleveActionPerformed(java.awt.event.ActionEvent evt) {
        GenerateurEleve generateurEleve = new GenerateurEleve();
        generateurEleve.promoTF.setText(Globals.promoActuelle.getNom());
        generateurEleve.setVisible(true);
        dispose();
    }

    /**
     * Détecte quand on enfonce ou non le bouton d'ordre de tri ( croissant ou décroissant)
     *
     * @param evt
     */
    private void toggleSensTriBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleSensTriBActionPerformed
        // isSelected() permet de voir si le bouton est enfoncé
        if (toggleSensTriB.isSelected()) {
            toggleSensTriB.setText("Ordre : décroissant");
            Globals.triCroissant = false;
        } else {
            toggleSensTriB.setText("Ordre : croissant");
            Globals.triCroissant = true;
        }
        GestionnairePromosOperations operations = new GestionnairePromosOperations();
        operations.trierAfficherPromotionActuelle(elevesTable);
    }//GEN-LAST:event_toggleSensTriBActionPerformed

    /**
     * Gestion du code lorsque l'on clique sur le bouton de suppression d'un élève
     *
     * @param evt
     */
    private void supprEleveBActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int id = (Integer) elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
        Eleve e = Globals.promoActuelle.rechercherEleve(id);
        Globals.promoActuelle.getEleves().remove(e);
        CSV_Loader.supprimerEleveDansFichier(e, CSV_Loader.ELEVES_PATH);
        ((DefaultTableModel) elevesTable.getModel()).removeRow(elevesTable.getSelectedRow());
        modifEleveB.setEnabled(false);
    }

    /**
     * Affiche des informations sur l'élève sans pouvoir les modifier
     *
     * @param evt
     */
    private void montrerEleveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprEleveBActionPerformed
        // TODO add your handling code here:
        int id = (Integer) elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
        Eleve e = Globals.promoActuelle.rechercherEleve(id);
        EleveInfos eleveWindow = new EleveInfos(e);
        eleveWindow.setVisible(true);
        dispose();
    }//GEN-LAST:event_supprEleveBActionPerformed

    /**
     * Gestion du code lorsque l'on clique sur le bouton de modification d'un élève
     *
     * @param evt
     */
    private void modifEleveBClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifEleveBClick
        // TODO add your handling code here:
        int id = (Integer) elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
        Eleve e = Globals.promoActuelle.rechercherEleve(id);
        if (e != null) {
            // On ouvre une fenêtre qui gère les modifications d'un élève
            ModifEleve modEleve = new ModifEleve(e);
            modEleve.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_modifEleveBClick

    private void diagNbEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagNbEleveActionPerformed
        new CamembertNombreEleveParPromo(Promotion.getListePromos());
    }//GEN-LAST:event_diagNbEleveActionPerformed

    private void diagAppreciationBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagAppreciationBActionPerformed
        new CamembertAppreciationPromo(Globals.promoActuelle);
    }//GEN-LAST:event_diagAppreciationBActionPerformed

    private void diagMoyBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagMoyBActionPerformed
        new DiagrammeBatonMoyennePromotion(Promotion.getListePromos());
    }//GEN-LAST:event_diagMoyBActionPerformed

    private void diagStatsPromoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagStatsPromoBActionPerformed
        new DiagrammeBatonStatPromotion(Promotion.getListePromos());
    }//GEN-LAST:event_diagStatsPromoBActionPerformed
    // End of variables declaration//GEN-END:variables
}
