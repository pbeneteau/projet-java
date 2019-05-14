package graphique;


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.*;

/*
* Cette classe génère plein de graphique, surtout utile pour les test de la V3
*/
public class GraphiqueTest { 

    public static void main(String[] args) {
     
          try {
            CSV_Loader.start("Data\\élèves.csv", "Data\\Résultats élèves.csv");
            
            
            for(Promotion a : Promotion.getListePromos())
            {
                CamembertAppreciationPromo b = new CamembertAppreciationPromo(a);
            }
            
            CamembertNombreEleveParPromo c = new CamembertNombreEleveParPromo(Promotion.getListePromos());
           
            DiagrammeBatonNoteMatiere d = new DiagrammeBatonNoteMatiere(Promotion.rechercherElevePartout(2).getEvaluations().get(1).getMat());
            DiagrammeBatonResultatEleve res = new DiagrammeBatonResultatEleve(Promotion.rechercherElevePartout(1));
            DiagrammeBatonMoyennePromotion prom = new DiagrammeBatonMoyennePromotion(Promotion.getListePromos());
            DiagrammeBatonStatPromotion prom2 = new DiagrammeBatonStatPromotion(Promotion.getListePromos());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}           
    
    
