/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * L'appel de cette classe crée un camembert
 * contenant les différentes appréciations pour une promotion donné
 */


public class CamembertAppreciationPromo extends JFrame {

    int[] appreciation = new int[5];
    private JPanel pan;

    public CamembertAppreciationPromo(Promotion prom) // On doit appeler la classe avec la promotion en paramètre, et elle crée automatiquement une fenetre avec le camembert
    {
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pan = new JPanel(new BorderLayout());
        setContentPane(pan);
        setSize(800, 450);

        for (Eleve a : prom.getEleves()) // Ici, on repartie le nombre d'appreciation en fonction de la moyenne de l'élève
        {
            if (a.calculMoyenne() < 10) appreciation[0]++;
            if (a.calculMoyenne() < 12) appreciation[1]++;
            if (a.calculMoyenne() < 14) appreciation[2]++;
            if (a.calculMoyenne() < 16) appreciation[3]++;
            if (a.calculMoyenne() < 18) appreciation[4]++;
        }

        DefaultPieDataset pie = new DefaultPieDataset(); // On crée un set de données et on ajoute les valeurs
        pie.setValue("Non validé (" + appreciation[0] + ")", appreciation[0]);
        pie.setValue("Validé (" + appreciation[1] + ")", appreciation[1]);
        pie.setValue("Assez bien (" + appreciation[2] + ")", appreciation[2]);
        pie.setValue("Bien (" + appreciation[3] + ")", appreciation[3]);
        pie.setValue("Très bien (" + appreciation[4] + ")", appreciation[4]);

        JFreeChart chart = ChartFactory.createPieChart("Résultat dans la promotion " + prom.getNom(), // on crée le camembert avec les données
                pie, true, true, true);

        ChartPanel cPan = new ChartPanel(chart);

        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
