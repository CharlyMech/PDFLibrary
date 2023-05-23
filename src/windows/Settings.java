package windows;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Settings extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
	// ATTRIBUTES
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private JPanel main = new JPanel();
	private ImageIcon radioIcon;
	private ImageIcon selectedRadioIcon;
	private ButtonGroup themes = new ButtonGroup();
	private JRadioButton lightTheme;
	private JRadioButton darkTheme;
	private JLabel directoryButton;
	private ImageIcon directoryButtonIcon;
	private ImageIcon directoryButtonHoverIcon;
	private ImageIcon directoryButtonPressedIcon;
	private JLabel downloadPath;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public Settings() {
		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(500, 350); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library - Settings");
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon wlogo = new ImageIcon("icons/settings_icon.png");
		this.setIconImage(wlogo.getImage());

		// TOP BAR -
		this.topBar.setBounds(0, 0, 500, 80);
		this.topBar.setLayout(null); // Set Layout manager to null
		this.topBar.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel

		// Close Button
		this.closeButton = new ImageIcon("icons/LIGHT/close_button.png");
		this.closeButtonPressedIcon = new ImageIcon("icons/LIGHT/close_button_pressed.png");
		ImageIcon closeShadowIcon = new ImageIcon("icons/LIGHT/close_shadow.png");
		this.close = new JLabel(this.closeButton, JLabel.CENTER); // CLOSE
		this.close.setBounds(15, 15, 50, 50);
		this.close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.close.addMouseListener(this); // Add Mouse Listener for close operation
		this.topBar.add(this.close);

		JLabel closeShadow = new JLabel(closeShadowIcon, JLabel.CENTER); // SHADOW
		closeShadow.setBounds(15, 15, 50, 50);
		this.topBar.add(closeShadow);

		// Top bar Image
		JLabel topBarLabel = new JLabel(new ImageIcon(
				new ImageIcon("img/library_settings_topBar.jpg").getImage().getScaledInstance(1200, 80,
						Image.SCALE_DEFAULT)));
		topBarLabel.setBounds(0, 0, 500, 80);
		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel
		this.topBar.add(topBarLabel);

		// - TOP BAR

		// - MAIN
		this.main.setBounds(0, 80, 500, 270);
		this.main.setLayout(null); // Set Layout manager to null
		this.main.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		// - THEME
		JPanel themeSetting = new JPanel();
		themeSetting.setLayout(null);
		themeSetting.setBounds(20, 25, 450, 75);
		themeSetting.setBackground(new Color(0, 0, 0, 0));

		// Theme label
		JLabel themeLabel = new JLabel();
		themeLabel.setBounds(0, 20, 170, 35);
		themeLabel.setText("Select a Theme:");
		themeLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		themeLabel.setHorizontalAlignment(JLabel.CENTER);
		themeLabel.setForeground(new Color(0x232323));
		themeLabel.setBackground(new Color(0, 0, 0, 0));
		themeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		themeSetting.add(themeLabel);

		this.selectedRadioIcon = new ImageIcon("icons/LIGHT/radio_button_selected.png");
		this.radioIcon = new ImageIcon("icons/LIGHT/radio_button.png");

		// Light Theme JRadioButton
		this.lightTheme = new JRadioButton("Light");
		this.lightTheme.setBounds(200, 20, 110, 35);
		this.lightTheme.setBackground(new Color(0, 0, 0, 0));
		this.lightTheme.setForeground(new Color(0x232323));
		this.lightTheme.setFont(new Font("Roboto", Font.BOLD, 16));
		this.lightTheme.setIcon(this.radioIcon);
		this.lightTheme.setSelectedIcon(this.selectedRadioIcon);
		this.lightTheme.setRolloverIcon(this.radioIcon);
		this.lightTheme.addActionListener(this);
		this.lightTheme.setSelected(true);
		this.themes.add(this.lightTheme);
		themeSetting.add(this.lightTheme);

		// Dark Theme JRadioButton
		this.darkTheme = new JRadioButton("Dark");
		this.darkTheme.setBounds(320, 20, 110, 35);
		this.darkTheme.setBackground(new Color(0, 0, 0, 0));
		this.darkTheme.setForeground(new Color(0x232323));
		this.darkTheme.setFont(new Font("Roboto", Font.BOLD, 16));
		this.darkTheme.setIcon(this.radioIcon);
		this.darkTheme.setSelectedIcon(this.selectedRadioIcon);
		this.darkTheme.setRolloverIcon(this.radioIcon);
		this.darkTheme.addActionListener(this);
		this.darkTheme.setSelected(true);
		this.themes.add(this.darkTheme);
		themeSetting.add(this.darkTheme);

		this.main.add(themeSetting);
		// - THEME

		// - DIRECTORY
		JPanel directorySetting = new JPanel();
		directorySetting.setLayout(null);
		directorySetting.setBounds(20, 120, 450, 125);
		directorySetting.setBackground(new Color(0, 0, 0, 0));

		// Directory Label
		JLabel directoryLabel = new JLabel();
		directoryLabel.setBounds(0, 20, 285, 35);
		directoryLabel.setText("Select Download Directory:");
		directoryLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		directoryLabel.setHorizontalAlignment(JLabel.CENTER);
		directoryLabel.setForeground(new Color(0x232323));
		directoryLabel.setBackground(new Color(0, 0, 0, 0));
		directoryLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		directorySetting.add(directoryLabel);

		// Directory Button
		this.directoryButtonIcon = new ImageIcon("icons/LIGHT/select_folder.png");
		this.directoryButtonPressedIcon = new ImageIcon("icons/LIGHT/select_folder_pressed.png");
		this.directoryButtonHoverIcon = new ImageIcon("icons/LIGHT/select_folder_hover.png");
		ImageIcon directoryshadow = new ImageIcon("icons/LIGHT/select_folder_shadow.png");

		this.directoryButton = new JLabel(this.directoryButtonIcon);
		this.directoryButton.setBounds(310, 20, 120, 35);
		this.directoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.directoryButton.addMouseListener(this);
		directorySetting.add(this.directoryButton);

		JLabel directoryShadow = new JLabel(directoryshadow);
		directoryShadow.setBounds(310, 20, 120, 35);
		directorySetting.add(directoryShadow);

		// Current Dowload Label
		JLabel currDirectory = new JLabel("Current Download Directory:");
		currDirectory.setBounds(0, 75, 210, 30);
		currDirectory.setFont(new Font("Roboto", Font.BOLD, 12));
		currDirectory.setForeground(new Color(0x8E765F));
		directorySetting.add(currDirectory);

		// Current Download Path
		this.downloadPath = new JLabel();
		this.downloadPath.setBounds(220, 75, 250, 25);
		this.downloadPath.setText("/user/download/path/config"); // Sample text
		this.downloadPath.setHorizontalAlignment(JLabel.CENTER);
		this.downloadPath.setFont(new Font("Roboto", Font.ITALIC, 12));
		this.downloadPath.setForeground(new Color(0x8E765F));
		this.downloadPath.setBackground(new Color(0, 0, 0, 0));
		this.downloadPath.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xA6947D)));
		directorySetting.add(this.downloadPath);

		this.main.add(directorySetting);
		// - DIRECTORY

		//- MAIN

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(this.main);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	// MOUSE LISTENER Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			Library.settingsFlag = false;
			Library.openedWindows.remove(this);
			this.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Close Button Hover
		if (e.getSource() == this.close) {
			this.close.setBounds(20, 10, 50, 50);
			this.repaint();
		}

		// Select Directory Button Hover
		if (e.getSource() == this.directoryButton) {
			this.directoryButton.setBounds(315, 15, 120, 35);
			this.directoryButton.setIcon(this.directoryButtonHoverIcon);
			this.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Close Button Exit Hover
		if (e.getSource() == this.close) {
			this.close.setIcon(this.closeButton);
			this.close.setBounds(15, 15, 50, 50);
			this.repaint();
		}

		// Select Directory Button Exit Hover
		if (e.getSource() == this.directoryButton) {
			this.directoryButton.setBounds(310, 20, 120, 35);
			this.directoryButton.setIcon(this.directoryButtonIcon);
			this.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Top Bar event to Drag and Drop
		if (e.getSource() == this.topBar) {
			this.xMouse = e.getX();
			this.yMouse = e.getY();
		}

		// Close Button Pressed
		if (e.getSource() == this.close) {
			this.close.setIcon(this.closeButtonPressedIcon);
			this.close.setBounds(15, 15, 50, 50);
			this.repaint();
		}

		// Select Directory Button Pressed
		if (e.getSource() == this.directoryButton) {
			this.directoryButton.setBounds(310, 20, 120, 35);
			this.directoryButton.setIcon(this.directoryButtonPressedIcon);
			this.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Close Button Released
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeButton);
			this.repaint();
		}

		// Select Directory Button Pressed
		if (e.getSource() == this.directoryButton) {
			this.directoryButton.setBounds(310, 20, 120, 35);
			this.directoryButton.setIcon(this.directoryButtonIcon);
			this.repaint();
		}
	}

	// MOUSE MOTION LISTENER Methods
	@Override
	public void mouseDragged(MouseEvent e) {
		// In case the user tries to drag on close button
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeButton);
			this.repaint();
		}

		// Select Directory Button Pressed
		if (e.getSource() == this.directoryButton) {
			this.directoryButton.setBounds(310, 20, 120, 35);
			this.directoryButton.setIcon(this.directoryButtonIcon);
			this.repaint();
		}

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		this.setLocation(x - this.xMouse, y + this.yMouse); // TODO It's a little offset, but works for now 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// ACTION LISTENER Methods
	@Override
	public void actionPerformed(ActionEvent e) {
		// Light theme selected
		if (e.getSource() == this.lightTheme) {
			this.darkTheme.setIcon(this.radioIcon);
			this.repaint();
		}
		// Dark theme selected -> //! For now only change icon
		if (e.getSource() == this.darkTheme) {
			this.lightTheme.setIcon(this.radioIcon);
			this.repaint();
		}
	}

}
