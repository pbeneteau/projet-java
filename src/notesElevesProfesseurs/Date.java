package notesElevesProfesseurs;
/*
    Classe pour les dates avec l'année, le mois et le jour, utilisé pour notamment la date de naissance d'un élève
 */
public class Date {
    private int annee;
    private int mois;
    private int jour;

    public Date(){};

    public Date(int annee, int mois, int jour){
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }

    public int getAnnee() {
        return annee;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
    
    @Override
    public String toString()
    {
        return String.format("%d/%d/%d",getJour(),getMois(),getAnnee());
    }
}

