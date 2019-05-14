

package notesElevesProfesseurs;

import java.util.*;

public class Eleve extends Personne implements Iterable<Evaluation>, Comparable<Eleve>  {


    private static int registre = 1; // identifiant qui s'autoincrémente lors de la création d'un élève
    final int NB_EVALUATION = 10;    // nombre d'évaluations maximale que peut avoir un élève
    private int id;
    private Date dateNaissance;
    private ArrayList<Evaluation> evaluations; 
    private Promotion promotion;
    
    public Eleve() {    
        this.id = Eleve.registre;
        this.evaluations = new ArrayList<>(NB_EVALUATION);
        Eleve.registre++; 
    }

    public Eleve(String prenom, String nom, int anneeNaissance, int moisNaissance, int jourNaissance){
        // Le this() permet d'appeler le premier constructeur, celui sans arguments
        this();
        this.setPrenom(prenom);
        this.setNom(nom);
        this.dateNaissance = new Date(anneeNaissance,moisNaissance,jourNaissance);
    }
    
        public Eleve(String prenom, String nom, Date dateNaissance){
        // Le this() permet d'appeler le premier constructeur, celui sans arguments
        this();
        this.setPrenom(prenom);
        this.setNom(nom);
        this.dateNaissance = dateNaissance;

    }
    
        // Il est aussi possible de créer un élève en précisant en plus sa promotion
        public Eleve(String prenom, String nom, int annee, int mois, int jour, Promotion promo){
            this( prenom,  nom,  annee,  mois,  jour);
            this.promotion = promo;
    }

     /**
     * @return  dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public Iterator<Evaluation> iterator() {
        return this.evaluations.iterator();
    }

    public void add(Evaluation e) {
        if (evaluations.size() < NB_EVALUATION) 
        {
            this.evaluations.add(e);
        } else {
            System.out.println("vous ne pouvez plus ajouter de notes (limite : " + NB_EVALUATION + " notes)");
        }
    }

    /**
     * Calcule la moyenne de cet élève grâce à toutes ses évaluations
     * @return la moyenne
     */
    public float calculMoyenne() {
        float result = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            result += evaluations.get(i).getNote();
        }
        return  result / evaluations.size();
    }

        /**
     * Calcule la médiane de cet élève grâce à toutes ses évaluations
     * @return la médiane
     */
    public float calculMedianne() {
        this.triNote();
        float result = 0;
        // 2 cas possible 
        // Nombre pair
          if (evaluations.size() % 2 == 0) {
            result = evaluations.get((int) (evaluations.size() / 2)).getNote() + evaluations.get((int) (evaluations.size() / 2) - 1).getNote();
            return  result / 2;
        } else
          // Nombre impair
          {
            return  evaluations.get((int) (evaluations.size() / 2)).getNote();
        }
    }

    /**
     * Tri des évaluations par note
     */
    public void triNote() {
        Collections.sort(evaluations, new ComparatoNote());
    }

    /**
     * Obtient l'ensemble des professeurs ayant corrigé cet élève
     * @return 
     */
    public Set<Professeur> getCorrecteurs()
    {
        HashSet<Professeur> h = new HashSet<>();
        for (int i = 0; i < evaluations.size(); i++){
            h.add(evaluations.get(i).getProf());
        }
        return h;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString()
    {
        String s = super.toString()+"id: " + this.getId()+"\nPromotion : " +  this.getPromotion().getNom()+"\n";
        if(getEvaluations()!=null && getEvaluations().size()>0)
        {
        s+= this.affNotes()+"\n";
        s+= "Moyenne : " + this.calculMoyenne()+"\n";
        s+= "Mediane : " + this.calculMedianne()+"\n";
        s+= "Date de naissance " + this.dateNaissance+"\n";
        s+= "Correcteur(s): "+this.getCorrecteurs()+"\n";
        }
        return s;
    }

    /**
     * @return Obtient la liste des évaluations pour cet élève
     */
    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }
    
    /*
    Affiche les notes une par une en indiquant le nom de la matière puis la note elle même
    */
    public String affNotes() {
        String s = "notes: ";
        for (int i = 0; i < evaluations.size(); i++) {
            s += evaluations.get(i).getMat().getNom()+" "+evaluations.get(i).getNote()+" ";
        }
        return s;

    }

    /**
     * @return la promotion de cet élève
     */
    public Promotion getPromotion() {
        return promotion;
    }
  
    
    public void setPromotion(Promotion p)
    {
        promotion = p;
    }

    /**
     * Compare un élève à un autre en comparant d'abord leurs moyennes, si elles sont égales, leurs médianes sont alors comparées
     * @param o autre élève à comparé
     * @return entier de comparaison
     */
    @Override
    public int compareTo(Eleve o) {
        float moy = this.calculMoyenne() - o.calculMoyenne();
        if(moy!=0) return (int) moy;
        return (int) ((int) this.calculMedianne() - o.calculMedianne());
    } 
    
  
}
