
package notesElevesProfesseurs;

import java.util.Comparator;
import java.util.HashSet;

public class Evaluation implements Comparable<Evaluation>{

    /**
     * @return the evalType
     */
    public String getEvalType() {
        return evalType;
    }

    /**
     * @param evalType the evalType to set
     */
    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }
    private float note;
    private Matiere mat = new Matiere();
    private Eleve eleve = new Eleve();
    private Professeur prof = new Professeur();
    
    //Options bonus
    private String evalType = "CE"; 
    //Le coefficient pourra être utilisé dans une possible mise à jour
    private int coeff = 1;

    public Evaluation(){};

    
    public Evaluation(float note, Matiere mat, Eleve eleve, Professeur prof){
        this.note = note;
        this.mat = mat;
        this.eleve = eleve;
        this.prof = prof;
        mat.updateData(note);
    }

    @Override
    public String toString() {
        return "(("+eleve.getNom() +" Professeur: "+prof.toString()+" Matière : "+mat.getNom()+" "+note+"/20)";
    }

    public Eleve getEleve() {
        return eleve;
    }

    public float getNote() {
        return note;
    }

    public Matiere getMat() {
        return mat;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public void setNote(float note) {
        this.note = note;
        this.mat.updateData(note);
    }

    public void setMat(Matiere mat) {
        this.mat = mat;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    @Override
    public int compareTo(Evaluation o) {
        return (int) (this.getNote() - o.getNote());
    }
}

class ComparatoNote implements Comparator<Evaluation> {
    @Override
    public int compare(Evaluation o1, Evaluation o2) {
        return (int) (o1.getNote() - o2.getNote());
    }
}