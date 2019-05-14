/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import notesElevesProfesseurs.*;


/**
 * @author Paul BENETEAU & Marc-Antoine Bock
 */
public class Globals {

    // Ce fichier représente les variables globales

    /**
     * Promotion actuelle
     */
    public static Promotion currentPromotion = null;

    /**
     * Eleve sélectionné
     */
    public static Eleve selectedStudent = null;

    /**
     * Ordre de tri par défaut des élèves ( Vrai : tri , croissant Faux: tri décroissant)
     */
    public static boolean ascendingSort = true;

    /**
     * Mode de tri par défaut des élèves d'une promotion
     */
    public static TriEleves modeTriParDefaut = TriEleves.identifiant;

    /**
     * Evaluation sélectionnée
     */
    public static Evaluation evaluationSelectionnee = null;
    public static Matiere evalMat = null;
}
