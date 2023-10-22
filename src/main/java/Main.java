import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
            System.out.println("***********==Menu==***********");
            System.out.println("1) Créer un QR Code");
            System.out.println("2) Éditer un QR Code");
            System.out.println("3) Supprimer un QR Code");
            System.out.println("4) Archiver les QR Codes");
            System.out.println("0) Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createQRCode(scanner);
                    break;
                case 2:
                    editQRCode(scanner);
                    break;
                case 3:
                    deleteQRCode(scanner);
                    break;
                case 4:
                    archiveQRCodes();
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
        String filePath = "src/main/resources/imagesQR" + nom + ".png";

        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, size, size);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

        MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
        System.out.println(filePath);
    }

    public static void editQRCode(Scanner scanner) throws IOException {
        System.out.print("Enter le nom du fichier à éditer: ");
        String fileName = scanner.nextLine();

        // on peut modifier le path
        String filePath = "src/main/resources/imagesQR" + fileName;

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }

        System.out.print("Enter le nouveau contenu du fichier: ");
        String newContent = scanner.nextLine();

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(newContent.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

        System.out.println("Fichier modifié avec succès.");
    }

    public static void deleteQRCode(Scanner scanner) throws IOException {
        System.out.print("Enter le nom du fichier à supprimer: ");
        String fileName = scanner.nextLine();

        // on peut modifier le path
        String filePath = "src/main/resources/imagesQR" + fileName;

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Le fichier n'existe pas.");
            return;
        }

        if (file.delete()) {
            System.out.println("Fichier supprimé avec succès.");
        } else {
            System.out.println("Impossible de supprimer le fichier.");
        }
    }

    public static void archiveQRCodes() throws IOException {

            }
        }

