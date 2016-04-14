package module.imageutils.imagefusion;

import java.awt.*;
import java.awt.image.*;
import javax.media.jai.*;
import javax.media.jai.operator.BandMergeDescriptor;
import javax.media.jai.operator.ColorConvertDescriptor;
import javax.media.jai.operator.InvertDescriptor;
import javax.media.jai.operator.OverlayDescriptor;
import javax.media.jai.operator.SubtractDescriptor;
import javax.media.jai.operator.XorDescriptor;
import javax.swing.*;


public class ImageFusion
{


    public static void fuseImages(BufferedImage image1, BufferedImage image2)
    {
        // image1 = GrayScale(image1);
        // image2 = GrayScale(image2);

        BufferedImage result = Colorize(image1,image2);

        JFrame framej = new JFrame();
        framej.getContentPane().setLayout(new FlowLayout());
        framej.getContentPane().add(new JLabel("1"));
        framej.getContentPane().add(new JLabel("THIS"));
        framej.getContentPane().add(new JLabel(new ImageIcon(image1)));
        framej.getContentPane().add(new JLabel("COLORIZED WITH"));
        framej.getContentPane().add(new JLabel(new ImageIcon(image2)));
        framej.getContentPane().add(new JLabel("EQUALS"));
        framej.getContentPane().add(new JLabel(new ImageIcon(result)));
        framej.pack();
        framej.setVisible(true);
        framej.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graphics2D bImageGraphics = image1.createGraphics();
        //draw the Image (image) into the BufferedImage (bImage)
        bImageGraphics.drawImage(image1, null, null);
        // cast it to rendered image
        RenderedImage firstImage = (RenderedImage)image1;

        Graphics2D aImageGraphics = image2.createGraphics();
        //draw the Image (image) into the BufferedImage (bImage)
        aImageGraphics.drawImage(image2, null, null);
        // cast it to rendered image
        RenderedImage secondImage = (RenderedImage)image2;

        RenderingHints key = new RenderingHints( RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        RenderedOp subtractImages1 = SubtractDescriptor.create(firstImage, secondImage, key);
        BufferedImage rImageGraphics1 = subtractImages1.getAsBufferedImage();
        JFrame framex = new JFrame();
        framex.getContentPane().setLayout(new FlowLayout());
        framex.getContentPane().add(new JLabel("2"));
        framex.getContentPane().add(new JLabel("THIS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(image1)));
        framex.getContentPane().add(new JLabel("MINUS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(image2)));
        framex.getContentPane().add(new JLabel("EQUALS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics1)));
        framex.pack();
        framex.setVisible(true);
        framex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RenderedOp subtractImages2 = SubtractDescriptor.create(secondImage, firstImage, key);
        BufferedImage rImageGraphics2 = subtractImages2.getAsBufferedImage();
        JFrame framey = new JFrame();
        framey.getContentPane().setLayout(new FlowLayout());
        framey.getContentPane().add(new JLabel("3"));
        framey.getContentPane().add(new JLabel("THIS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(image2)));
        framey.getContentPane().add(new JLabel("MINUS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(image1)));
        framey.getContentPane().add(new JLabel("EQUALS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics2)));
        framey.pack();
        framey.setVisible(true);
        framey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* //original images xor'd
        RenderedOp mergeOriginal = XorDescriptor.create(firstImage, secondImage, key);
        BufferedImage rImageGraphicsMergeOriginal = mergeOriginal.getAsBufferedImage();
        JFrame frameq = new JFrame();
        frameq.getContentPane().setLayout(new FlowLayout());
        frameq.getContentPane().add(new JLabel("4"));
        frameq.getContentPane().add(new JLabel("THIS"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frameq.getContentPane().add(new JLabel("XOR WITH"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frameq.getContentPane().add(new JLabel("EQUALS"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeOriginal)));
        frameq.pack();
        frameq.setVisible(true);
        frameq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */

        //xor
        RenderedOp mergeSubtractions = XorDescriptor.create(subtractImages1, subtractImages2, key);
        //RenderedOp coloredSubs = ColorConvertDescriptor.create((RenderedImage)mergeSubtractions,firstImage.getColorModel(),null);
        //BufferedImage rImageGraphicsMergeSubtractions = coloredSubs.getAsBufferedImage();
        BufferedImage rImageGraphicsMergeSubtractions = mergeSubtractions.getAsBufferedImage();
        //rImageGraphicsMergeSubtractions = Colorize(image1,image2,rImageGraphicsMergeSubtractions);

        JFrame framez = new JFrame();
        framez.getContentPane().setLayout(new FlowLayout());
        framez.getContentPane().add(new JLabel("FINAL"));
        framez.getContentPane().add(new JLabel("THIS"));
        framez.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics1)));
        framez.getContentPane().add(new JLabel("XOR WITH"));
        framez.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics2)));
        framez.getContentPane().add(new JLabel("EQUALS"));
        framez.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeSubtractions)));
        framez.pack();
        framez.setVisible(true);
        framez.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //xor
       /* RenderedOp mergeSubtractions2 = XorDescriptor.create(mergeSubtractions, mergeOriginal, key);
        BufferedImage rImageGraphicsMergeSubtractions2 = mergeSubtractions2.getAsBufferedImage();
        JFrame framek = new JFrame();
        framek.getContentPane().setLayout(new FlowLayout());
        framek.getContentPane().add(new JLabel("THIS"));
        framek.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeSubtractions)));
        framek.getContentPane().add(new JLabel("XOR WITH"));
        framek.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeOriginal)));
        framek.getContentPane().add(new JLabel("EQUALS"));
        framek.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeSubtractions2)));
        framek.pack();
        framek.setVisible(true);
        framek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    public static ColorModel findColorModel(BufferedImage src)
    {
        if (src instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) src;
            System.out.println(bimage.getColorModel().getNumColorComponents());
        }

        PixelGrabber pg = new PixelGrabber(src, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }
        ColorModel cm = pg.getColorModel();
        System.out.println(cm.getNumColorComponents());
        return cm;
    }

    public static BufferedImage GrayScale(BufferedImage image)
    {
        try {
            int width = image.getWidth();
            int height = image.getHeight();

            for(int i=0; i<height; i++){

                for(int j=0; j<width; j++){

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,

                            red+green+blue,red+green+blue);

                    image.setRGB(j,i,newColor.getRGB());
                }
            }

            return image;

        } catch (Exception e) {}
        return null;
    }

    public static BufferedImage Colorize(BufferedImage colorReference1,BufferedImage colorReference2)
    {
        int width = colorReference1.getWidth();
        int height = colorReference1.getHeight();
        BufferedImage colorized = new BufferedImage(width,height,colorReference1.getType());

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
                red1 = ((red1-red2)/2)+red2;
                green1 = ((green1-green2)/2)+green2;
                blue1 = ((blue1-blue2)/2)+blue2;
                Color newColor = new Color(red1,green1,blue1);

                colorized.setRGB(j,i,newColor.getRGB());

                // 60, 67     60-67= -7     -7/2 = -4    -4+67 = 63
                // 67, 60     67-60= 7      7/2 = 4       4 + 60 = 64
            }
        }

        return colorized;
    }

}

