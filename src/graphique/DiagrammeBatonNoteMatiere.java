/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import notesElevesProfesseurs.Matiere;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import javafx.scene.chart.ValueAxis;

/**
 * Cette classe va calculer le nombre de personne ayant obtenue une note dans une des matières
 */
public class DiagrammeBatonNoteMatiere extends JFrame {


    int[] val = new int[21];
    private JPanel pan;

    public DiagrammeBatonNoteMatiere(Matiere m) // On doit donc passer la matière en paramètre
    {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        pan = new JPanel(new BorderLayout());
        setContentPane(pan);
        setSize(1000, 700);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        for (int i = 0; i < m.allNote.size(); i++) {
            val[Math.round(m.allNote.get(i))]++;  // On vérifie la note obtenue par chacun des élèves, puis l'on incrémente un tableau
        }

        for (int i = 0; i <= 20; i++) {
            dataset.addValue(val[i], "Nombre d'élève", "" + i); // On ajoute les data
        }

        JFreeChart res = ChartFactory.createBarChart("Note pour la matière : " + m.getNom() + "  (Promotion " + m.getNomPromo() + " )", "Note Obtenue",
                "Nombre d'élève", dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) res.getPlot();

        org.jfree.chart.axis.ValueAxis rageAxis = plot.getRangeAxis();
        rageAxis.setRange(new Range(0, 10));

        ChartPanel cPan = new ChartPanel(res);

        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
