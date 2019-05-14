package GUI;

import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Paul BENETEAU & Marc-Antoine Bock
 */
public class ChoixProfMatiere extends javax.swing.JFrame {

    /**
     * Fenêtre d'affichage de choix ou création d'une matière ou d'un prof
     */

    private TypeSelectionChoice typeActuel = null; // Permet de savoir si nous sommes par exemple dans une fenêtre de matieres ou de professeurs
    private String stringSelection = ""; //  Permet de savoir quelle ligne nous avons sélectionné, ou quelle prof, matiere... est en cours de création
    private javax.swing.JLabel choixAlternatifL;
    private javax.swing.JLabel choixLabel;
    private javax.swing.JButton confirmerB;
    private javax.swing.JTextField creationTF;
    private javax.swing.JTable table;


    /**
     * Fenêtre d'affichage de choix ou création d'une matière ou d'un prof, on initialise graphiquement avec initComponents()
     */
    ChoixProfMatiere() {
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            initSwingUIManager();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChoixProfMatiere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ChoixProfMatiere().setVisible(true));
    }

    static void initSwingUIManager() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    }

    /**
     * Méthode à appeler obligatoirement après avoir instantié la classe, permet d'afficher la fenêtre pour le bon type
     *
     * @param type Le type d'initialisation à effectuer, cela produira des chargements différents et des affichages de texte différents
     * @param gen  La fenêtre permettant de gérer les évaluations de l'élève, on la garde en argument afin de pouvoir y accéder pour la mettre à jour plus tard
     */
    void init(TypeSelectionChoice type, GenerateurEvaluations gen) {
        if (type != null) {
            typeActuel = type;
            majAffichage(type);
            enableTableClicDetection();
            activerVerifCreationProf();
            activerVerifCreationMat(confirmerB, creationTF);
            retournerValeurQdFermeture(gen);
        } else
            System.out.println("Erreur le type est null");
        setLocationRelativeTo(null);
    }

    private void verifEspaceTF(JTextField tf, JButton buttonConfirmer) {
        if (typeActuel == TypeSelectionChoice.Professeur) {
            if (tf.getText().contains(" ")) {
                buttonConfirmer.setText("Ajouter " + tf.getText());
                stringSelection = tf.getText();
                buttonConfirmer.setEnabled(true);
            } else {
                buttonConfirmer.setEnabled(false);
                buttonConfirmer.setText("Confirmer");
            }
        } else {
            activerBoutonSiTextePresent(tf, buttonConfirmer);
            checkSubjectExistenceTF(confirmerB);
        }


    }

    private void activerVerifCreationMat(JButton boutonConfimer, JTextField matiereTF) {
        activerBoutonSiTextePresent(matiereTF, boutonConfimer);
    }

    private void activerVerifCreationProf() {
        if (typeActuel != null) {

            creationTF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
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

                private void verifExistenceProfTF(JTextField creationTF, JButton confirmerB) {
                    if (creationTF.getText().contains(" ") && !creationTF.getText().endsWith(" ")) {
                        String[] nomPrenom = creationTF.getText().split(" ");
                        Professeur prof = Professeur.trouverProfesseur(nomPrenom[1], nomPrenom[0]);
                        if (prof != null) {
                            confirmerB.setText("Existe déjà");
                            confirmerB.setEnabled(false);
                        } else {
                            confirmerB.setEnabled(true);
                        }
                    }
                }
            });
        }
    }

    private void listSubjects(JTable table) {

        PromotionManagerOperations op = new PromotionManagerOperations();
        op.viderTable(table);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nom de la matière");
        for (Matiere m : Matiere.listeMatieres) {
            // On crée et initialise un tableau à un seul élément
            Object[] row = {m.getNom()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    private void listTeachers(JTable table) {

        PromotionManagerOperations op = new PromotionManagerOperations();
        op.viderTable(table);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nom");
        model.addColumn("Prénom");
        for (Professeur p : Professeur.getListeProfesseurs()) {
            // On crée et initialise un tableau à un seul élément
            Object[] row = {p.getNom(), p.getPrenom()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    private void enableTableClicDetection() {

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (typeActuel == TypeSelectionChoice.Matiere) {
                    stringSelection = (String) table.getValueAt(table.getSelectedRow(), 0);
                    choixLabel.setText("Matière sélectionnée : " + stringSelection);
                } else {
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
    private void initComponents() {

        JPanel jPanel1 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        table = new javax.swing.JTable();
        choixAlternatifL = new javax.swing.JLabel();
        choixLabel = new javax.swing.JLabel();
        JPanel panel = new JPanel();
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
                new Object[][]{
                        {null},
                        {null},
                        {null},
                        {null}
                },
                new String[]{
                        "Nom"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        confirmerB.addActionListener(evt -> confirmerBClick());

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
    }

    private void confirmerBClick() {
        // TODO add your handling code here:
        switch (typeActuel) {
            case Matiere:
                ((DefaultTableModel) table.getModel()).addRow(new Object[]{stringSelection});
                if (Matiere.trouverMatiere(stringSelection, Globals.currentPromotion.getNom()) == null) {
                    Matiere.listeMatieres.add(new Matiere(stringSelection, Globals.currentPromotion.getNom()));
                    listSubjects(table);
                }
                break;
            case Professeur:
                String[] prenomNom = null;
                try {
                    prenomNom = stringSelection.split(" ");
                    if (Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]) == null) {
                        Professeur.getListeProfesseurs().add(new Professeur(prenomNom[1], prenomNom[0]));
                        listTeachers(table);
                    }

                } catch (Exception e) {
                    System.out.println("Erreur ajout prof");
                }
                break;
        }

        confirmerB.setEnabled(false);
    }

    /**
     * Modifie deux labels de la fenêtre pour avoir un texte approprié selon le type passé en argument
     *
     * @param type Type d'élément à sélectionner qui détermine tout l'affichage de la fenêtre
     */
    public void majAffichage(TypeSelectionChoice type) {
        switch (type) {
            case Matiere:
                choixLabel.setText("Choisissez votre matière");
                choixAlternatifL.setText("Ou créez une nouvelle matière");
                listSubjects(table);
                break;
            case Professeur:
                choixLabel.setText("Choisissez votre professeur");
                choixAlternatifL.setText("Ou ajoutez un nouveau professeur");
                listTeachers(table);
                break;
        }
    }

    private void activerBoutonSiTextePresent(JTextField tf, JButton bouton) {

        System.out.println("verif texte present");
        if (!tf.getText().isEmpty()) {
            bouton.setText("Créer " + tf.getText());
            bouton.setEnabled(true);
            stringSelection = tf.getText();
        } else {
            bouton.setEnabled(false);
        }
    }


    private void retournerValeurQdFermeture(GenerateurEvaluations gen) {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                switch (typeActuel) {
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

    private void checkSubjectExistenceTF(JButton confirmerB) {

        Matiere m = Matiere.trouverMatiere(stringSelection, Globals.currentPromotion.getNom());
        if (m != null) {
            confirmerB.setText("Existe déjà");
            confirmerB.setEnabled(false);
        } else {
            confirmerB.setEnabled(true);
        }
    }

}
