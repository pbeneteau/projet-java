

package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Promotion {

  // Les variables sont privées car accessibles avec des getters/setters
    private String nom;
    private ArrayList<Eleve> eleves= new ArrayList<>();
    private static ArrayList<Promotion> listePromos = new ArrayList<>();
    
    /**
     * La promotion nécéssite un nom pour être créee
     * @param nom 
     */
    public Promotion(String nom)
    {
       this.nom = nom;
    }
    
    /**
     * Recherche un élève par identifiant dans toutes les promotions connues par le logiciel
     * @param identifiant identifiant de l'élève recherché
     * @return l'élève recherché
     */
        public static Eleve rechercherElevePartout(int identifiant) {
        
        Eleve e;
         for(Promotion promoActuelle : Promotion.getListePromos() )
        {
           e = promoActuelle.rechercherEleve(identifiant);
           if(e!=null) return e;
        }
         return null;
    }

        
    /**
     * Permet d'ajouter un élève à cette promotion
     * @param e l'élève à ajouter
     */
    public void ajouterEleve(Eleve e) {
        eleves.add(e);
        e.setPromotion(this);
    }

    /**
     * Recherche un élève par identifiant dans cette promotion 
     * @param identifiant l'identifiant de l'élève
     * @return l'élève trouvé
     */
    Eleve RechercherEleve(int identifiant)
    {
        for(Eleve e : eleves)
        {
           if(e.getId()==identifiant)
            return e;
        }
        return null;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return l'ensemble des élèves de cette promotion
     */
    public ArrayList<Eleve> getEleves() {
        return eleves;
    }
    
    /**
     * Liste l'ensemble des élèves d'une promotion en indiquant au moins leur identifiant, leur nom et leur prénom
     * @param detailler si à Vrai, on liste les élèves avec davantage d'informations comme leur date de naissance
     */
    public void listerEleves(boolean detailler)
    {
        System.out.println("Affichage des élèves pour la promotion : " + getNom());   
        System.out.println("----------------------------");
        for(Eleve e : getEleves())
       {
           if(detailler)
               System.out.println(e);
           else
               System.out.println( "ID : "+ e.getId()+ "|"+ e.getNom()+ "|" + e.getPrenom() );
           System.out.println("---------------------------------");
       }
    }

    /**
     * @return the listePromos
     */
    public static ArrayList<Promotion> getListePromos() {
        return listePromos;
    }

    /**
     * @param aListePromos the listePromos to set
     */
    public static void setListePromos(ArrayList<Promotion> aListePromos) {
        listePromos = aListePromos;
    }

    public Eleve rechercherEleve(int searchId) 
    {
        for(Eleve e : getEleves())
            if(e.getId()==searchId) return e;
        return null;
    }

    // Ici nous avons l'ensemble des classes de comparaisons qui peuvent être utilisées dans la méthode Collection.sort()
    // afin de pouvoir trier nos élèves    
    public class MoyenneComparator implements Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) {
            return (int)(e1.calculMoyenne() - e2.calculMoyenne());
        }
    }
    
    public class MedianeComparator implements Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) {
            return (int)(e1.calculMoyenne() - e2.calculMoyenne());
        }
    }

    public class idComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return (int)(e1.getId() - e2.getId());}
    }

    public class nomComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return e1.getNom().compareTo(e2.getNom());}
    }

    public class prenomComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return e1.getPrenom().compareTo(e2.getPrenom());}
    }

    /**
     * Classe les élèves de la promotion par le prénom
     * @param croissant si à Vrai, les élèves seront triés dans un ordre croissant
     **/
    public void triPrenom(boolean croissant)
    {
        Collections.sort(getEleves(),new prenomComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    /**
     * Classe les élèves de la promotion par le nom
     * @param croissant si à Vrai, les élèves seront triés dans un ordre croissant
     **/
    public void triNom(boolean croissant)
    {
        Collections.sort(getEleves(),new nomComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    /**
     * Classe les élèves de la promotion par leur identifiant
     * @param croissant si à Vrai, les élèves seront triés dans un ordre croissant
     **/
    public void triId(boolean croissant)
    {
        Collections.sort(getEleves(),new idComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }
    
    /**
     * Classe les élèves de la promotion par leur médiane
     * @param croissant si à Vrai, les élèves seront triés dans un ordre croissant
     **/
    public void triMediane(boolean croissant)
    {
        Collections.sort(getEleves(),new MedianeComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    /**
     * Classe les élèves de la promotion par leur moyenne
     * @param croissant si à Vrai, les élèves seront triés dans un ordre croissant
     **/
    public void triMoyenne(boolean croissant)
    {   
        Collections.sort(getEleves(),new MoyenneComparator());
           if(!croissant)
            Collections.reverse(getEleves());
    }

    /**
     * Recherche une promotion par nom et retourne null si rien est trouvé
     * @param nom le nom de la promotion à chercher
     * @return l'objet Promotion
     */
    public static Promotion trouverPromotion(String nom)
    {
        nom = nom.toLowerCase();
        for(Promotion p : listePromos)
            if(nom.equals(p.getNom().toLowerCase()))
                return p;
        return null;
    }
    
    /**
     * Retourne la moyenne générale de la promotion
     * @return la moyenne générale de la promotion
     */
    public float obtenirMoyennePromotion()
    {
        float moy = 0;
        for(int i = 0; i < this.getEleves().size();i++)
        {
            if(!Float.isNaN(this.getEleves().get(i).calculMoyenne()))
            {
                moy += this.getEleves().get(i).calculMoyenne();
            }
            
        }
        
        return (float)(moy/this.getEleves().size());
    }
     
    /**
     * Retourne la moyenne minimale de la promotion
     * @return la moyenne minimale de la promotion
     */
    public float obtenirMinimumPromotion()
    {
        float value = 21;
        for(Eleve a : this.getEleves())
        {
            if(a.calculMoyenne() < value && !Float.isNaN(a.calculMoyenne()) )
            {
                value = a.calculMoyenne();
            }
            
        }
        
        System.out.println("Valeur : " + value);
        if(value == 21) value = 0;
        
        return value;
    }
    
     /**
     * Retourne la moyenne maximale de la promotion
     * @return la moyenne maximale de la promotion
     */        
    public float obtenirMaximumPromotion()
    {
        float value = 0;
        for(Eleve a : this.getEleves())
        {
            if(a.calculMoyenne() > value && !Float.isNaN(a.calculMoyenne()))
            {
                value = a.calculMoyenne();
            }
        }
        
        return value;
    }
}
        