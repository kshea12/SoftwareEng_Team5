package module.imageutils.imagefusion;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
* This class provides one algorithm we have implemented to fuse two images together. This specific algorithm
* takes the RGB pixel value from two images, finds the median of those two pixels and then uses that as the RGB
* value of the corresponding pixel in the fused image. This algorithm could theorehtically be expanded upon to
* be able to work with more than two images should that be required.
*/
public class ImageFusion
{

    /**
	 * fuse images takes two images as input and displays a fused image that is created with the colorize method.
	 * @param image1 the first image you wish to fuse
	 * @param image2 the second image you wish to fuse
     * @return the final fused image
	 */
    public static BufferedImage[] fuseImages(BufferedImage image1, BufferedImage image2)
    {
        BufferedImage[] images = new BufferedImage[4];
        images[0] = image1;
        images[1] = image2;
        images[2] = colorize(image1, image2);
        images[3] = highlight(image1, image2);

        return images;
    }

    /**
     * colorize takes two images as input and returns a colorized image that is constructed by finding the median RGB value
     * between a pixel in the first image and a pixel in the same location in pixel two. That median RGB value is then stored
     * as the RGB value of the corresponding pixel in the colorized image. The colorized image is returned after it is finished.
     * @param colorReference1 the first image to be colorized
     * @param colorReference2 the second image to be colorized
     * @return a colorized image created as explained by the description for this method
     */
    private static BufferedImage colorize(BufferedImage colorReference1,BufferedImage colorReference2)
    {
        int width = colorReference1.getWidth();
        int height = colorReference1.getHeight();
        BufferedImage colorized = new BufferedImage(width,height,colorReference1.getType());

        for(int i=0; i<height; i++)
        {
            for(int j=0; j<width; j++)
            {
                Color c1 = new Color(colorReference1.getRGB(j, i));
                int red1 = c1.getRed();
                int green1 = c1.getGreen();
                int blue1 = c1.getBlue();

                Color c2 = new Color(colorReference2.getRGB(j, i));
                int red2 = c2.getRed();
                int green2 = c2.getGreen();
                int blue2 = c2.getBlue();

                red1 = ((red1 - red2)/2) + red2;
                green1 = ((green1 - green2)/2) + green2;
                blue1 = ((blue1 - blue2)/2) + blue2;

                Color newColor = new Color(red1,green1,blue1);
                colorized.setRGB(j,i,newColor.getRGB());
            }
        }
        return colorized;
    }

    /**
     * highlight takes two images as input and returns a fused highlighted image that is constructed by finding the
     * difference between the RGB values of a pixel in the first image and a pixel in the same location in image two.
     * If the difference is above a certain threshold, the RGB value of the corresponding pixel in the highlighted image
     * is set to pure red to stand out and highlight the major differences between image one and image two.
     * The highlighted image is returned after it is finished.
     * @param colorReference1 the first image to be highlighted
     * @param colorReference2 the second image to be highlighted
     * @return a highlighted image created as explained by the description for this method
     */
    private static BufferedImage highlight(BufferedImage colorReference1, BufferedImage colorReference2)
    {
        int width = colorReference1.getWidth();
        int height = colorReference1.getHeight();
        BufferedImage highlighted = new BufferedImage(width,height,colorReference1.getType());

        for(int i=0; i<height; i++){

            for(int j=0; j<width; j++){

                Color c1 = new Color(colorReference1.getRGB(j, i));
                int red1 = c1.getRed();
                int green1 = c1.getGreen();
                int blue1 = c1.getBlue();

                Color c2 = new Color(colorReference2.getRGB(j, i));
                int red2 = c2.getRed();
                int green2 = c2.getGreen();
                int blue2 = c2.getBlue();

                int redDiff = Math.abs(red1 - red2);
                int redMedian = ((red1 - red2)/2) + red2;
                int red = (redDiff > 100) ? 255 : redMedian;

                int greenDiff = Math.abs(green1 - green2);
                int greenMedian = ((green1 - green2)/2) + green2;
                int green = (greenDiff > 100) ? 0 : greenMedian;

                int blueDiff = Math.abs(blue1 - blue2);
                int blueMedian = ((blue1 - blue2)/2) + blue2;
                int blue = (blueDiff > 100) ? 0 : blueMedian;

                Color newColor = new Color(red,green,blue);
                highlighted.setRGB(j,i,newColor.getRGB());
            }
        }
        return highlighted;
    }
}

