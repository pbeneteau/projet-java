/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de gérer l'ensemble des opérations en CSV qu'il s'agisse de chargement ou sauvegarde de données et qu'il s'agisse pour des versions consoles ou graphiques (méthodes universelles)
 * @author franc
 */
public class CSV_Loader 
{    
    
    /**
     * Charge toutes les données lues dans les fichiers CSV passés en argument de cette fonction, le logiciel aura alors toutes ses données créees (matieres, eleves, profs...)
     * @param pathEleves Chemin du fichier contenant tous les élèves
     * @param pathEvaluations Chemin du fichier contenant toutes les évaluations
     * @throws FileNotFoundException
     */
    public static void start(String pathEleves, String pathEvaluations) throws FileNotFoundException
    {
            CSV_Loader.chargerFichierEleves(pathEleves);   
            CSV_Loader.chargerEvaluations(pathEvaluations);
    }
    

    /**
     *Charge les élèves dans leurs promotions respectives, indiquées dans le fichier en format CSV
     * @param chemin chemin du fichier élèves
     * @throws FileNotFoundException Si le fichier élèves n'existe pas
     */
    public static void chargerFichierEleves(String chemin) throws FileNotFoundException
    {
        if(!new File(chemin).exists())
        {
            System.out.println("Le fichier demandé n'existe pas à "+ chemin);
        }
        else 
        {
            // Le chemin qui est validé car il existe dans l'ordinateur, devient accessible depuis n'importe quel fichier
            ELEVES_PATH = chemin;
            Scanner scanner = new Scanner(new File(chemin));
            boolean firstLineDone = false;
            while(scanner.hasNext()) // tant que le scanner parvient à lire des lignes
            {
                if(!firstLineDone)
                {
                    // La première ligne est purement décorative donc on la saute
                   firstLineDone = true;   
                   scanner.nextLine(); 
                }
                else
                {
                    String line = scanner.nextLine(); // On récupère la ligne suivante dans le scanner
                    if(!"".equals(line.trim())) // on vérifie si la ligne n'est pas vide, sinon on la saute
                    {
                    
                    // On récupère l'ensemble des données de la ligne en obtenant toutes les données séparées par des points virgule
                    String[] lineStrings = line.split(";");
                    Eleve e = new Eleve();
                    e.setNom(lineStrings[1]);
                    e.setPrenom(lineStrings[2]);
                    
                     if(lineStrings[4] != null)
                    {
                        // On sépare la date de naissance en 3 parties afin de pouvoir créer un objet
                        String[] dateString = lineStrings[4].split("/");
                        e.setDateNaissance(new Date(Integer.parseInt(dateString[2]),Integer.parseInt(dateString[1]), Integer.parseInt(dateString[0])));
                    }
                     
                    Promotion recherchePromo = Promotion.trouverPromotion(lineStrings[3]);
                    if( recherchePromo !=null)
                    {
                       Promotion.trouverPromotion(lineStrings[3]).ajouterEleve(e);
                    }
                    else 
                    {
                        // Si la promotion lue dans la case n'existe pas, on la crée 
                        Promotion nouvellePromo = new Promotion(lineStrings[3]);
                        nouvellePromo.ajouterEleve(e);
                        Promotion.getListePromos().add(nouvellePromo);
                    }
                   
                }
                    }
                  
            }
            System.out.println("\nTerminé!");
        }
    }
    
    // Index des différentes colonnes du fichier contenant les évaluations
    final static int COL_IDENTIFIANT = 0;
    final static int COL_NOTES = 1;    
    final static int COL_MATIERE = 2;
    final static int COL_CORRECTEUR = 3;
    final static int COL_TYPE = 4;
    
    /**
     * Chemin Global accessible depuis n'importe quel autre fichier du fichier élève
     */
    public static String ELEVES_PATH;

    /**
     *Chemin Global accessible depuis n'importe quel autre fichier du fichier évaluations
     */
    public static String EVALUATIONS_PATH;
    

