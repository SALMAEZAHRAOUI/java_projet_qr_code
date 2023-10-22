import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Main {
    public static void main(String[] args) throws IOException, WriterException {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            /*voici le menu */
            System.out.println("================Menu===============");
            System.out.println("1. Créer un QR Code");
            System.out.println("2. Éditer un QR Code");
            System.out.println("3. Supprimer un QR Code");
            System.out.println("4. Archiver les QR Codes");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createQRCode(scanner);
                    break;
                case 2:
                    // editQRCode(scanner);
                    break;
                case 3:
                    // deleteQRCode(scanner);
                    break;
                case 4:
                    // archiveQRCodes();
                    break;
                case 0:
                    exit = true;
                    System.out.println("aurevoir ");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }


    public static void createQRCode(Scanner scanner) throws WriterException, IOException {

        System.out.print("Enter le nom de siteweb: ");
        String nom = scanner.nextLine();
        System.out.print("Enter the url https://");
        String data = scanner.nextLine();


        int size = 400;

        // on peut modifier le path

        String filePath = "C:\\Users\\DELL\\Documents\\s3\\Projet_JAVA\\QR_code\\src\\main\\resources\\image\\"+ nom +".png";


        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
/*
        try {
            File file1 = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/

        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));


        MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
        System.out.println(filePath);
    }



}






