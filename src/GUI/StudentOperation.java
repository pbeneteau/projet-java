package GUI;

import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author Paul BENETEAU & Marc-Antoine Bock
 */
public class StudentOperation {

    private void emptyTable(JTable table) {

        DefaultTableModel model = ((DefaultTableModel) table.getModel());
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) // On enlève toutes les lignes en partant de la fin
        {
            model.removeRow(i);
            System.out.println("Suppression ligne " + i);
        }
    }

    /**
     * @param e
     * @param evalTable
     */
    void displayStudentsEvaluations(Eleve e, JTable evalTable) {

        emptyTable(evalTable);
        System.out.println("Début d'affichage des évaluations");
        DefaultTableModel model = new DefaultTableModel();
        Object[] columns = {"Note", "Matière", "Correcteur", "Type"};
        model.setColumnIdentifiers(columns);
        Object[] rows = new Object[evalTable.getColumnCount()]; // par défaut 4 colonnes

        for (Evaluation eval : e.getEvaluations()) {
            rows[0] = eval.getNote();
            rows[1] = eval.getMat().getNom();
            rows[2] = eval.getProf().getNom();
            rows[3] = eval.getEvalType();
            model.addRow(rows);
            System.out.println("Ajout de " + eval);
        }
        evalTable.setModel(model);
        evalTable.repaint();
        System.out.println("Terminé!");
    }
}
