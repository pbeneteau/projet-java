

package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;

/**
 *
 * @author franc
 */
public class ChoixProfMatiere extends javax.swing.JFrame {

    /**
     * Fenêtre d'affichage de choix ou création d'une matière ou d'un prof
     */
    
    ChoixSelectionType typeActuel = null; // Permet de savoir si nous sommes par exemple dans une fenêtre de matieres ou de professeurs
    String stringSelection  = ""; //  Permet de savoir quelle ligne nous avons sélectionné, ou quelle prof, matiere... est en cours de création

    /**
     * Fenêtre d'affichage de choix ou création d'une matière ou d'un prof, on initialise graphiquement avec initComponents()
     */
    public ChoixProfMatiere()
    {
        initComponents();
    }
    

    /**
     *  Méthode à appeler obligatoirement après avoir instantié la classe, permet d'afficher la fenêtre pour le bon type
     * @param type Le type d'initialisation à effectuer, cela produira des chargements différents et des affichages de texte différents
     * @param gen La fenêtre permettant de gérer les évaluations de l'élève, on la garde en argument afin de pouvoir y accéder pour la mettre à jour plus tard
     */
    public void init(ChoixSelectionType type, GenerateurEvaluations gen)
    {
        if(type!=null)
        {
        typeActuel = type;
        majAffichage(type);
        activerDetectionClicTable();
        activerVerifCreationProf();
        activerVerifCreationMat(confirmerB,creationTF );
        retournerValeurQdFermeture(gen);
        }
        else 
            System.out.println("Erreur le type est null");
        setLocationRelativeTo(null);
    }
    
    
    private void verifEspaceTF(JTextField tf, JButton buttonConfirmer)
    {
        if(typeActuel == ChoixSelectionType.Professeur)
        {
            if(tf.getText().contains(" "))
            {
                buttonConfirmer.setText("Ajouter " + tf.getText());
                stringSelection = tf.getText();
                buttonConfirmer.setEnabled(true);
            }
            else 
            {
               buttonConfirmer.setEnabled(false);
               buttonConfirmer.setText("Confirmer");
            }
        }
        else 
        {
                        activerBoutonSiTextePresent(tf, buttonConfirmer);
                        verifExistenceMatiereTF(creationTF, confirmerB);
        }
        
       
    }
    
    private void activerVerifCreationMat(JButton boutonConfimer, JTextField matiereTF)
    {
        activerBoutonSiTextePresent(matiereTF,boutonConfimer);
    }
    
    private void activerVerifCreationProf()
    {
        if(typeActuel != null)
        {
            
         creationTF.getDocument().addDocumentListener(new DocumentListener() {
           @Override
           public void insertUpdate(DocumentEvent e) 
           {
               verifEspaceTF(creationTF, confirmerB);
               verifExistenceProfTF(creationTF, confirmerB);
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
               verifEspaceTF(creationTF, confirmerB);
               verifExistenceProfTF(creationTF, confirmerB);
           }

           @Override
           public void changedUpdate(DocumentEvent e) {
               verifEspaceTF(creationTF, confirmerB);   
               verifExistenceProfTF(creationTF, confirmerB);
           }

             private void verifExistenceProfTF(JTextField creationTF, JButton confirmerB) 
             {
                 if(creationTF.getText().contains(" ") && !creationTF.getText().endsWith(" "))
                 {
                 String[] nomPrenom = creationTF.getText().split(" ");
                 Professeur prof = Professeur.trouverProfesseur(nomPrenom[1], nomPrenom[0]);
                 if(prof != null)
                 {
                     confirmerB.setText("Existe déjà");
                     confirmerB.setEnabled(false);
                 }
                 else
                 {
                     confirmerB.setEnabled(true);
                 }
                 }
             }
       });
                 }
    }

    
    
    private void listerMatiere(JTable table)
    {
       GestionnairePromosOperations op = new GestionnairePromosOperations();
       op.viderTable(table);
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Nom de la matière");
       for( Matiere m :  Matiere.listeMatieres)
       {
           // On crée et initialise un tableau à un seul élément
           Object[] row= {m.getNom()}; 
           model.addRow(row);
       }
       table.setModel(model);
      
    }
    
    private void listerProfs(JTable table)
    {
       GestionnairePromosOperations op = new GestionnairePromosOperations();
       op.viderTable(table);
       DefaultTableModel model = new DefaultTableModel();
       model.addColumn("Nom");
       model.addColumn("Prénom");
       for( Professeur p :  Professeur.getListeProfesseurs())
       {
           // On crée et initialise un tableau à un seul élément
           Object[] row= {p.getNom(),p.getPrenom()}; 
           model.addRow(row);
       }
       table.setModel(model);
    }
    
