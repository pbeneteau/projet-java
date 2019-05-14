

package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;

/**
 * Fichier de gestion des événements et opérations de la fenêtre JFrame GenerateurEvaluations.java
 * @author franc
 */
public class GenerateurEvaluationsOperations
{
    
    /**
     * Permet d'ajouter à la table fournie en argument la possibilité de détecter quand une ligne est cliquée, elle permet alors d'obtenir en mémoire l'évaluation sélectionnée
     * Cette fonction gère également l'activation des boutons supprimer et modifier. A chaque fois qu'une ligne (et donc une évaluation) est sélectionnée dans le tableau, les boutons supprimer et modifier
     * une évaluation vont s'activer
     * @param evalsTable table à analyser
     * @param buttonSupp bouton de suppression d'une évaluation
     * @param buttonModif bouton de modification d'une évaluation
     */
    public void ajouterDetectionClicLigne(JTable evalsTable, JButton buttonSupp, JButton buttonModif)
    {
          evalsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(evalsTable.getSelectedRow()>=0)
                {
                     System.out.println("Ligne cliquée changée !");
                buttonSupp.setEnabled(true);
                buttonModif.setEnabled(true);
                float note = (float)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 0);
                String matString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 1);
                String profString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 2);
                String typeString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 3);
                
                if(Globals.eleveSelectionne != null)
                for(Evaluation eval : Globals.eleveSelectionne.getEvaluations())
                {
                    if(eval.getNote()== note)
                    {
                        if( eval.getEvalType() == typeString)
                        {
                            if(eval.getMat().getNom()== matString)
                            {
                                String nomPrenom = eval.getProf().getPrenom()+ " "+ eval.getProf().getNom();
                                if(nomPrenom.equals(profString))
                                {
                                    System.out.println(">>>>>>> Evaluation sélectionnée !");
                                    Globals.evaluationSelectionnee = eval;
                                    GenerateurEvaluations.evalEnCours = eval;
                                     break;
                                }
                            }
                        }
                    } 
                    System.out.println();
                }
                else System.out.println("(!) Erreur  : Aucun élève n'est sélectionné");
                }
               
            }
        });
    }
    
    /**
     * Affiche les évaluations d'un élève dans la JTable indiquée
     * @param e
     * @param evaluationsTable
     * @param mainLabel
     */
    public void afficherEvaluationsEleve(Eleve e, JTable evaluationsTable, JLabel mainLabel)
    {
        
        System.out.println("Début d'affichage des évaluations");
        DefaultTableModel model = new DefaultTableModel();
        
        Object[] columns = {"Note","Matière","Correcteur","Type"};
        model.setColumnIdentifiers(columns);
        Object[] rows = new Object[evaluationsTable.getColumnCount()]; // par défaut 4 colonnes   
        System.out.println(System.lineSeparator()+rows.length +" évaluations trouvées");
        for(Evaluation eval : e.getEvaluations())
        {
                    rows[0] = eval.getNote();                                                                    // NOTE
                    rows[1] = eval.getMat().getNom();                                                            // MATIERE
                    rows[2] = eval.getProf().getPrenom() + " " + eval.getProf().getNom();                      // CORRECTEUR
                    rows[3] = eval.getEvalType();                                                              // TYPE
                            model.addRow(rows);
                            System.out.println("Ajout de " + eval);
        }
        evaluationsTable.setModel(model);
        evaluationsTable.repaint();
        mainLabel.setText("Liste des évaluations pour l'élève : "+ e.getNom().toUpperCase() + " " + e.getPrenom());
        System.out.println("Terminé!"); 
    }

    /**
     * Détecte à chaque fois que l'on clique sur une ligne du tableau et affiche son contenu automatiquement dans les champs passés en argument
     * @param evalsTable table des évaluations d'un élève
     * @param champNote zone de texte de la note de la ligne sélectionné
     * @param champCorrecteur zone du correcteur de la ligne sélectionné
     * @param champMatiere zone de la matière de la ligne sélectionné
     * @param champType zone du type d'évaluation de la ligne sélectionné
     */
    public void activerRemplissageChampsEval(JTable evalsTable, JTextField champNote, JTextField champCorrecteur, JTextField champMatiere, JTextField champType) {
  evalsTable.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e)
           {
                remplirChamps(evalsTable.getSelectedRow(), evalsTable,champNote,champCorrecteur,champMatiere,champType);
           }
       });    }
    
    /**
     * Remplit automatiquement les champs/zones de texte passés en argument en fonction de la ligne sélectionée dans la JTable 
     * @param rowNumber ligne de la table
     * @param evalsTable table des évaluations d'un élève
     * @param champNote zone de texte de la note de la ligne sélectionné
     * @param champCorrecteur zone du correcteur de la ligne sélectionné
     * @param champMatiere zone de la matière de la ligne sélectionné
     * @param champType zone du type d'évaluation de la ligne sélectionné
     */
    public void remplirChamps(int rowNumber, JTable evalsTable, JTextField champNote, JTextField champCorrecteur, JTextField champMatiere, JTextField champType)
    {
         float note = (float)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 0);
        String matString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 1);
        String correctString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 2);
        String typeString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 3);

         Matiere mat =Matiere.trouverMatiere(matString,Globals.promoActuelle.getNom());
        if(mat==null)
        {
             mat = new Matiere(matString,Globals.promoActuelle.getNom());
            Matiere.listeMatieres.add(mat);
        }
        
        String[] nomPrenom = correctString.split(" ");
        Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);
        if(prof==null)
        {
          prof = new Professeur(nomPrenom[0],nomPrenom[1]);
          Professeur.getListeProfesseurs().add(prof);
        }
        Evaluation eval = new Evaluation(note, mat,Globals.eleveSelectionne, prof);
        eval.setEvalType(typeString);
        champNote.setText(String.format("%.1f", eval.getNote()));
        champCorrecteur.setText(eval.getProf().getPrenom() + " " +eval.getProf().getNom());
        champMatiere.setText(eval.getMat().getNom());
        champType.setText(eval.getEvalType());
        
    }
    
    /**
     *  Deteccte quand la zone de texte est cliquée et ouvre la fenêtre en fonction du type de fenêtre fourni (matière prof) puis l'initialise
     * @param tfClique
     * @param typeFenetre
     * @param gen 
     */
    private void ouvrirFenetreClicField(JTextField tfClique, ChoixSelectionType typeFenetre, GenerateurEvaluations gen)
    {
        tfClique.addMouseListener(new MouseAdapter() {
            @Override 
            public void mousePressed(MouseEvent event)
            {
                System.out.println("Clic sur " + tfClique.getName());
                ChoixProfMatiere fenetre = new ChoixProfMatiere();
                fenetre.init(typeFenetre, gen);
                fenetre.setVisible(true);
            }

        });
    }

    /**
     * Permet de savoir quand la zone de texte d'une matière est cliquée et cela permet à ce moment là d'ouvrir la fenêtre de gestion des matières
     * @param matTF zone de texte pour une matière d'une évaluation
     * @param gen
     */
    public void activerDetectionClicMatiereTF(JTextField matTF, GenerateurEvaluations gen) 
    {
        System.out.println("activerDetectionClicMatiereTF()");
        ouvrirFenetreClicField(matTF, ChoixSelectionType.Matiere, gen);
    }

    /**
     * Permet de savoir quand la zone de texte d'un professeur est cliquée et cela permet à ce moment là d'ouvrir la fenêtre de gestion des professeurs
     * @param correcteurTF
     * @param gen
     */
    public void activerDetectionClicProfTF(JTextField correcteurTF, GenerateurEvaluations gen) 
    {
       System.out.println("activerDetectionClicProfTF()");
        ouvrirFenetreClicField(correcteurTF, ChoixSelectionType.Professeur, gen);
    }
          
}
