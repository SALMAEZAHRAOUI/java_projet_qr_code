import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// Class genarateCodeQR
public class genarateCodeQR {

    private final Map<String, String> qrCodes;


    // the attributes of class
    private String data ;
    private String name;
    private final int size = 400;


    // Constructor with arguments
  public genarateCodeQR(String data , String name) {

        this.data = data;
        this.name = name;
      qrCodes = new HashMap<>();

  }
// the setter and getter
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    private void createQRCode(Scanner scanner) {
        System.out.print("Entrez le contenu du QR Code : ");
        setData(scanner.nextLine());
        System.out.print("Entrez le nom du QR Code : ");
        setName(scanner.nextLine());

        qrCodes.put(getName(), getData());

        generateQRCode(getData(), getName());
        System.out.println("QR Code créé avec succès !");


    }


    // the methods
    public void generateQRCode(String data, String name) {

        String filePath = "QR_code\\" + name + ".png";

        /*

        FileOutputStream fileOutputStream;
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(getData(), BarcodeFormat.QR_CODE, getSize(), getSize());

            fileOutputStream = new FileOutputStream(filePath);

            MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);

            fileOutputStream.close();

        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Created of QR code is succefull");
*/

        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, getSize(), getSize());

            BufferedImage image = new BufferedImage(getSize(), getSize(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < getSize(); x++) {
                for (int y = 0; y < getSize(); y++) {
                    int grayValue = bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
                    image.setRGB(x, y, grayValue);
                }
            }

            File outputFile = new File("QRcode"+name + "." + "png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("QR Code généré : " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Erreur lors de la génération du QR Code : " + e.getMessage());
        }


    }


    public void edit(Scanner scanner){

        System.out.println("Entrez le nom du QR Code à éditer : ");
        setName(scanner.nextLine());
        if (qrCodes.containsKey(getName())){
            System.out.print("Entrez le nouveau contenu du QR Code : ");
            //String newContent = scanner.nextLine();
            setData(scanner.nextLine());
            generateQRCode(getData(),getName());
            qrCodes.put(name, getData());
            System.out.println("QR Code édité avec succès !");
        } else {
            System.out.println("QR Code non trouvé.");
        }


    }
/*
    private void deleteQRCode(Scanner scanner) {
        System.out.print("Entrez le nom du QR Code à supprimer : ");
        String name = scanner.nextLine();

        if (qrCodes.containsKey(name)) {
            File("QRcode"+name + "." + "png");
            qrCodes.remove(name);
            outputFile.delete();
            System.out.println("QR Code supprimé avec succès !");
        } else {
            System.out.println("QR Code non trouvé.");
        }
    }
*/


}
