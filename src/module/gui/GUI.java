package module.gui;

import module.controller.Controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The current GUI class for our software. Communicates with the controller via actionlisteners. The "view"
 * of this software's MVC design pattern.
 */
public class GUI {

    private Controller controller;
    private BufferedImage[] images;
	private JFrame frame;
    private JMenuBar menuBar;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JLabel statusLabel;
    private JLabel currentStatusLabel;
	private JButton image1Button;
	private JButton image2Button;
	private JButton fuseButton;
    private JButton saveStandardButton;
    private JButton saveHighlightButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the view.
	 */
	private GUI() {
        controller = new Controller();
        initializeComponents();
        setupComponents();
        setupListeners();
        setupMenu();
        addComponents();
	}

    /**
     * Initialize the view component fields.
     */
    private void initializeComponents() {
        frame = new JFrame();
        fileChooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg", "tiff");
        progressBar = new JProgressBar();
        progressLabel = new JLabel("Fusion Progress");
        statusLabel = new JLabel("Status:");
        currentStatusLabel = new JLabel("Waiting For Images");
        image1Button = new JButton("Input Image 1");
        image2Button = new JButton("Input Image 2");
        fuseButton = new JButton("Fuse");
        saveStandardButton = new JButton("Save Standard");
        saveHighlightButton = new JButton("Save Highlight");
        menuBar = new JMenuBar();
    }

    /**
     * Sets bounds and other attributes for each view component.
     */
    private void setupComponents() {
        // Main Frame
        frame.setJMenuBar(menuBar);
        frame.setTitle("Image Fusion");
        frame.setBounds(100, 100, 466, 254);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // File Chooser
        fileChooser.setFileFilter(filter);

        // Image 1 Button
        image1Button.setBounds(20, 70, 117, 29);

        // Image 2 Button
        image2Button.setBounds(20, 114, 117, 29);

        // Fuse Button
        fuseButton.setBounds(166, 156, 117, 29);

        // Save Standard Button
        saveStandardButton.setBounds(307, 70, 117, 29);

        // Save Highlight Button
        saveHighlightButton.setBounds(307, 114, 117, 29);

        // Progress Label
        progressLabel.setBounds(171, 24, 109, 16);
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Progress Bar
        progressBar.setBounds(153, 41, 146, 20);

        // Status Label
        statusLabel.setBounds(204, 80, 43, 16);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Update Status Label
        currentStatusLabel.setBounds(153, 115, 146, 16);
        currentStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentStatusLabel.setToolTipText("Current Status");
    }

    /**
     * Sets up and adds a listener to every button.
     */
    private void setupListeners() {
        // Image 1 Button
        image1Button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pass image1 to controller
                File imageFile = getImageFromUser();
                if (imageFile != null) {
                    controller.getImage1(imageFile);
                    int progressValue = progressBar.getValue();
                    progressBar.setValue(progressValue + 33);
                    currentStatusLabel.setText("Image 1 Accepted");
                }
                else
                    currentStatusLabel.setText("Image 1 Cancelled");
            }
        });

        // Image 2 Button
        image2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //pass image2 to controller
                File imageFile = getImageFromUser();
                if (imageFile != null) {
                    controller.getImage2(imageFile);
                    int progressValue = progressBar.getValue();
                    progressBar.setValue(progressValue + 33);
                    currentStatusLabel.setText("Image 2 Accepted");
                }
                else
                    currentStatusLabel.setText("Image 2 Cancelled");
            }
        });

        // Fuse Button
        fuseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fuse image1 and image2
                images = controller.fuseImages();
                if (images != null) {
                    displayOriginal(images[0], images[1]);
                    displayFusion(images[2], images[3]);
                    progressBar.setValue(100);
                    currentStatusLabel.setText("Images Fused");
                }
                else
                    currentStatusLabel.setText("Select Two Images");
            }
        });

        // Save Standard Button
        saveStandardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (images != null) {
                    File imageFile = getSavePath();
                    if (imageFile != null) {
                        controller.saveImage(images[2], imageFile);
                        currentStatusLabel.setText("Saved Standard Image");
                    }
                    else
                        currentStatusLabel.setText("Cancelled Saving");
                }
                else
                    currentStatusLabel.setText("Fuse Before Saving");
            }
        });

        // Save Highlight Button
        saveHighlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (images != null) {
                    File imageFile = getSavePath();
                    if (imageFile != null) {
                        controller.saveImage(images[3], imageFile);
                        currentStatusLabel.setText("Saved Highlight Image");
                    }
                    else
                        currentStatusLabel.setText("Cancelled Saving");
                }
                else
                    currentStatusLabel.setText("Fuse Before Saving");
            }
        });
    }

    /**
     * Adds each view component to the frame's content pane.
     */
    private void addComponents() {
        Container contentPane = frame.getContentPane();
        contentPane.add(image1Button);
        contentPane.add(image2Button);
        contentPane.add(fuseButton);
        contentPane.add(saveStandardButton);
        contentPane.add(saveHighlightButton);
        contentPane.add(progressBar);
        contentPane.add(progressLabel);
        contentPane.add(statusLabel);
        contentPane.add(currentStatusLabel);
    }

    /**
     * Creates the drop down file menu.
     */
    private void setupMenu() {
        JMenu mFile = new JMenu("File");
        mFile.setMnemonic(KeyEvent.VK_F);
        Action actionNew = new AbstractAction("New") {
            private static final long serialVersionUID = 1L;
            // create new instance of gui display
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            GUI window = new GUI();
                            window.frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        JMenuItem item = mFile.add(actionNew);
        mFile.add(item);
        mFile.addSeparator();

        Action actionExit = new AbstractAction("Exit") {
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        };
        item = mFile.add(actionExit);
        item.setMnemonic('x');
        menuBar.add(mFile);
    }

    /**
     * Gets the selected image file from the file chooser.
     * @return the user selected image file
     */
    private File getImageFromUser() {
        File imageFile = null;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            imageFile = fileChooser.getSelectedFile();
        }
        return imageFile;
    }

    /**
     * Gets the selected file path from the file chooser.
     * @return the user selected file path
     */
    private File getSavePath() {
        File imageFile = null;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            imageFile = fileChooser.getSelectedFile();
        }
        return imageFile;
    }

    private void displayOriginal(BufferedImage image1, BufferedImage image2) {
        JFrame frame1 = new JFrame();
        frame1.getContentPane().setLayout(new FlowLayout());
        frame1.getContentPane().add(new JLabel("IMAGE 1"));
        frame1.getContentPane().add(new JLabel(new ImageIcon(image1)));
        frame1.getContentPane().add(new JLabel("IMAGE 2"));
        frame1.getContentPane().add(new JLabel(new ImageIcon(image2)));
        frame1.pack();
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void displayFusion(BufferedImage standard, BufferedImage highlighted) {
        JFrame frame2 = new JFrame();
        frame2.getContentPane().setLayout(new FlowLayout());
        frame2.getContentPane().add(new JLabel("Standard"));
        frame2.getContentPane().add(new JLabel(new ImageIcon(standard)));
        frame2.getContentPane().add(new JLabel("Highlight"));
        frame2.getContentPane().add(new JLabel(new ImageIcon(highlighted)));
        frame2.pack();
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
