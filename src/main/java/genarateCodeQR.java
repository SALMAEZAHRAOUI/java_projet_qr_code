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


    //Create QR code
    public void createQRCode() {

      genarateQR(getName(), getData());

      System.out.println("QR Code créé avec succès !");

    }
    // this method is for generate a code QR
    public void genarateQR(String name , String data){

        String filePath = "QR_code\\" + name + ".png";

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

            File outputFile = new File(filePath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("QR Code généré : " + outputFile.getAbsolutePath());
            qrCodes.put(getName(), outputFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erreur lors de la génération du QR Code : " + e.getMessage());
        }

    }


    //Modify a QR code
    public void edit(Scanner scanner){

        if (qrCodes.containsKey(getName())){
            System.out.print("Entrez le nouveau contenu du QR Code : ");

            setData(scanner.nextLine());
            genarateQR(getName(), getData());

            System.out.println("QR Code édité avec succès !");
        } else {
            System.out.println("QR Code non trouvé.");
        }


    }


    //Delete a QR code
    public void deleteQRCode(String name) {


        if (qrCodes.containsKey(name)) {

            String filePath = "QR_code\\" + name + ".png"; // Specify the path to the file you want to delete

            File file = new File(filePath);

            //delete the file exists deleted then delete the QR ccode 
            if (file.delete()) {
                    System.out.println("QR Code supprimé avec succès !");
                qrCodes.remove(name);
            }
            else {
                    System.err.println("Échec de la suppression du QR Code.");
            }

        } 
        //otherwise the file does not exist
        else {
            System.out.println("QR Code non trouvé.");
        }
    }

    Archive QR codes
    public void archiveQRCodes(){

        for (Map.Entry<String, String> entry : qrCodes.entrySet()) {

            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }


}
