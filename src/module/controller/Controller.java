package module.controller;

import module.imageutils.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kshea12 on 3/22/16.
 */
public class Controller {

    private BufferedImage image1;
    private BufferedImage image2;
    private ImageUtils imageUtils;

    public Controller()
    {
        image1 = null;
        image2 = null;
        imageUtils = new ImageUtils();
    };

    public void getImage1(File image) {
        image1 = imageUtils.importImage(image);
    }

    public void getImage2(File image) {
        image2 = imageUtils.importImage(image);
    }

    public void fuseImages() {
        if (image1 != null && image2 != null)
            imageUtils.fuseImages(image1, image2);
        else
            ;//handle error by returning a bool
    }

}
