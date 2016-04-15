package module.gui;

import module.controller.Controller;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;


public class GUI {

    private Controller controller = new Controller();
	private JFrame frmImageFusion;

	JProgressBar progressBar;

	JFileChooser fileChooser = new JFileChooser();

	JLabel lblWaitingForImages;

	File selectedFile1;
	File selectedFile2;

	final JRadioButton rdbtnDefaultFusion = new JRadioButton("Standard Fused Image");
	final JRadioButton rdbtnUserChoosesBase = new JRadioButton("Highlighted Fused Image");

	JButton btnInputImage1 = new JButton("Input Image 1");
	JButton btnInputImage2 = new JButton("Input Image 2");
	JButton btnSaveImage = new JButton("Save Image");
	JButton btnFuse = new JButton("Fuse");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmImageFusion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getImage1FromUser(){
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(frmImageFusion);
		if (result == JFileChooser.APPROVE_OPTION) {
            controller.getImage1(fileChooser.getSelectedFile());
		}
	}

	public void getImage2FromUser(){
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(frmImageFusion);
		if (result == JFileChooser.APPROVE_OPTION) {
            controller.getImage2(fileChooser.getSelectedFile());
		}
	}
	
	public boolean isHighlightedChecked(){
		if(rdbtnDefaultFusion.isSelected() == true)
		{
			return true;
		}
		return false;
	}

	private void setProgBar(int value){
		progressBar.setValue(value);
	}

	private void setLabelText(JLabel label, String text){
		label.setText(text);
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmImageFusion = new JFrame();
		frmImageFusion.setTitle("Image Fusion");
		frmImageFusion.setBounds(100, 100, 466, 324);
		frmImageFusion.getContentPane().setLayout(null);
		frmImageFusion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		progressBar = new JProgressBar();
        //lblWaitingForImages = new JLabel();


		btnInputImage1.setBounds(20, 160, 117, 29);
		btnInputImage1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//pass image1 to image IO
				getImage1FromUser();
				setProgBar(33);
				setLabelText(lblWaitingForImages, "Image 1 Accepted");
			}
		});
		frmImageFusion.getContentPane().setLayout(null);
		frmImageFusion.getContentPane().add(btnInputImage1);
		
		btnInputImage2.setBounds(20, 204, 117, 29);
		btnInputImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pass image2 to image IO
				getImage2FromUser();
				setProgBar(66);
				setLabelText(lblWaitingForImages, "Image 2 Accepted");
			}
		});
		frmImageFusion.getContentPane().add(btnInputImage2);
		rdbtnDefaultFusion.setBounds(55, 33, 197, 23);

		rdbtnDefaultFusion.setToolTipText("Chooses the larger file as the base image.");
		rdbtnDefaultFusion.setSelected(true);
		rdbtnDefaultFusion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnUserChoosesBase.setSelected(false);
			}
		});
		frmImageFusion.getContentPane().add(rdbtnDefaultFusion);

		rdbtnUserChoosesBase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnDefaultFusion.setSelected(false);
			}
		});
		rdbtnUserChoosesBase.setBounds(55, 68, 197, 23);


		rdbtnUserChoosesBase.setToolTipText("You decide which image will be the base image");
		frmImageFusion.getContentPane().add(rdbtnUserChoosesBase);
		btnSaveImage.setBounds(307, 204, 117, 29);
		frmImageFusion.getContentPane().add(btnSaveImage);
		progressBar.setBounds(153, 134, 146, 20);
		frmImageFusion.getContentPane().add(progressBar);

		JLabel lblFusionProgress = new JLabel("Fusion Progress");
		lblFusionProgress.setBounds(171, 119, 109, 16);
		lblFusionProgress.setHorizontalAlignment(SwingConstants.CENTER);
		frmImageFusion.getContentPane().add(lblFusionProgress);
		btnFuse.setBounds(307, 160, 117, 29);
        btnFuse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fuse image1 and image2
                setProgBar(100);
                controller.fuseImages();
                setLabelText(lblWaitingForImages, "Images Fused");
            }
        });

		frmImageFusion.getContentPane().add(btnFuse);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(204, 182, 43, 16);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		frmImageFusion.getContentPane().add(lblStatus);

		lblWaitingForImages = new JLabel("Waiting For Images");
		lblWaitingForImages.setToolTipText("Current Status");
		lblWaitingForImages.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitingForImages.setBounds(153, 217, 146, 16);
		frmImageFusion.getContentPane().add(lblWaitingForImages);

		JMenuBar menuBar = new JMenuBar();
		frmImageFusion.setJMenuBar(menuBar);

		JMenu mFile = new JMenu("File");
		mFile.setMnemonic('f');

		ImageIcon iconNew = new ImageIcon("file_new.gif");
		Action actionNew = new AbstractAction("New", iconNew) {
			/**
			 *  I want this to restart the GUI
			 * 
			 */
			private static final long serialVersionUID = 1L;
			// create new instance of gui display

			public void actionPerformed(ActionEvent e) {
				try {
                    restartApplication();
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
			}
		};
		JMenuItem item = mFile.add(actionNew);
		mFile.add(item);

		mFile.addSeparator();

		Action actionExit = new AbstractAction("Exit") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		item = mFile.add(actionExit);
		item.setMnemonic('x');
		menuBar.add(mFile);

		//frmImageFusion.pack();
	}

    public void restartApplication() throws Exception
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(GUI.class.getProtectionDomain().getCodeSource().getLocation().toURI());

  /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

  /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }
}
