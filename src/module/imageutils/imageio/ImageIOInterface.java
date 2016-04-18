package module.imageutils.imageio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class's purpose is to define the requirements for an ImageIO class.
 * Any class that implements this interface should be able to import an image.
 * This interface provides an easy way to extend upon the image fusion technology we have created.
 * It allows for multiple ways of utilizing I/O without having to change or replace other parts of the software.
 * Created by kshea12 on 3/22/16.
 */
public interface ImageIOInterface {

     BufferedImage importImage(File imageFile);
     void exportImage(BufferedImage outputImage, File outputFilePath);

}
