import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        genarateCodeQR genarateCodeQR = new genarateCodeQR("www.youtube.com","youtube");
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

                    System.out.print("Entrez le contenu du QR Code : ");
                    genarateCodeQR.setData(scanner.nextLine());
                    System.out.print("Entrez le nom du QR Code : ");
                    genarateCodeQR.setName(scanner.nextLine());
                    genarateCodeQR.createQRCode();
                    break;
                case 2:
                    //editQRCode(scanner);
                    System.out.print("Entrez le nom du QR Code à éditer : ");
                    genarateCodeQR.setName(scanner.nextLine());
                    genarateCodeQR.edit(scanner);




                    break;
                case 3:
                    System.out.print("Entrez le nom du QR Code à supprimer : ");
                    String name = scanner.nextLine();
                    genarateCodeQR.deleteQRCode(name);
                    break;
                case 4:
                    genarateCodeQR.archiveQRCodes();
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