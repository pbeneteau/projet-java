/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
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

/**
 * Ce diagramme en baton compare les resultats d'un élève comparé à sa promotion
 */
public class DiagrammeBatonResultatEleve extends JFrame {

    private JPanel pan;

    public DiagrammeBatonResultatEleve(Eleve a) {
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

        for (Evaluation e : a.getEvaluations()) {
            dataset.addValue(e.getMat().noteMin, "Minimum", e.getMat().getNom());
            dataset.addValue(e.getMat().moyenne, "Moyenne", e.getMat().getNom());
            dataset.addValue(e.getNote(), "Note de l'élève", e.getMat().getNom());
            dataset.addValue(e.getMat().noteMax, "Maximum", e.getMat().getNom());
        }

        JFreeChart res = ChartFactory.createBarChart("Resultat de " + a.getNom() + " " + a.getPrenom() + " comparé aux autres de la promotion  " + a.getPromotion().getNom()
                , "Matières", "Note", dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) res.getPlot();

        org.jfree.chart.axis.ValueAxis rageAxis = plot.getRangeAxis();
        rageAxis.setRange(new Range(0, 20));

        ChartPanel cPan = new ChartPanel(res);

        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
