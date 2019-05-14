package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.HashSet;

public class Professeur extends Personne 
{

    /**
     * Constitue l'ensemble des professeurs répertoriés dans l'application
     */
    private static HashSet<Professeur> listeProfesseurs = new HashSet<>();
    
    public Professeur() {}
    public Professeur(String prenom, String nom) 
    {
        setPrenom(prenom);
        setNom(nom);
    }

    /**
     * Un professeur peut rechercher un élève dans une promotion
     * @param promo La promotion de l'élève
     * @param identifiantEleve l'identifiant de l'élève recherché
     * @return l'élève recherché par le professeur
     */
     Eleve Rechercher(Promotion promo, int identifiantEleve)
     {
       for( Eleve e :  promo.getEleves()) { if(e.getId()==identifiantEleve) return e;}                   
       return null;
     }
     
     public void setNote(Promotion promo, int identifiantEleve, float note, int indexNote)
     {
         Eleve e = Rechercher(promo, identifiantEleve);
         if(e==null)throw new IllegalStateException(); // Si la recherche échoue, on lance une exception
         System.out.println("Modification de la note de l'élève: " + e  );
         if( indexNote >  e.getEvaluations().size()-1 && indexNote>0) // Si l'évaluation existe
             e.getEvaluations().get(indexNote).setNote(note);
         else  // Si la note n'existe pas, on la créee
             e.getEvaluations().add(new Evaluation(note,null, e, null));
             
         System.out.println("Echec, note non trouvée à l'index :" +indexNote );
     }
     
     /**
     * @return constitue le getter pour la variable listeProfesseurs
     */
    public static HashSet<Professeur> getListeProfesseurs() {
        return listeProfesseurs;
    }
     
    /**
     * Recherche un professeur à l'aide de son nom et son prenom. Retourne null si il n'est pas trouvé
     * @param nom du professeur
     * @param prenom du professeur
     * @return le professeur recherché
     */
    public static Professeur trouverProfesseur(String nom, String prenom)
    {
        System.out.println("Recherche : " + prenom + " " + nom);
        HashSet<Professeur> profs = getListeProfesseurs();
        for(Professeur p : profs )
        {
            if(p.getNom().equals(nom) && p.getPrenom().equals(prenom))
            return p;
        }
        return null;
    }
}
