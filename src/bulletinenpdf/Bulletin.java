package bulletinenpdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;

import java.io.File;
import java.io.IOException;


/*
Cette classe permet de generer le bulletin en PDF d'un élève
Le bulletin sera stocké dans le dossier resultats/

*/
public class Bulletin {

    public static String DEST;
    private Eleve eleve;

    Bulletin() {

    }

    public Bulletin(Eleve a) {
        eleve = a;
        DEST = "resultats/" + eleve.getPromotion().getNom() + "/" + eleve.getId() + ".pdf";
        // On choisi ici l'endroit ou sera stocké le bulletin en fonction du nom de la promo et de l'ID de l'élève
    }

    /*
    On crée d'abord le bulletin ici
    */
    public void creerBulletin() throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        this.createPdf(DEST);
    }

    /*
    Puis, on crée le fichier en PDF
    */
    public void createPdf(String DEST) throws IOException {

        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);

        creationStructureBulletin(document);
        document.close();

    }

    /*
    On remplit ici la structure du bulletin
    */
    private void creationStructureBulletin(Document document) {
        document.add(new Paragraph(eleve.getPrenom() + "\n"));
        document.add(new Paragraph(eleve.getNom() + "\n"));
        document.add(new Paragraph("Promotion " + eleve.getPromotion().getNom() + "\n\n\n"));

        Paragraph para = new Paragraph("BULLETIN DE NOTE");
        para.setFontSize(18);
        para.setTextAlignment(TextAlignment.CENTER);

        document.add(para);

        Table table = new Table(7);

        Cell cell = new Cell(1, 7);
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.add("Résultats");
        cell.setFontSize(15);
        table.addCell(cell);

        table.addCell("Matière");
        table.addCell("Note");
        table.addCell("Moyenne Promo");
        table.addCell("Mediane Promo");
        table.addCell("Minimum Promo");
        table.addCell("Maximum Promo");
        table.addCell("Correcteur");

        ajoutNoteMatière(table);
        document.add(table);
        ajoutLigneFinal(document);
    }

    /*
    Ici, on ajoute le tableau avec toutes les notes
    */
    private void ajoutNoteMatière(Table table) {
        for (Evaluation a : eleve.getEvaluations()) {
            table.addCell(a.getMat().getNom() + " " + a.getEvalType());
            table.addCell("" + a.getNote());
            table.addCell("" + a.getMat().moyenne);
            table.addCell("" + a.getMat().mediane);
            table.addCell("" + a.getMat().noteMin);
            table.addCell("" + a.getMat().noteMax);
            table.addCell(a.getProf().getNom());
        }
    }

    /*
    Dernière ligne du bulletin, pour la moyenne générale, et 
    */
    private void ajoutLigneFinal(Document doc) {
        float value = eleve.calculMoyenne();
        doc.add(new Paragraph("\n\n"));
        String appreciation;
        if (value < 10) {
            appreciation = "Non validé";
        } else if (value < 12) {
            appreciation = "Validé";
        } else if (value < 14) {
            appreciation = "Validé mention Assez bien";
        } else if (value < 16) {
            appreciation = "Validé mention bien";
        } else {
            appreciation = "Validé mention très bien";
        }

        doc.add(new Paragraph("Moyenne générale : " + value + "  " + appreciation));

        doc.add(new Paragraph("Moyenne de la Promotion : " + String.format("%.1f", eleve.getPromotion().obtenirMoyennePromotion()) + "\n"));
        doc.add(new Paragraph("Minimum de la Promotion : " + String.format("%.1f", eleve.getPromotion().obtenirMinimumPromotion()) + "\n"));
        doc.add(new Paragraph("Maximum de la Promotion : " + String.format("%.1f", eleve.getPromotion().obtenirMaximumPromotion()) + "\n"));
    }
}
