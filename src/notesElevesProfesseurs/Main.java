
package notesElevesProfesseurs;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
       
        try {
            
            // Chargement des données du programme via les fichiers CSV
            CSV_Loader.start("data/élèves.csv", "data/Résultats élèves.csv");

            // Affiche le menu principal en interface console (VERSION 2 du rendu)
            Menu m = new Menu();
            m.afficherAccueil();
                        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
