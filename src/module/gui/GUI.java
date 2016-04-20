package module.gui;

import module.controller.Controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * The current GUI class for our software. Communicates with the controller via actionlisteners. The "view"
 * of this software's MVC design pattern.
 */
public class GUI {

    private Controller controller;
	private JFrame frame;
    private JMenuBar menuBar;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
	private JRadioButton standardRadioButton;
	private JRadioButton highlightRadioButton;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JLabel statusLabel;
    private JLabel currentStatusLabel;
	private JButton image1Button;
	private JButton image2Button;
	private JButton saveButton;
	private JButton fuseButton;

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
        filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        standardRadioButton = new JRadioButton("Standard Fused Image");
        highlightRadioButton = new JRadioButton("Highlighted Fused Image");
        progressBar = new JProgressBar();
        progressLabel = new JLabel("Fusion Progress");
        statusLabel = new JLabel("Status:");
        currentStatusLabel = new JLabel("Waiting For Images");
        image1Button = new JButton("Input Image 1");
        image2Button = new JButton("Input Image 2");
        saveButton = new JButton("Save Image");
        fuseButton = new JButton("Fuse");
        menuBar = new JMenuBar();
    }

    /**
     * Sets bounds and other attributes for each view component.
     */
    private void setupComponents() {
        // Main Frame
        frame.setJMenuBar(menuBar);
        frame.setTitle("Image Fusion");
        frame.setBounds(100, 100, 466, 324);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Standard Radio Button
        standardRadioButton.setBounds(55, 33, 197, 23);
        standardRadioButton.setToolTipText("This provides a standard fused image.");
        standardRadioButton.setSelected(true);

        // Highlight Radio Button
        highlightRadioButton.setBounds(55, 68, 197, 23);
        highlightRadioButton.setToolTipText("This highlights the significant differences.");

        // File Chooser
        fileChooser.setFileFilter(filter);

        // Image 1 Button
        image1Button.setBounds(20, 160, 117, 29);

        // Image 2 Button
        image2Button.setBounds(20, 204, 117, 29);

        // Fuse Button
        fuseButton.setBounds(307, 160, 117, 29);

        // Save Button
        saveButton.setBounds(307, 204, 117, 29);

        // Progress Bar
        progressBar.setBounds(153, 134, 146, 20);

        // Progress Label
        progressLabel.setBounds(171, 119, 109, 16);
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Status Label
        statusLabel.setBounds(204, 182, 43, 16);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Update Status Label
        currentStatusLabel.setBounds(153, 217, 146, 16);
        currentStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentStatusLabel.setToolTipText("Current Status");
    }

    /**
     * Sets up and adds a listener to every button.
     */
    private void setupListeners() {
        // Standard Radio Button
        standardRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { highlightRadioButton.setSelected(false); }
        });

        // Highlight Radio Button
        highlightRadioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { standardRadioButton.setSelected(false); }
        });

        // Image 1 Button
        image1Button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pass image1 to controller
                controller.getImage1(getImageFromUser());
                progressBar.setValue(33);
                currentStatusLabel.setText("Image 1 Accepted");
            }
        });

        // Image 2 Button
        image2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //pass image2 to controller
                controller.getImage2(getImageFromUser());
                progressBar.setValue(66);
                currentStatusLabel.setText("Image 2 Accepted");
            }
        });

        // Fuse Button
        fuseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //fuse image1 and image2
                controller.fuseImages();
                progressBar.setValue(100);
                currentStatusLabel.setText("Images Fused");
            }
        });

        // Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveImage(getSavePath());
                currentStatusLabel.setText("Saved Fused Image");
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
        contentPane.add(standardRadioButton);
        contentPane.add(highlightRadioButton);
        contentPane.add(saveButton);
        contentPane.add(progressBar);
        contentPane.add(progressLabel);
        contentPane.add(fuseButton);
        contentPane.add(statusLabel);
        contentPane.add(currentStatusLabel);
    }

    /**
     * Creates the drop down file menu.
     */
    private void setupMenu() {
        JMenu mFile = new JMenu("File");
        mFile.setMnemonic('f');
        ImageIcon iconNew = new ImageIcon("file_new.gif");
        Action actionNew = new AbstractAction("New", iconNew) {
            private static final long serialVersionUID = 1L;
            // create new instance of gui display
            public void actionPerformed(ActionEvent e) {
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
        File imageFile = new File("");
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
        File imageFile = new File("");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            imageFile = fileChooser.getSelectedFile();
        }
        return imageFile;
    }
}
