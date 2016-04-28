package module.controller;

import module.imageutils.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The controller's purpose is provide an interface between the view (GUI) and the model (image utilities).
 * The user interacts with the GUI which sends a signal to the controller which has access to image utilities
 * which includes services like I/O and fusion. This software follows the MVC design pattern.
 */
public class Controller {

    private BufferedImage image1;
    private BufferedImage image2;
    private ImageUtils imageUtils;

    public Controller() {
        image1 = null;
        image2 = null;
        imageUtils = new ImageUtils();
    }

    public void getImage1(File image) { image1 = imageUtils.importImage(image); }

    public void getImage2(File image) { image2 = imageUtils.importImage(image); }

    public BufferedImage[] fuseImages() {
        if (image1 != null && image2 != null)
            return imageUtils.fuseImages(image1, image2);
        return null;
    }

    public void saveImage(BufferedImage imageToSave, File selectedPath) {
            imageUtils.exportImage(imageToSave, selectedPath);
    }
}
