package text_2_pdf;
 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.Scanner;
 public class TextToPDF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask the user to input the text file path
            System.out.print("Enter The TEXT_FILE full path:");
            String inputFilePath = scanner.nextLine();
            
            // Validate if the file exists
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists() || inputFile.isDirectory()) {
                System.out.println("OOPS ! Sorry ! SOMETHING WENT WRONG !");
                return;
            }

            // Predefined folder for storing PDFs
            String outputFolder = "Generated_PDFs";
            File folder = new File(outputFolder);

            // Create the folder if it does not exist
            if (!folder.exists()) {
                folder.mkdir();
                System.out.println("Folder 'Generated_PDFs' created.");
            }

            // Ask the user to input the desired PDF name
            System.out.print("Enter THE PDF file name (without extension): ");
            String outputFileName = scanner.nextLine();
 
            // Validate the entered name
            if (outputFileName.trim().isEmpty()) {
                System.out.println("Error: Invalid PDF name.");
                return;
            }

            // Construct the full path for the PDF
            String outputFilePath = outputFolder + File.separator + outputFileName + ".pdf";

            // Read the text file
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;

            // Create the PDF document
            Document pdfDocument = new Document();
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(outputFilePath));
            pdfDocument.open();

            // Write content to the PDF
            while ((line = br.readLine()) != null) {
                pdfDocument.add(new Paragraph(line));
            }

            // Close resources
            pdfDocument.close();
            br.close();

            // Completion message
            System.out.println("PDF created successfully At: " + outputFilePath);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
