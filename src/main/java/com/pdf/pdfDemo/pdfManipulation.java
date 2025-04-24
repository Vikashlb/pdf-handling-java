package com.pdf.pdfDemo;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class pdfManipulation {

    public static void main(String[] args) throws IOException {
        pdfManipulation obj = new pdfManipulation();
        Scanner sc = new Scanner(System.in);
        int usrChoice = 1;
        do {
            System.out.println("PDF Handling in Java :- ");
            System.out.println("1. Create a empty PDF!");
            System.out.println("2. Load PDF and add pages!");
            System.out.println("3. Add text in PDF!");
            System.out.println("4. Get all text from PDF!");
            System.out.print("Enter the desired option number :-");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    obj.emptyPdf();
                    break;
                case 2:
                    obj.loadExistingPdf();
                    break;
                case 3:
                    obj.addTextInPdf();
                    break;
                case 4:
                    obj.getAllText();
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
            System.out.println("Do you want to continue (1 = Yes, 0 = No) ");
            usrChoice = sc.nextInt();
        }while (usrChoice == 1);
        System.out.println("Program Exited!");
    }

    void emptyPdf() throws IOException {
        PDDocument document = new PDDocument();
        PDPage  firstPage = new PDPage();

        document.addPage(firstPage);

        document.save("emptyPdf.pdf");

        System.out.println("PDF successfully created :- 'emptyPdf.pdf' ");

        document.close();
    }
    void loadExistingPdf() throws IOException {
        File file = new File("samplePdf.pdf");
        PDDocument document = Loader.loadPDF(file);

        for (int i = 0; i<=3; i++) {
            PDPage pagesA4 = new PDPage(PDRectangle.A4);
            document.addPage(pagesA4);
        }
        document.save("loadExistingPdf.pdf");
        document.close();
        System.out.println("PDF successfully created :- 'loadExistingPdf.pdf' ");
    }
    void addTextInPdf() throws IOException {
        File file = new File("samplePdf.pdf");
        PDDocument document = Loader.loadPDF(file);

        PDPage page = document.getPage(0);

        PDPageContentStream content = new PDPageContentStream(document, page);

        content.beginText();
        content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
        content.newLineAtOffset(25,500);

        String text = "This is inserted through library!";

        content.showText(text);
        content.endText();
        System.out.println("Content Added");
    }
    void getAllText() throws IOException {
        File file = new File("samplePdf.pdf");
        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String textFromPdf = pdfTextStripper.getText(document);
        document.close();

        System.out.println(textFromPdf);
    }
}
