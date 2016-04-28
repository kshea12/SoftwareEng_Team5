package module.imageutils;

import module.imageutils.imagefusion.ImageFusion;
import module.imageutils.imagefusion.ImageFusionInterface;
import module.imageutils.imageio.ImageIO;
import module.imageutils.imageio.ImageIOInterface;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class purpose is to provide encapsulate all image utilities such as IO and fusion into one class.
 * An imageutils object will be able to perform all functions of all developed interfaces that involve image
 * processing.
 */
public class ImageUtils implements ImageFusionInterface, ImageIOInterface {

    public BufferedImage[] fuseImages(BufferedImage image1, BufferedImage image2) { return ImageFusion.fuseImages(image1,image2); }

    public BufferedImage importImage(File imageFile) { return ImageIO.importImage(imageFile); }

    public void exportImage(BufferedImage outputImage, File outputFilePath) { ImageIO.exportImage(outputImage, outputFilePath); }

}
