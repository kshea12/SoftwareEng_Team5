package module.imageutils.imageresizing;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

/**
 * Image Resizer class
 * Rescales a given image to specification.
 * Maintains maximum quality.
 * @author johnn_000
 *
 */
public class ImageResizing {

	/**
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		File imageForResize = new File("C:/Users/johnn_000/Desktop/FrogRide.jpg");

		//Read the given image as a buffered image
		BufferedImage image = read(imageForResize);

		//Resize the image to
		image = resize(image);

//

		//Save image to specified path and filename
		write(image);
	}

	/**
	 * Takes the given image file and returns it as a buffered image.
	 *
	 * @param imageForResize Given file for resize
	 * @return file as buffered image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static BufferedImage read(File imageForResize) throws IOException {
		return ImageIO.read(imageForResize);
	}

	/**
	 * Will Resize image based on imgScalr Specifications
	 *
	 * @param image temp image to be resize
	 * @return image the resized buffered image
	 */
	public static  BufferedImage resize(BufferedImage image) {
		//Scalr.resize(image, Method.ULTRA_QUALITY, 2000, Scalr.OP_ANTIALIAS);

		// (source, scalingMethod(scaling algorithm used),
		//  target width, target height)
		//http://javadox.com/org.imgscalr/imgscalr-lib/4.2/index-all.html
		//

		// This will auto adjust image width and height to priority.
		// image= Scalr.resize(image, Method.ULTRA_QUALITY, 1000,1000);

		// This will adjust image to exact width and height
		image= Scalr.resize(image, Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 1000,1000);
		return image;
	}


	/**
	 * Writes the resized to specified path and filename.
	 *
	 * @param image resized image
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws ImagingOpException the imaging op exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void write(BufferedImage image) throws IllegalArgumentException, ImagingOpException, IOException {
		ImageIO.write(image, "JPG", new File("C:/Users/johnn_000/Desktop/FrogRide_ImgScaler.jpg"));
	}

}
