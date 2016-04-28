package module.imageutils.imageio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The purpose of this class is provide imageIO services for the software.
 */
public class ImageIO {

    public static BufferedImage importImage(File imageFile)
    {
        BufferedImage image = null;
        try {
            image = javax.imageio.ImageIO.read(imageFile);
        }

        catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public static void exportImage(BufferedImage outputImage, File outputFilePath)
    {
        try {
            javax.imageio.ImageIO.write(outputImage, "jpg", outputFilePath);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

