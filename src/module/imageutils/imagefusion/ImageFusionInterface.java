package module.imageutils.imagefusion;

import java.awt.image.BufferedImage;

/**
 * Created by kshea12 on 3/22/16.
 * This class's purpose is to define the requirements for an ImageFusion class.
 * Any class that implements this interface should be able to fuse two images together.
 * This interface provides an easy way to extend upon the image fusion technology we have created.
 * It allows for multiple algorithms and implementations of image fusion to be added or used in this software
 * without having to change or replace other parts of the software.
 */
public interface ImageFusionInterface {

    BufferedImage[] fuseImages(BufferedImage image1, BufferedImage image2);

}
