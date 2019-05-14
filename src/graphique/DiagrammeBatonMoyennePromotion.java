/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import notesElevesProfesseurs.Promotion;
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
import java.util.ArrayList;

/**
 * Cette classe va créer un diagramme en baton,
 * avec la moyenne de toute les promotions
 */
public class DiagrammeBatonMoyennePromotion extends JFrame {

    private JPanel pan;


    public DiagrammeBatonMoyennePromotion(ArrayList<Promotion> listePromos) // Le constructeur prends donc en paramètre toutes les promotions
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

        for (Promotion p : listePromos) {
            dataset.addValue(p.obtenirMoyennePromotion(), "Note moyenne", "Promo " + p.getNom()); // On ajoute la moyenne a chaque set de données
        }


        JFreeChart res = ChartFactory.createBarChart("Moyenne des promotions : ", "",
                "Note Obtenue", dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) res.getPlot();

        org.jfree.chart.axis.ValueAxis rageAxis = plot.getRangeAxis();
        rageAxis.setRange(new Range(0, 20)); // On s'assure que on est bien entre 0 et 20

        ChartPanel cPan = new ChartPanel(res);

        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