    /**
     * Charge l'ensemble des notes/evaluations dans le logiciel
     * @param chemin
     * @throws FileNotFoundException
     */
    public static void chargerEvaluations(String chemin) throws FileNotFoundException
    {
        // Sécurité, nécéssite au préalable d'avoir chargé les élèves, sinon erreur et arrêt du programme
        if(Promotion.getListePromos() == null || Promotion.getListePromos().isEmpty())
        {
            System.out.println("(!) Erreur, il faut avoir chargé le fichier des élèves avant de charger le fichier des évaluations." + System.lineSeparator() +" fin du programme... ");
            System.exit(-1);
        }
         if(!new File(chemin).exists()) // Sécurité
        {
            System.out.println("Le fichier demandé n'existe pas");
        }
        else 
        {
            // Le chemin qui est validé car il existe dans l'ordinateur, devient accessible depuis n'importe quel fichier
            EVALUATIONS_PATH = chemin;
            Scanner scanner = new Scanner(new File(chemin));
            ArrayList<Evaluation> evals = new ArrayList<>();
            Professeur nouveauProf = null;
            boolean firstLineChecked= false;
            while(scanner.hasNext())
            {
                // Idée algorithmique : Possibilité de mettre en place facilement une gestion des élèves peu importe la position de la colonne via la lecture de la premiere ligne
                if(!firstLineChecked)
                {
                    firstLineChecked=true;
                    scanner.nextLine();
                    continue;
                }
                nouveauProf = null;
                String line = scanner.nextLine();
                System.out.println("Traitement : " + line);
                String[] lineStrings = line.split(";");
                Eleve eleveNote = Promotion.rechercherElevePartout( Integer.parseInt(lineStrings[COL_IDENTIFIANT]));
                if(Matiere.trouverMatiere(lineStrings[COL_MATIERE],eleveNote.getPromotion().getNom()) == null && !"".equals(lineStrings[COL_MATIERE].trim())) // Si la matière cherchée n'est pas trouvée, on la créee
                {
                    Matiere.listeMatieres.add(new Matiere(lineStrings[COL_MATIERE],eleveNote.getPromotion().getNom()));
                }
                
                if(lineStrings[COL_CORRECTEUR] != null && !"".equals(lineStrings[COL_CORRECTEUR].trim()) )
                {
                    String[] prenomNom = lineStrings[COL_CORRECTEUR].split(" ");
                     nouveauProf = new Professeur(prenomNom[0],prenomNom[1]);
                    if(Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]) == null)
                    Professeur.getListeProfesseurs().add(nouveauProf);
                }

            
                float note = Float.parseFloat(lineStrings[COL_NOTES].replace(',','.'));
                Matiere mat = Matiere.trouverMatiere(lineStrings[COL_MATIERE],eleveNote.getPromotion().getNom());
                Evaluation e = new Evaluation(note,mat,eleveNote ,nouveauProf);
                
                if(lineStrings[COL_TYPE]!=null && !"".equals(lineStrings[COL_TYPE].trim())) // Type de note (optionnel)
                {
                    e.setEvalType(lineStrings[COL_TYPE]);
                }
                evals.add(e);
            }
            attribuerEvaluationsAuxEleves(evals);
            System.out.println("Terminé!");
        }
    }
    
    /**
     * Supprime un élève dans le fichier CSV indiqué
     * @param e Eleve à supprimer
     * @param chemin Chemin du fichier CSV élève
     */
    public static void supprimerEleveDansFichier(Eleve e, String chemin) 
    {
          File f = new File(chemin);
        if(e!=null)
        if(!f.exists()) // Sécurité
        {
            System.out.println("Impossible d'ajouter l'élève, le fichier n'existe pas au chemin : " + chemin);
        }
        else
        {
              try {
                  List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                  ArrayList<String> linesNew = new ArrayList<>();
                  for(String line : lines)
                      linesNew.add(line);
                  
                  for(String line : lines)
                  {
                      if(line.contains(e.getNom()) && line.contains(e.getPrenom()) && line.contains(e.getPromotion().getNom()))
                      {
                          linesNew.remove(line);
                          System.out.println("Eleve supprimé du fichier CSV");
                          break;
                      }
                  } 
                Files.write(f.toPath(), linesNew, StandardCharsets.UTF_8);
              } 
              catch (IOException ex) {
                  Logger.getLogger(CSV_Loader.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }
    
    /**
     * Supprime une évaluation/note dans le fichier des évaluations
     * @param eval Evaluation à supprimer
     * @param chemin Chemin du fichier CSV des évaluations
     */
    public static void supprimerEvaluationDansFichier(Evaluation eval, String chemin) 
    {
          File f = new File(chemin);
        if(eval!=null)
        if(!f.exists())
        {
            System.out.println("Impossible de supprimer l'évaluation, le fichier n'existe pas au chemin : " + chemin);
        }
        else
        {
              try {
                  List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                  ArrayList<String> linesNew = new ArrayList<>();
                  for(String line : lines)
                      linesNew.add(line);
                  
                  
                  for(String line : lines)
                  {
                      if(line == lines.get(0)) continue;
                      String[] lineParts = line.split(";");
                      for(String part : lineParts)
                      {
                          System.out.print(part ); 
                      if(part!=lineParts[lineParts.length-1])
                         System.out.print(";");
                      }
                      System.out.println(System.lineSeparator()+ eval.getEleve().getId()+";" +eval.getNote()+";"+eval.getMat().getNom()+";"+eval.getProf().getPrenom() +";"+ eval.getProf().getNom() );
                      System.out.println("");
                      // on compare à un à un les caractéristiques des 2 évaluations pour voir si il faut la supprimer
                      if(lineParts[0].equals(String.valueOf(eval.getEleve().getId())) && eval.getNote() == Float.parseFloat(lineParts[1].replace(',', '.')) && lineParts[2].equals(eval.getMat().getNom()) && lineParts[3].equals(eval.getProf().getPrenom() + " " + eval.getProf().getNom() ) )
                      {
                          linesNew.remove(line);
                          System.out.println("Note supprimée du fichier CSV");
                          break;
                      }
                  }
                Files.write(f.toPath(), linesNew, StandardCharsets.UTF_8);
              } catch (IOException ex) {
                  Logger.getLogger(CSV_Loader.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }
    
    /**
     * Ajoute une évaluation/note dans le fichier des évaluations
     * @param e Eleve à ajouter
     * @param path Chemin du fichier CSV des évaluations
     */
    public static void ajouterEleveDansFichier(Eleve e,String path)
    {
        File f = new File(path);
        if(e!=null)
        if(!f.exists())
        {
            System.out.println("Impossible d'ajouter l'élève, le fichier n'existe pas au chemin : " + path);
        }
        else 
        {
            try {
                Date d = e.getDateNaissance();
                List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                String csvEleveFormat = String.format("%d;%s;%s;%s;%d/%d/%d",lines.size()-1,e.getNom(),e.getPrenom(),e.getPromotion().getNom(),d.getJour(),d.getMois(),d.getAnnee());
                // On ajoute une nouvelle ligne à la liste des lignes
                lines.add(lines.size()-1, csvEleveFormat);
                // Puis on écrit cela dans le fichier CSV
                Files.write(f.toPath(), lines, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                System.out.println("(!) Erreur ajout élève, vérifiez que le fichier n'est pas déjà ouvert");
                System.out.println(System.lineSeparator()+"Que souhaitez vous faire ?");
                System.out.println(" - Réessayer (1)");
                System.out.println(" - Quitter (2)");
                Scanner s = new Scanner(System.in);
                try {
                      s.nextInt();
                } catch (Exception exception) {
                    System.out.println(" (!) Erreur");
                    ajouterEleveDansFichier(e, path);
                }
            }
        }
    }
    
    /**
     * Ajoute un élève dans le fichier des élèves indiqué à la position indiquée
     * @param e L'élève à ajouter
     * @param path chemin du fichier CSV des élèves
     * @param pos
     */
    public static void ajouterEleveDansFichier(Eleve e,String path, int pos)
    {
        File f = new File(path);
        if(e!=null)
        if(!f.exists())
        {
            System.out.println("Impossible d'ajouter l'élève, le fichier n'existe pas au chemin : " + path);
        }
        else 
        {
            try {
                Date d = e.getDateNaissance();
                List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                String csvEleveFormat = String.format("%d;%s;%s;%s;%d/%d/%d",lines.size()-1,e.getNom(),e.getPrenom(),e.getPromotion().getNom(),d.getJour(),d.getMois(),d.getAnnee());
                // On remplace la ligne 
                lines.set(pos, csvEleveFormat);
                Files.write(f.toPath(), lines, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                System.out.println("(!) Erreur modif élève, vérifiez que le fichier n'est pas déjà ouvert");
                System.out.println(System.lineSeparator()+"Que souhaitez vous faire ?");
                System.out.println(" - Réessayer (1)");
                System.out.println(" - Quitter (2)");
                Scanner s = new Scanner(System.in);
                try {
                      s.nextInt();
                } catch (Exception exception) {
                    System.out.println(" (!) Erreur");
                    ajouterEleveDansFichier(e, path,pos);
                }
            }
        }
    }
    
    /**
     * Ajoute une évaluation dans le fichier des évaluations indiqué
     * @param eval L'évaluation à ajouter
     * @param path Chemin du fichier des évaluations
     */
    public static void ajouterEvaluationDansFichier(Evaluation eval,String path)
    {
        File f = new File(path);
        if(eval!=null)
        if(!f.exists())
        {
            System.out.println("Impossible d'ajouter l'élève, le fichier n'existe pas au chemin : " + path);
        }
        else 
        {
             List<String> lines = null;
            try {
                lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                String csvEvalFormat = String.format("%d;%f;%s;%s;%s",eval.getEleve().getId(),eval.getNote(),eval.getMat().getNom(),eval.getProf().getPrenom() + " " + eval.getProf().getNom(), eval.getEvalType());
                lines.add(csvEvalFormat);
                Files.write(f.toPath(), lines, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                System.out.println("(!) Erreur modif évaluation, vérifiez que le fichier n'est pas déjà ouvert");
                System.out.println(System.lineSeparator()+"Que souhaitez vous faire ?");
                System.out.println(" - Réessayer (1)");
                System.out.println(" - Quitter (2)");
                Scanner s = new Scanner(System.in);
                try {
                      s.nextInt();
                } catch (Exception exception) {
                    System.out.println(" (!) Erreur");
                    ajouterEvaluationDansFichier(eval, path);
                }
            }
        }
    }
   
    /**
     * Permet d'ajouter des évaluations fournies aux évaluations pour chaque élève de l'ensemble des promotions, si l'évaluation leur appartient
     * @param evals  Liste des évaluations à attribuer aux élèves existants 
     */
    private static void attribuerEvaluationsAuxEleves(ArrayList<Evaluation> evals)
    {
        // On parcourt toutes les promotions
        for(Promotion p : Promotion.getListePromos())
        {
            for(Eleve e: p.getEleves())
            {
                for(Evaluation eval : evals)
                {
                    if(eval.getEleve() == e && !e.getEvaluations().contains(eval))
                        e.getEvaluations().add(eval);
                }
            }
        }
    }


    /**
     * Met à jour toutes les caracteristiques de l'élève sauf les évaluations
     * @param e élève à mettre à jour
     * @param chemin chemin du fichier CSV des évaluations
     */

    public static void majEleve(Eleve e, String chemin)
    {
        File f = new File(chemin);
        if(e!=null)
        if(!f.exists())
        {
            System.out.println("Impossible de mettre à jour l'élève, le fichier n'existe pas au chemin : " + chemin);
        }
        else 
        {
            ajouterEleveDansFichier(e, chemin, e.getId());
        }
    }
    
    /**
     *Met à jour une évaluation particulière, utilisé pour la version console uniquement
     * @param eval l'évaluation à mettre à jour
     * @param chemin chemin du fichier CSV des évaluations
     */
    public static void majEvaluations(Evaluation eval, String chemin) 
    {
        File f = new File(chemin);
        if(eval!=null)
        if(!f.exists())
        {
            System.out.println("[CSV]Impossible de mettre à jour l'évaluation, le fichier n'existe pas au chemin : " + chemin);
        }
        else 
        {
               try {
                   System.out.println("[CSV]Début mise à jour de l'évaluation " + eval);
                List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                String csvEvalFormat = String.format("%d;%.1f;%s;%s;%s",eval.getEleve().getId(),eval.getNote(),eval.getMat().getNom(),eval.getProf().getPrenom() + " " + eval.getProf().getNom(), eval.getEvalType());
                csvEvalFormat = csvEvalFormat.replace(',', '.');
                System.out.println(csvEvalFormat);
                for(int i = 0; i < lines.size() ; i++ )
                {
                    String[] lineParts = lines.get(i).split(";");
                    if(lineParts[0].equals(String.valueOf(eval.getEleve().getId())) && Float.parseFloat(lineParts[1].replace(',', '.')) == eval.getNote() && lineParts[2].equals(eval.getMat().getNom()) && lineParts[3].equals(eval.getProf().getPrenom() + " " + eval.getProf().getNom()) )
                    {
                        System.out.println("[CSV]Ligne changée! "+ System.lineSeparator() +" Ligne initiale : " + lines.get(i) );
                        lines.set(i, csvEvalFormat);
                        System.out.println("[CSV] Ligne maj : " + lines.get(i) );
                        break;
                    }
                }
                Files.write(f.toPath(), lines, StandardCharsets.UTF_8);
                   System.out.println("Modification écrite dans le fichier CSV");
            } catch (IOException ex) {
                System.out.println("(!) Erreur modif élève, vérifiez que le fichier n'est pas déjà ouvert");
                System.out.println(System.lineSeparator()+"Que souhaitez vous faire ?");
                System.out.println(" - Réessayer (1)");
                System.out.println(" - Quitter (2)");
                Scanner s = new Scanner(System.in);
                try {
                    int choixErreur =  s.nextInt();
                    if(choixErreur != 1 )
                        System.exit(0);
                } catch (Exception exception) {
                    System.out.println(" (!) Erreur de modification de l'évaluation de l'élève : " );
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * Met à jour une évaluation en passant en argument l'ancienne évaluation puis la nouvelle
     * @param evalMarquePage Ancienne évaluation, elle sert d'index pour repérer celle à remplacer le fichier (le fichier ne possédant pas d'index lui même)
     * @param nouvelleEvaluation  l'évaluation qui va remplacer l'évaluation index dans le fichier indiqué
     * @param chemin chemin du fichier CSV des évaluations
     */
    public static void majEvaluations(Evaluation evalMarquePage, Evaluation nouvelleEvaluation, String chemin) 
    { File f = new File(chemin);
        if(nouvelleEvaluation!=null && evalMarquePage!=null)
        if(!f.exists())
        {
            System.out.println("[CSV]Impossible de mettre à jour l'évaluation, le fichier n'existe pas au chemin : " + chemin);
        }
        else 
        {
               try {
                   System.out.println("[CSV]Début mise à jour de l'évaluation " + evalMarquePage);
                List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                String oldEvalFormat = String.format("%d;%.1f;%s;%s;%s",evalMarquePage.getEleve().getId(),evalMarquePage.getNote(),evalMarquePage.getMat().getNom(),evalMarquePage.getProf().getPrenom() + " " + evalMarquePage.getProf().getNom(), evalMarquePage.getEvalType());
                String newEvalFormat = String.format("%d;%.1f;%s;%s;%s",nouvelleEvaluation.getEleve().getId(),nouvelleEvaluation.getNote(),nouvelleEvaluation.getMat().getNom(),nouvelleEvaluation.getProf().getPrenom() + " " + nouvelleEvaluation.getProf().getNom(), nouvelleEvaluation.getEvalType());
                oldEvalFormat = oldEvalFormat.replace(',', '.');
                newEvalFormat = newEvalFormat.replace(',', '.');
                System.out.println(oldEvalFormat);
                for(int i = 0; i < lines.size() ; i++ )
                {
                    String[] lineParts = lines.get(i).split(";");
                    if(lineParts[0].equals(String.valueOf(evalMarquePage.getEleve().getId())) && Float.parseFloat(lineParts[1].replace(',', '.')) == evalMarquePage.getNote() && lineParts[2].equals(evalMarquePage.getMat().getNom()) && lineParts[3].equals(evalMarquePage.getProf().getPrenom() + " " + evalMarquePage.getProf().getNom()) )
                    {
                        System.out.println("[CSV]Ligne changée! "+ System.lineSeparator() +" Ligne initiale : " + lines.get(i) );
                        lines.set(i, newEvalFormat);
                        System.out.println("[CSV] Ligne maj : " + lines.get(i) );
                        break;
                    }
                }
                Files.write(f.toPath(), lines, StandardCharsets.UTF_8);
                   System.out.println("Modification écrite dans le fichier CSV");
            } catch (IOException ex) {
                System.out.println("(!) Erreur modif élève, vérifiez que le fichier n'est pas déjà ouvert");
                System.out.println(System.lineSeparator()+"Que souhaitez vous faire ?");
                System.out.println(" - Réessayer (1)");
                System.out.println(" - Quitter (2)");
                Scanner s = new Scanner(System.in);
                try {
                    int choixErreur =  s.nextInt();
                    if(choixErreur != 1 )
                        System.exit(0);
                } catch (Exception exception) {
                    System.out.println(" (!) Erreur de modification de l'évaluation de l'élève : " );
                    System.out.println(ex);
                }
            }
        }
    }
}