    private void activerDetectionClicTable()
    {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event)
                {
                    if(typeActuel == ChoixSelectionType.Matiere)
                    {
                       stringSelection= (String) table.getValueAt(table.getSelectedRow(), 0);
                       choixLabel.setText("Matière sélectionnée : " + stringSelection);
                    }
                    else
                    {
                       String nom = (String) table.getValueAt(table.getSelectedRow(), 0);
                       String prenom = (String) table.getValueAt(table.getSelectedRow(), 1);
                       stringSelection = prenom + " " + nom;
                       choixLabel.setText("Professeur sélectionné : " + stringSelection);
                    }
                    System.out.println(stringSelection);
                }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        choixAlternatifL = new javax.swing.JLabel();
        choixLabel = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        creationTF = new javax.swing.JTextField();
        confirmerB = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fenêtre de sélection");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(30);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
        }

        choixAlternatifL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        choixAlternatifL.setText("Ou créez en une nouvelle en indiquant son Nom");

        choixLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        choixLabel.setText("Choisissez votre matière");

        confirmerB.setText("Confirmer");
        confirmerB.setEnabled(false);
        confirmerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmerBClick(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(confirmerB)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creationTF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmerB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(choixAlternatifL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(choixLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(choixLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(choixAlternatifL)
                .addGap(25, 25, 25)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmerBClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmerBClick
        // TODO add your handling code here:
        switch( typeActuel)
        {
            case Matiere:
                ((DefaultTableModel)table.getModel()).addRow(new Object[]{stringSelection});
                if(Matiere.trouverMatiere(stringSelection,Globals.promoActuelle.getNom()) == null)
                {
                    Matiere.listeMatieres.add(new Matiere(stringSelection,Globals.promoActuelle.getNom()));
                    listerMatiere(table);
                }
            break;
            case Professeur:
                String[] prenomNom = null;
                try {
                     prenomNom = stringSelection.split(" ");
                     if(Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]) == null)
                     {
                        Professeur.getListeProfesseurs().add(new Professeur(prenomNom[1], prenomNom[0]));
                        listerProfs(table);
                     }
                     
                } catch (Exception e) {
                    System.out.println("Erreur ajout prof");
                }
            break;
        }
        
        confirmerB.setEnabled(false);
    }//GEN-LAST:event_confirmerBClick

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
            java.util.logging.Logger.getLogger(ChoixProfMatiere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChoixProfMatiere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChoixProfMatiere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChoixProfMatiere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChoixProfMatiere().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel choixAlternatifL;
    private javax.swing.JLabel choixLabel;
    private javax.swing.JButton confirmerB;
    private javax.swing.JTextField creationTF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    
    //

    /**
     * Modifie deux labels de la fenêtre pour avoir un texte approprié selon le type passé en argument
     * @param type Type d'élément à sélectionner qui détermine tout l'affichage de la fenêtre
     */
    public void majAffichage(ChoixSelectionType type) 
    {
        switch(type)
        {
            case Matiere:
                choixLabel.setText("Choisissez votre matière");
                choixAlternatifL.setText("Ou créez une nouvelle matière");
                listerMatiere(table);
                break;
            case Professeur:
                choixLabel.setText("Choisissez votre professeur");
                choixAlternatifL.setText("Ou ajoutez un nouveau professeur");
                listerProfs(table);
                break;
        }
    }

    /**
     * Affiche une fenêtre JFrame en fonction de l'enum ChoixSelectionType
     * @param type le type de fenêtre que l'on souhaite afficher, par exemple une fenêtre de la liste des matieres, ou une fenêtre de la liste des professeurs
     * @param gen la fenêtre permettant de gérer les évaluations de l'élève, on la garde en argument afin de pouvoir y accéder pour la mettre à jour plus tard
     */
    public void montrerFenetreSelection(ChoixSelectionType type, GenerateurEvaluations gen)
    {
           ChoixProfMatiere fenetreChoix =  new ChoixProfMatiere();
           fenetreChoix.setVisible(true);
           fenetreChoix.init(type, gen);
    }
    
    private void activerBoutonSiTextePresent(JTextField tf,  JButton bouton)
    {
                    System.out.println("verif texte present");
        if(!tf.getText().isEmpty())
        {
            bouton.setText("Créer " + tf.getText());
            bouton.setEnabled(true);
            stringSelection = tf.getText();
        }
        else 
        {
             bouton.setEnabled(false);
        }
    }
    
   
    private void retournerValeurQdFermeture(GenerateurEvaluations gen) 
    {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event)
            {
                switch(typeActuel)
                {
                    case Matiere:
                        gen.matTF.setText(stringSelection);
                        break;
                    case Professeur:
                        gen.correcteurTF.setText(stringSelection);
                        break;
                }
            }

        });
    }

    private void verifExistenceMatiereTF(JTextField creationTF, JButton confirmerB) {
                 Matiere m = Matiere.trouverMatiere(stringSelection,Globals.promoActuelle.getNom());
                 if(m != null)
                 {
                     confirmerB.setText("Existe déjà");
                     confirmerB.setEnabled(false);
                 }
                 else
                 {
                     confirmerB.setEnabled(true);
                 }
             }

}
