package module.imageutils.imageio;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kshea12 on 3/22/16.
 */
public interface ImageIOInterface {
     BufferedImage importImage(File imageFile);
}
