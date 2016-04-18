package imagefusion;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.event.*;

/**
 * This is another image fusion algorithm that was being tested. It currently is not being used.
 * The algorithm involves overlapping two images and weighting their pixel color values to create a fused image.
 * Source: http://www.informit.com/articles/article.aspx?p=1245201&seqNum=2
 *
 * http://www.informit.com/articles/article.aspx?p=1245201&seqNum=2
 */
public class TransparentOverlayFusion
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
       // BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\mri.jpg"));
       // BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\ct.jpg"));
        
       // BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 2 Picture 1.jpg"));
       // BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 2 Picture 2.jpg"));
        
        //BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 3 Picture 1.jpg"));
        //BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 3 Picture 2.jpg"));
        
       // BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 4 Picture 1.jpg"));
       // BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 4 Picture 2.jpg"));
        
        BufferedImage image1 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 5 Picture 1.jpg"));
        BufferedImage image2 = ImageIO.read(new File("C:\\Users\\Choyce\\Desktop\\Image Fusion\\Example 5 Picture 2.jpg"));
        
        BufferedImage finx = blend(image1,image2,0.1);
        BufferedImage finy = blend(image2,image1,0.1);
        BufferedImage finv = blend(finx,finy,0.5);
        JFrame framex = new JFrame();
        framex.getContentPane().setLayout(new FlowLayout());
        framex.getContentPane().add(new JLabel(new ImageIcon(finv)));
        framex.pack();
        framex.setVisible(true);
        framex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage fin = blend(image1,image2,0.1);
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel("0.1"));
        frame.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame.getContentPane().add(new JLabel(new ImageIcon(fin)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        BufferedImage fin2 = blend(image1,image2,0.2);
        JFrame frame2 = new JFrame();
        frame2.getContentPane().setLayout(new FlowLayout());
        frame2.getContentPane().add(new JLabel("0.2"));
        frame2.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame2.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame2.getContentPane().add(new JLabel(new ImageIcon(fin2)));
        frame2.pack();
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Thread.sleep(5000);
        
        BufferedImage fin3 = blend(image1,image2,0.3);
        JFrame frame3 = new JFrame();
        frame3.getContentPane().setLayout(new FlowLayout());
        frame3.getContentPane().add(new JLabel("0.3"));
        frame3.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame3.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame3.getContentPane().add(new JLabel(new ImageIcon(fin3)));
        frame3.pack();
        frame3.setVisible(true);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage fin4 = blend(image1,image2,0.4);
        JFrame frame4 = new JFrame();
        frame4.getContentPane().setLayout(new FlowLayout());
        frame4.getContentPane().add(new JLabel("0.4"));
        frame4.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame4.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame4.getContentPane().add(new JLabel(new ImageIcon(fin3)));
        frame4.pack();
        frame4.setVisible(true);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage fin5 = blend(image1,image2,0.5);
        JFrame frame5 = new JFrame();
        frame5.getContentPane().setLayout(new FlowLayout());
        frame5.getContentPane().add(new JLabel("0.5"));
        frame5.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame5.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame5.getContentPane().add(new JLabel(new ImageIcon(fin3)));
        frame5.pack();
        frame5.setVisible(true);
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage fin6 = blend(image1,image2,0.6);
        JFrame frame6 = new JFrame();
        frame6.getContentPane().setLayout(new FlowLayout());
        frame6.getContentPane().add(new JLabel("0.6"));
        frame6.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame6.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame6.getContentPane().add(new JLabel(new ImageIcon(fin)));
        frame6.pack();
        frame6.setVisible(true);
        frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // Thread.sleep(5000);
        
        BufferedImage fin7 = blend(image1,image2,0.7);
        JFrame frame7 = new JFrame();
        frame7.getContentPane().setLayout(new FlowLayout());
        frame7.getContentPane().add(new JLabel("0.7"));
        frame7.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame7.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame7.getContentPane().add(new JLabel(new ImageIcon(fin)));
        frame7.pack();
        frame7.setVisible(true);
        frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // Thread.sleep(5000);
        
        BufferedImage fin8 = blend(image1,image2,0.8);
        JFrame frame8 = new JFrame();
        frame8.getContentPane().setLayout(new FlowLayout());
        frame8.getContentPane().add(new JLabel("0.8"));
        frame8.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame8.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame8.getContentPane().add(new JLabel(new ImageIcon(fin)));
        frame8.pack();
        frame8.setVisible(true);
        frame8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Thread.sleep(5000);
        
        BufferedImage fin9 = blend(image1,image2,0.9);
        JFrame frame9 = new JFrame();
        frame9.getContentPane().setLayout(new FlowLayout());
        frame9.getContentPane().add(new JLabel("0.9"));
        frame9.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame9.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame9.getContentPane().add(new JLabel(new ImageIcon(fin)));
        frame9.pack();
        frame9.setVisible(true);
        frame9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // Thread.sleep(5000);
        
        BufferedImage merge1 = blend(fin,fin9,0.5);
        BufferedImage merge2 = blend(fin2,fin8,0.5);
        BufferedImage merge3 = blend(fin3,fin7,0.5);
        BufferedImage merge4 = blend(fin4,fin6,0.5);
        BufferedImage merge5 = blend(merge1,merge4,0.5);
        BufferedImage merge6 = blend(merge2,merge3,0.5);
        BufferedImage merge7 = blend(merge5,merge6,0.5);
        
        JFrame frame99 = new JFrame();
        frame99.getContentPane().setLayout(new FlowLayout());
        frame99.getContentPane().add(new JLabel("Final Merge of Merges"));
        frame99.getContentPane().add(new JLabel(new ImageIcon(merge7)));;
        frame99.pack();
        frame99.setVisible(true);
        frame99.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static BufferedImage blend (BufferedImage bi1, BufferedImage bi2, double weight)
    {
        if (bi1 == null)
            throw new NullPointerException ("bi1 is null");

    if (bi2 == null)
        throw new NullPointerException ("bi2 is null");

    int width = bi1.getWidth ();
    if (width != bi2.getWidth ())
        throw new IllegalArgumentException ("widths not equal");

    int height = bi1.getHeight ();
    if (height != bi2.getHeight ())
        throw new IllegalArgumentException ("heights not equal");

    BufferedImage bi3 = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = bi3.createGraphics ();
    g2d.drawImage (bi1, null, 0, 0);
    g2d.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER,(float) (1.0-weight)));
    g2d.drawImage (bi2, null, 0, 0);
    g2d.dispose ();

    return bi3;
    }
    
}


