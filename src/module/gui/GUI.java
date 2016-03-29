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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import java.io.File;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font; 


public class GUI {

	private JFrame frmImageFusion;

	JProgressBar progressBar;

	JFileChooser fileChooser = new JFileChooser();

	JLabel lblWaitingForImages;

	File selectedFile1;
	File selectedFile2;
	
	JRadioButton rdbtnDefaultFusion;
	JRadioButton rdbtnUserChoosesBase;

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

	public JProgressBar getProgBar(){
		return progressBar;
	}

	public File getFile1(){
		return selectedFile1;
	}

	public File getFile2(){
		return selectedFile2;
	}

	public void getImageFromUser1(){
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(frmImageFusion);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile1 = fileChooser.getSelectedFile();
			//System.out.println("Selected file: " + selectedFile1.getAbsolutePath());
		}
	}

	public void getImageFromUser2(){
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(frmImageFusion);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile2 = fileChooser.getSelectedFile();
			//System.out.println("Selected file: " + selectedFile2.getAbsolutePath());
		}
	}
	
	public boolean isDefaultImageChecked(){
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
	 * Reset to default settings
	 *
	private void reset() {
		initialize();
	}
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		frmImageFusion = new JFrame();
		//frmImageFusion.getContentPane().setBackground(SystemColor.window);
		frmImageFusion.setTitle("Image Fusion");
		frmImageFusion.setBounds(100, 100, 466, 324);
		frmImageFusion.getContentPane().setLayout(null);
		frmImageFusion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		


		final JRadioButton rdbtnDefaultFusion = new JRadioButton("Default Fusion");
		final JRadioButton rdbtnUserChoosesBase = new JRadioButton("User Chooses Base Image");
		JButton btnInputImage = new JButton("Input Image 1");
		JButton btnInputImage_1 = new JButton("Input Image 2");
		JButton btnSaveImage = new JButton("Save Image");
		JButton btnFuse = new JButton("Fuse");
		progressBar = new JProgressBar();


		btnInputImage.setBounds(20, 160, 117, 29);
		btnInputImage.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//pass image1 to image IO
				getImageFromUser1();
				setProgBar(10);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						setLabelText(lblWaitingForImages, "Image 1 Accepted");
					}
				});
				t.start();
			}
		});
		frmImageFusion.getContentPane().setLayout(null);
		frmImageFusion.getContentPane().add(btnInputImage);
		
		btnInputImage_1.setBounds(20, 204, 117, 29);
		btnInputImage_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pass image2 to image IO
				getImageFromUser2();
				setProgBar(20);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						setLabelText(lblWaitingForImages, "Image 2 Accepted");
					}
				});
				t.start();
			}
		});
		frmImageFusion.getContentPane().add(btnInputImage_1);
		rdbtnDefaultFusion.setBounds(55, 33, 141, 23);

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
		frmImageFusion.getContentPane().add(btnFuse);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(204, 182, 43, 16);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		frmImageFusion.getContentPane().add(lblStatus);

		JLabel lblStatusUpdate = new JLabel("Waiting For Images");
		lblStatusUpdate.setToolTipText("Current Status");
		lblStatusUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusUpdate.setBounds(153, 217, 146, 16);
		frmImageFusion.getContentPane().add(lblStatusUpdate);

		JMenuBar menuBar = new JMenuBar();
		frmImageFusion.setJMenuBar(menuBar);

		JMenu mFile = new JMenu("File");
		mFile.setMnemonic('f');

		ImageIcon iconNew = new ImageIcon("file_new.gif");
		Action actionNew = new AbstractAction("New", iconNew) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			// create new instance of gui display

			public void actionPerformed(ActionEvent e) {
				//GUI.this.reset();
				//System.out.println("new action");
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
}
