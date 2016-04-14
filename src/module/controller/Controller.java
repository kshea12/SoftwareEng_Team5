package module.controller;

import module.imageutils.imagefusion.ImageFusion;
import module.imageutils.imagefusion.ImageFusionInterface;
import module.imageutils.imageio.ImageIO;
import module.imageutils.imageio.ImageIOInterface;
import module.imageutils.imageresizing.ImageResizingInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kshea12 on 3/22/16.
 */
public class Controller {

    private BufferedImage image1;
    private BufferedImage image2;

    public Controller()
    {
        image1 = null;
        image2 = null;
    };

    public void getImage1(File image) {
        image1 = ImageIO.importImage(image);
    }

    public void getImage2(File image) {
        image2 = ImageIO.importImage(image);
    }

    public void fuseImages() {
        ImageFusion.fuseImages(image1, image2);
    }

}
