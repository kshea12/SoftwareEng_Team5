package module.imageutils;

import module.imageutils.imagefusion.ImageFusion;
import module.imageutils.imagefusion.ImageFusionInterface;
import module.imageutils.imageio.ImageIO;
import module.imageutils.imageio.ImageIOInterface;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kshea12 on 4/14/16.
 */
public class ImageUtils implements ImageFusionInterface, ImageIOInterface {

    public void fuseImages(BufferedImage image1, BufferedImage image2)
    {
        ImageFusion.fuseImages(image1,image2);
    }

    public BufferedImage importImage(File imageFile)
    {
        return ImageIO.importImage(imageFile);
    }

}
