import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            /*voici le menu */
            System.out.println(" ==== Menu =============== ");
                    System.out.println("1. Creer un QR Code");
            System.out.println("2. Éditer un QR Code");
            System.out.println("3. Supprimer un QR Code");
            System.out.println("4. Archiver les QR Codes");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();

            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    //createQRCode(scanner);
                    break;
                case 2:
                    //editQRCode(scanner);
                    break;
                case 3:
                    //deleteQRCode(scanner);
                    break;
                case 4:
                    //archiveQRCodes();
                    break;
                case 0:
                    exit = true;
                    System.out.println("aurevoir ");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez reessayer.");
                    break;
            }

        System.out.println();


    }

}

}