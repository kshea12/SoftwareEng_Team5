package module.controller;

import module.imageutils.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The controller's purpose is provide an interface between the view (GUI) and the model (image utilities).
 * The user interacts with the GUI which sends a signal to the controller which has access to image utilities
 * which includes services like I/O and fusion. This software follows the MVC design pattern.
 * Created by kshea12 on 3/22/16.
 */
public class Controller {

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage fusedImage;
    private ImageUtils imageUtils;

    public Controller()
    {
        image1 = null;
        image2 = null;
        fusedImage = null;
        imageUtils = new ImageUtils();
    };

    public void getImage1(File image) { image1 = imageUtils.importImage(image); }

    public void getImage2(File image) { image2 = imageUtils.importImage(image); }

    public void fuseImages() {
        if (image1 != null && image2 != null)
            fusedImage = imageUtils.fuseImages(image1, image2);
    }

    public void saveImage(File selectedPath) {
        if (fusedImage != null)
            imageUtils.exportImage(fusedImage, selectedPath);
    }
}
