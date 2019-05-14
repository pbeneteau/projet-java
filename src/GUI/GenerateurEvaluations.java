package GUI;

import notesElevesProfesseurs.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Paul BENETEAU & Marc-Antoine Bock
 */
public class GenerateurEvaluations extends javax.swing.JFrame {

    // Sert de marque page pour savoir où modifier dans le fichier CSV l'évaluation

    /**
     * Evaluation en cours de modification, utilisé comme index/ marque page pour la modification d'une évaluation ( voir la fonction majEvaluations() )
     */
    static Evaluation evalEnCours = null;
    javax.swing.JTextField correcteurTF;
    javax.swing.JTextField matTF;
    private javax.swing.JButton ajouterEvalB;
    private javax.swing.JTable evalsTable;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JButton modifEvalB;
    private javax.swing.JTextField noteTF;
    private javax.swing.JButton suppEvalB;
    private javax.swing.JTextField typeTF;
    /**
     * Constructeur de la fenêtre graphique
     */
    GenerateurEvaluations() {
        initComponents();
    }
    GenerateurEvaluations(StudentGenerator genEleve) {
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ChoixProfMatiere.initSwingUIManager();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateurEvaluations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GenerateurEvaluations().setVisible(true));
    }

    /**
     *
     */
    private void verifActivationBoutonAjoutEval() {

        if (!matTF.getText().isEmpty() && !noteTF.getText().isEmpty() && !typeTF.getText().isEmpty() && !correcteurTF.getText().isEmpty())
            ajouterEvalB.setEnabled(true);
        else
            ajouterEvalB.setEnabled(false);
    }

    /**
     * A utiliser obligatoire après l'instatiation de cette classe, active les operations, scripts, événements
     *
     * @param e
     */
    void init(Eleve e) {

        Globals.selectedStudent = e;
        EvaluationsGeneratorOperations operations = new EvaluationsGeneratorOperations();
        operations.afficherEvaluationsEleve(e, evalsTable, mainLabel);
        operations.ajouterDetectionClicLigne(evalsTable, suppEvalB, modifEvalB);

        // Affiche une fenetre de selection
        operations.enableSubjectClicDetectionTF(matTF, this);
        operations.activerDetectionClicProfTF(correcteurTF, this);

        operations.activerRemplissageChampsEval(evalsTable, noteTF, correcteurTF, matTF, typeTF);

        // Ajout d'un listener qui vérifie les zones de texte
        DocumentListener tfListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verifActivationBoutonAjoutEval();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verifActivationBoutonAjoutEval();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verifActivationBoutonAjoutEval();
            }
        };

        matTF.getDocument().addDocumentListener(tfListener);
        correcteurTF.getDocument().addDocumentListener(tfListener);
        typeTF.getDocument().addDocumentListener(tfListener);
        noteTF.getDocument().addDocumentListener(tfListener);

        // Au moment de la fermeture de cette fenêtre, on pourra mettre à jour le texte du bouton
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                majTexteBouton(Globals.selectedStudent);
            }
        });
    }

    /**
     * Met à jour dans la fenêtre de modification ou de génération d'un élève son nombre d'évaluations
     *
     * @param eleveSelectionne l'élève à mettre à jour
     */
    void majTexteBouton(Eleve eleveSelectionne) {
        System.out.println("Maj Texte Bouton...");
        Frame[] frames = Frame.getFrames();
        for (Frame f : frames) {
            System.out.println("Fenetre : " + f.getTitle() + "Type : " + f.getClass());
            if (f.getClass() == EditStudent.class || f.getClass() == StudentGenerator.class) {
                System.out.println("INSTANCE TROUVEE !");
                if (f.getClass() == EditStudent.class)
                    ((EditStudent) f).openGenEvalsB.setText("Ajouter des évaluations ( Nombre actuel : " + eleveSelectionne.getEvaluations().size() + ") ");
                else
                    ((StudentGenerator) f).ouvrirGenEvalsB.setText("Ajouter des évaluations ( Nombre actuel : " + eleveSelectionne.getEvaluations().size() + ") ");
            }
        }
        System.out.println("FIN");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        evalsTable = new javax.swing.JTable();
        ajouterEvalB = new javax.swing.JButton();
        suppEvalB = new javax.swing.JButton();
        mainLabel = new javax.swing.JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel5 = new JLabel();
        noteTF = new javax.swing.JTextField();
        typeTF = new javax.swing.JTextField();
        correcteurTF = new javax.swing.JTextField();
        matTF = new javax.swing.JTextField();
        modifEvalB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        evalsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Note", "Matière", "Correcteur", "Type"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        evalsTable.setRowHeight(30);
        jScrollPane1.setViewportView(evalsTable);

        ajouterEvalB.setText("Ajouter une évaluation");
        ajouterEvalB.setEnabled(false);
        ajouterEvalB.addActionListener(this::ajouterEvalBActionPerformed);

        suppEvalB.setText("Supprimer une évaluation");
        suppEvalB.setEnabled(false);
        suppEvalB.addActionListener(this::suppEvalBActionPerformed);

        mainLabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        mainLabel.setText("Liste des évaluations pour l'élève : ?");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Matière");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel2.setText("Correcteur");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel3.setText("Note");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel5.setText("Type");

        noteTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markTFActionPerformed(evt);
            }
        });

        modifEvalB.setText("Modifier");
        modifEvalB.setEnabled(false);
        modifEvalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEvalBClick(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(suppEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ajouterEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(modifEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(noteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(correcteurTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(117, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(mainLabel)
                                        .addContainerGap(541, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addContainerGap()))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(448, 448, 448)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(suppEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(modifEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(ajouterEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(noteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(correcteurTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(40, 40, 40)
                                                                                .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(matTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                                .addGap(56, 56, 56))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(mainLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(224, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void suppEvalBActionPerformed(java.awt.event.ActionEvent evt) {
        if (Globals.evaluationSelectionnee == null) {
            System.out.println("Impossible de supprimer l'évaluation car aucune n'est selectionnée");
            return;
        }

        suppEvalB.setEnabled(false);
        ((DefaultTableModel) evalsTable.getModel()).removeRow(evalsTable.getSelectedRow());
        evalsTable.clearSelection();
        for (Evaluation eval : Globals.selectedStudent.getEvaluations().toArray(new Evaluation[Globals.selectedStudent.getEvaluations().size()]))
            if (eval == Globals.evaluationSelectionnee)
                Globals.selectedStudent.getEvaluations().remove(eval);

        CSV_Loader.supprimerEvaluationDansFichier(Globals.evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
    }

    /**
     * Gestion du code lorsque qu'on clique sur le bouton d'ajout d'une évaluation
     *
     * @param evt
     */
    private void ajouterEvalBActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Float.parseFloat(noteTF.getText());

            // On vérifie que la note est un entier et qu'elle est comprise dans l'intervaalle [0;20]
            if (Float.parseFloat(noteTF.getText()) <= 20 && Float.parseFloat(noteTF.getText()) >= 0) {

                Matiere mat = Matiere.trouverMatiere(matTF.getText(), Globals.currentPromotion.getNom());
                if (mat == null) {
                    mat = new Matiere(matTF.getText(), Globals.currentPromotion.getNom());
                    Matiere.listeMatieres.add(mat);
                }

                String[] nomPrenom = correcteurTF.getText().split(" ");
                Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);

                if (prof == null) {

                    // Si le prof n'existe pas on le crée directement
                    prof = new Professeur(nomPrenom[0], nomPrenom[1]);
                    Professeur.getListeProfesseurs().add(prof);
                }
                // Création de l'évaluation à l'aide du contenu des zones de texte
                Evaluation eval = new Evaluation(Float.parseFloat(noteTF.getText().replace(',', '.')), mat, Globals.selectedStudent, prof);
                Globals.selectedStudent.getEvaluations().add(eval);
                eval.setEvalType(typeTF.getText());

                // On ajoute l'évaluation à la table
                ((DefaultTableModel) evalsTable.getModel()).addRow(new Object[]{eval.getNote(), eval.getMat().getNom(), eval.getProf().getPrenom(), eval.getEvalType()});
                CSV_Loader.ajouterEvaluationDansFichier(eval, CSV_Loader.EVALUATIONS_PATH);
                System.out.println("Evaluation ajoutée dans la JTABLE !");
            } else {
                noteTF.setText("");
                noteTF.setBorder(new LineBorder(Color.red, 1));
                JOptionPane.showMessageDialog(null, "Note invalide");
            }
        } catch (HeadlessException | NumberFormatException ex) {

            // Affichage d'un message d'erreur pour la note et zone de texte mise en rouge
            noteTF.setText("");
            noteTF.setBorder(new LineBorder(Color.red, 1));
            JOptionPane.showMessageDialog(null, "Format de la note incorrecte");
        }
    }

    /**
     * Mise à jour dans le fichier CSV des évaluations lorsque le bouton de modification de l'évaluation actuellement sélectionnée est cliqué
     *
     * @param evt
     */
    private void editEvalBClick(java.awt.event.ActionEvent evt) {

        if (Globals.evaluationSelectionnee == null)
            System.out.println("(!) Impossible de mettre à jour l'évaluation car NullReferenceException");
        Globals.selectedStudent.getEvaluations().remove(Globals.evaluationSelectionnee);
        updateEvaluation();
        CSV_Loader.majEvaluations(evalEnCours, Globals.evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
        Globals.selectedStudent.add(Globals.evaluationSelectionnee);
        EvaluationsGeneratorOperations operations = new EvaluationsGeneratorOperations();
        operations.afficherEvaluationsEleve(Globals.selectedStudent, evalsTable, mainLabel);
    }

    private void markTFActionPerformed(java.awt.event.ActionEvent evt) {}

    /**
     * Permet de créer une évaluation à partir des zones de texte affichés sur la fenêtre, elle devient l'évaluation sélectionnée
     */
    private void updateEvaluation() {

        Evaluation eval = new Evaluation();
        String[] prenomNom = correcteurTF.getText().split(" ");
        Professeur p = Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]);
        if (p == null) p = new Professeur(prenomNom[1], prenomNom[0]);
        float note = Float.parseFloat(noteTF.getText().replace(',', '.'));
        Matiere mat = Matiere.trouverMatiere(matTF.getText(), Globals.currentPromotion.getNom());
        if (mat == null) mat = new Matiere(matTF.getText(), Globals.currentPromotion.getNom());
        String typeNote = typeTF.getText();
        eval.setEleve(Globals.selectedStudent);
        eval.setEvalType(typeNote);
        eval.setMat(mat);
        eval.setNote(note);
        eval.setProf(p);
        Globals.evaluationSelectionnee = eval;
    }
}
