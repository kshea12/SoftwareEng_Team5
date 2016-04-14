package module.imageutils.imageio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kshea12 on 3/22/16.
 */
public class ImageIO {

    public static BufferedImage importImage(File imageFile)
    {
        BufferedImage image = null;
        try {
            image = javax.imageio.ImageIO.read(imageFile);
        }

        catch (IOException e){
            //handle error
        }

        return image;
    }
}
