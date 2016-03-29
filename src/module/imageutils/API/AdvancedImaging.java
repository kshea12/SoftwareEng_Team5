package imagefusion;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.media.jai.*;
import javax.media.jai.operator.BandMergeDescriptor;
import javax.media.jai.operator.OverlayDescriptor;
import javax.media.jai.operator.SubtractDescriptor;
import javax.media.jai.operator.XorDescriptor;

import javax.swing.*;
import javax.swing.event.*;

public class AdvancedImaging 
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 2 Picture 1.jpg"));
        BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 2 Picture 2.jpg"));
        
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
        
        RenderedOp subtractImages1 = SubtractDescriptor.create(firstImage, secondImage, null);
        BufferedImage rImageGraphics1 = subtractImages1.getAsBufferedImage(); 
        JFrame framex = new JFrame();
        framex.getContentPane().setLayout(new FlowLayout());
        framex.getContentPane().add(new JLabel("THIS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(image1)));
        framex.getContentPane().add(new JLabel("MINUS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(image2)));
        framex.getContentPane().add(new JLabel("EQUALS"));
        framex.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics1)));
        framex.pack();
        framex.setVisible(true);
        framex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        RenderedOp subtractImages2 = SubtractDescriptor.create(secondImage, firstImage, null);
        BufferedImage rImageGraphics2 = subtractImages2.getAsBufferedImage();
        JFrame framey = new JFrame();
        framey.getContentPane().setLayout(new FlowLayout());
        framey.getContentPane().add(new JLabel("THIS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(image2)));
        framey.getContentPane().add(new JLabel("MINUS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(image1)));
        framey.getContentPane().add(new JLabel("EQUALS"));
        framey.getContentPane().add(new JLabel(new ImageIcon(rImageGraphics2)));
        framey.pack();
        framey.setVisible(true);
        framey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //xor
        RenderedOp mergeOriginal = XorDescriptor.create(firstImage, secondImage, null);
        BufferedImage rImageGraphicsMergeOriginal = mergeOriginal.getAsBufferedImage();
        JFrame frameq = new JFrame();
        frameq.getContentPane().setLayout(new FlowLayout());
        frameq.getContentPane().add(new JLabel("THIS"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frameq.getContentPane().add(new JLabel("XOR WITH"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frameq.getContentPane().add(new JLabel("EQUALS"));
        frameq.getContentPane().add(new JLabel(new ImageIcon(rImageGraphicsMergeOriginal)));
        frameq.pack();
        frameq.setVisible(true);
        frameq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //xor
        RenderedOp mergeSubtractions = XorDescriptor.create(subtractImages1, subtractImages2, null);
        BufferedImage rImageGraphicsMergeSubtractions = mergeSubtractions.getAsBufferedImage();
        JFrame framez = new JFrame();
        framez.getContentPane().setLayout(new FlowLayout());
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
        RenderedOp mergeSubtractions2 = XorDescriptor.create(mergeSubtractions, mergeOriginal, null);
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
        framek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    
    }
    
}
