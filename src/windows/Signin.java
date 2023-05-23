package windows;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import database.Conn;
import database.Query;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Signin extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
	// ATTRIBUTES
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JPanel left = new JPanel(); // Left Panel -> only for image
	private JPanel right = new JPanel(); // Right Panel -> Contains 
	private JLabel close; // Close Button Label
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private JTextField name = new JTextField(); // Name Input Text
	private JTextField surname = new JTextField(); // Surname Input Text
	private JTextField mail; // Mail Input
	private JPasswordField passwd; // Password INput
	private JPasswordField confirmPasswd; // Confirm Password Input
	private JLabel showPasswd; // Show Password JLabel for Icon
	private JLabel showConfirmPasswd; // Show Confirm Password JLabel for Icon
	private ImageIcon closedEye;
	private ImageIcon openedEye;
	private JLabel passwdInfo;
	private ImageIcon radioIcon = new ImageIcon("icons/LIGHT/radio_button.png");
	private ImageIcon selectedRadioIcon = new ImageIcon("icons/LIGHT/radio_button_selected.png");
	private ButtonGroup tiers = new ButtonGroup();
	private int radioValue = 1;
	private JRadioButton free = new JRadioButton("Free Tier"); // Free Tier JRadioButton
	private JRadioButton pro = new JRadioButton("Pro Tier"); // Pro Tier JRadioButton
	private JRadioButton premium = new JRadioButton("Premium Tier"); // Premium Tier JRadioButton
	private JLabel signin = new JLabel(); // Sign In Label for button
	private ImageIcon signinButtonIcon;
	private ImageIcon signinButtonPressedIcon;
	private JLabel goLogin; // Go to Login Window Label
	private int xMouse;// Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public Signin() {
		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(900, 720); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library - Sign In");
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon wlogo = new ImageIcon("icons/logo_icon.png");
		this.setIconImage(wlogo.getImage());

		// TOP BAR -
		this.topBar.setBounds(0, 0, 900, 80);
		this.topBar.setLayout(null); // Set Layout manager to null
		this.topBar.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel

		// Close Button
		// Icons
		this.closeButton = new ImageIcon("icons/LIGHT/close_button.png");
		this.closeButtonPressedIcon = new ImageIcon("icons/LIGHT/close_button_pressed.png");
		ImageIcon closeShadowIcon = new ImageIcon("icons/LIGHT/close_shadow.png");
		// Set Icons to JLabel
		this.close = new JLabel(this.closeButton, JLabel.CENTER); // CLOSE
		this.close.setBounds(15, 15, 50, 50);
		this.close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.close.addMouseListener(this); // Add Mouse Listener for close operation
		this.topBar.add(this.close);

		JLabel closeShadow = new JLabel(closeShadowIcon, JLabel.CENTER); // SHADOW
		closeShadow.setBounds(15, 15, 50, 50);
		this.topBar.add(closeShadow);

		// - TOP BAR

		// LEFT PANEL -
		this.left.setBounds(0, 0, 600, 720); // Panel properties
		this.left.setLayout(null);
		this.left.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		// Icon and JLabel for logo
		ImageIcon logoIcon = new ImageIcon(
				new ImageIcon("img/logo.png").getImage().getScaledInstance(550, 150, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(logoIcon, JLabel.CENTER);
		logo.setBounds(0, 65, 600, 150);
		this.left.add(logo); // Add Logo to the Panel

		// Name JLabel
		this.name.setBounds(65, 215, 190, 55);
		this.name.setText("Name");
		this.name.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.name.setHorizontalAlignment(JTextField.CENTER);
		this.name.setForeground(new Color(0xA6947D));
		this.name.setBackground(new Color(0, 0, 0, 0));
		this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.name.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.left.add(this.name);

		// Surname JLabel
		this.surname.setBounds(275, 215, 240, 55);
		this.surname.setText("Surname");
		this.surname.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.surname.setHorizontalAlignment(JTextField.CENTER);
		this.surname.setForeground(new Color(0xA6947D));
		this.surname.setBackground(new Color(0, 0, 0, 0));
		this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.surname.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.left.add(this.surname);

		// Mail JLabel
		this.mail = new JTextField();
		this.mail.setBounds(65, 290, 450, 55);
		this.mail.setText("Enter your mail here");
		this.mail.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.mail.setHorizontalAlignment(JTextField.CENTER);
		this.mail.setForeground(new Color(0xA6947D));
		this.mail.setBackground(new Color(0, 0, 0, 0));
		this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.mail.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.left.add(this.mail);

		// Password Info JLabel
		ImageIcon passwdInfoIcon = new ImageIcon("icons/LIGHT/password_info.png");
		this.passwdInfo = new JLabel(passwdInfoIcon);
		this.passwdInfo.setBounds(30, 390, 20, 20);
		this.passwdInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.passwdInfo.addMouseListener(this);
		this.left.add(this.passwdInfo);

		// Password JLabel
		this.passwd = new JPasswordField();
		this.passwd.setBounds(65, 370, 450, 55);
		this.passwd.setText("Your Password"); // This will be displayed as "*"
		this.passwd.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.passwd.setHorizontalAlignment(JPasswordField.CENTER);
		this.passwd.setForeground(new Color(0xA6947D));
		this.passwd.setBackground(new Color(0, 0, 0, 0));
		this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.passwd.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.left.add(this.passwd);

		// Show Password Icon
		this.closedEye = new ImageIcon("icons/LIGHT/closed_eye.png");
		this.openedEye = new ImageIcon("icons/LIGHT/opened_eye.png");

		this.showPasswd = new JLabel();
		this.showPasswd.setIcon(this.closedEye);
		this.showPasswd.setBounds(525, 390, 25, 18);
		this.showPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.showPasswd.addMouseListener(this);
		this.left.add(this.showPasswd);

		// Password Confirm JLabel
		this.confirmPasswd = new JPasswordField();
		this.confirmPasswd.setBounds(65, 445, 450, 55);
		this.confirmPasswd.setText("Your Password"); // This will be displayed as "*"
		this.confirmPasswd.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.confirmPasswd.setHorizontalAlignment(JPasswordField.CENTER);
		this.confirmPasswd.setForeground(new Color(0xA6947D));
		this.confirmPasswd.setBackground(new Color(0, 0, 0, 0));
		this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.confirmPasswd.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.left.add(this.confirmPasswd);

		// Show Password Confirmation Icon
		this.showConfirmPasswd = new JLabel();
		this.showConfirmPasswd.setIcon(this.closedEye);
		this.showConfirmPasswd.setBounds(525, 465, 25, 18);
		this.showConfirmPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.showConfirmPasswd.addMouseListener(this);
		this.left.add(this.showConfirmPasswd);

		// Free Tier JRadioButton
		this.free.setBounds(65, 540, 150, 55);
		this.free.setBackground(new Color(0, 0, 0, 0));
		this.free.setForeground(new Color(0x2E2E2E));
		this.free.setFont(new Font("Roboto", Font.BOLD, 14));
		this.free.setIcon(this.radioIcon);
		this.free.setSelectedIcon(this.selectedRadioIcon);
		this.free.setRolloverIcon(this.radioIcon);
		this.free.addActionListener(this);
		this.free.setSelected(true); // Free tier selected by default
		this.left.add(this.free);

		// Pro Tier JRadioButton
		this.pro.setBounds(215, 540, 150, 55);
		this.pro.setBackground(new Color(0, 0, 0, 0));
		this.pro.setForeground(new Color(0x2E2E2E));
		this.pro.setFont(new Font("Roboto", Font.BOLD, 14));
		this.pro.setIcon(this.radioIcon);
		this.pro.setSelectedIcon(this.selectedRadioIcon);
		this.pro.setRolloverIcon(this.radioIcon);
		this.pro.addActionListener(this);
		this.pro.setSelected(false);
		this.left.add(this.pro);

		// Premium Tier JRadioButton
		this.premium.setBounds(365, 540, 150, 55);
		this.premium.setBackground(new Color(0, 0, 0, 0));
		this.premium.setForeground(new Color(0x2E2E2E));
		this.premium.setFont(new Font("Roboto", Font.BOLD, 14));
		this.premium.setIcon(this.radioIcon);
		this.premium.setSelectedIcon(this.selectedRadioIcon);
		this.premium.setRolloverIcon(this.radioIcon);
		this.premium.addActionListener(this);
		this.premium.setSelected(false);
		this.left.add(this.premium);

		// Add All RadioButtons to Radio Group
		this.tiers.add(this.free);
		this.tiers.add(this.pro);
		this.tiers.add(this.premium);

		// Sign In button
		// Declare Icons
		this.signinButtonIcon = new ImageIcon("icons/LIGHT/signin_button.png");
		this.signinButtonPressedIcon = new ImageIcon("icons/LIGHT/signin_button_pressed.png");
		ImageIcon signinShadowIcon = new ImageIcon("icons/LIGHT/signin_button_shadow.png");

		// Declare JLabels and assign Icons
		this.signin = new JLabel(); // SIGNIN
		this.signin.setBounds(230, 630, 120, 55);
		this.signin.setIcon(this.signinButtonIcon);
		this.signin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.signin.addMouseListener(this); // Add Mouse Listener -> Click
		this.left.add(this.signin);

		JLabel signinShadow = new JLabel(); // SHADOW
		signinShadow.setBounds(230, 630, 120, 55);
		signinShadow.setIcon(signinShadowIcon);
		this.left.add(signinShadow);

		// Go Log In
		ImageIcon loginIcon = new ImageIcon( // Sign In icon -> resized to 13x13
				new ImageIcon("icons/png/right-to-bracket-solid.png").getImage().getScaledInstance(13, 13,
						Image.SCALE_DEFAULT));
		this.goLogin = new JLabel();
		this.goLogin.setBounds(500, 685, 90, 35);
		this.goLogin.setText("Go Log In");
		this.goLogin.setIcon(loginIcon); // Set the icon to the label
		this.goLogin.setHorizontalTextPosition(JLabel.LEFT); // Set Label positioning by Icon
		this.goLogin.setForeground(new Color(0x2E2E2E));
		this.goLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.goLogin.addMouseListener(this);
		this.left.add(this.goLogin);

		// - LEFT PANEL

		// RIGHT PANEL -
		this.right.setBounds(600, 0, 300, 720);
		this.right.setLayout(null);

		// Background Image
		JLabel rightImage = new JLabel(); // Icon label
		ImageIcon loginImage = new ImageIcon(
				new ImageIcon("img/library_signin.jpg").getImage().getScaledInstance(300, 720,
						Image.SCALE_DEFAULT));
		rightImage.setBounds(0, 0, 300, 720);
		rightImage.setIcon(loginImage);

		this.right.add(rightImage); // Add Label to the Panel		

		// - RIGHT PANEL

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(this.left);
		this.add(this.right);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	//Mouse Listener Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			this.dispose();
			Conn.closeConnection();
			System.exit(0);
		}

		// Go Log In Window
		if (e.getSource() == this.goLogin) {
			this.dispose();
			new Login();
		}

		// Password Info Event
		if (e.getSource() == this.passwdInfo) {
			JOptionPane.showMessageDialog(null,
					"Password must have a lenth of 8 to 64 characters and contain at least:\n\t- 1 Capital letter (A-Z)\n\t- 1 Number (0-9)\n\t- 1 Special character (!@#$%^&*)");
		}

		// Inputs Text Events
		if (e.getSource() == this.name) {
			// Name
			if (this.name.getText().equalsIgnoreCase("Name")) {
				this.name.setText("");
				this.name.setBackground(new Color(0xE9C8A7));
				this.name.setForeground(new Color(0x2E2E2E));
				this.name.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText("Surname");
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText("Enter your mail here");
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("Your Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("Your Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		if (e.getSource() == this.surname) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText("Name");
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().equalsIgnoreCase("Surname")) {
				this.surname.setText("");
				this.surname.setBackground(new Color(0xE9C8A7));
				this.surname.setForeground(new Color(0x2E2E2E));
				this.surname.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText("Enter your mail here");
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("Your Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("Your Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		if (e.getSource() == this.mail) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText("Name");
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText("Surname");
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().equalsIgnoreCase("Enter your mail here")) {
				this.mail.setText("");
				this.mail.setBackground(new Color(0xE9C8A7));
				this.mail.setForeground(new Color(0x2E2E2E));
				this.mail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("Your Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("Your Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		if (e.getSource() == this.passwd) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText("Name");
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText("Surname");
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText("Enter your mail here");
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("Your Password")) {
				this.passwd.setText("");
				this.passwd.setBackground(new Color(0xE9C8A7));
				this.passwd.setForeground(new Color(0x2E2E2E));
				this.passwd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("Your Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		if (e.getSource() == this.confirmPasswd) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText("Name");
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText("Surname");
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText("Enter your mail here");
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("Your Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).equalsIgnoreCase("Your Password")) {
				this.confirmPasswd.setText("");
				this.confirmPasswd.setBackground(new Color(0xE9C8A7));
				this.confirmPasswd.setForeground(new Color(0x2E2E2E));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		// Sign In Button event
		if (e.getSource() == this.signin) {
			// Some fields are not filled
			if (this.name.getText().isEmpty() || this.surname.getText().isEmpty() || this.mail.getText().isEmpty()
					|| String.valueOf(this.passwd.getPassword()).isEmpty()
					|| String.valueOf(this.confirmPasswd.getPassword()).isEmpty()
					|| this.name.getText().equalsIgnoreCase("Name") || this.surname.getText().equalsIgnoreCase("Surname")
					|| this.mail.getText().equalsIgnoreCase("Enter your mail here")
					|| String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("Your Password")
					|| String.valueOf(this.confirmPasswd.getPassword()).equalsIgnoreCase("Your Password")) {
				JOptionPane.showMessageDialog(null, "All fields must be filled.\nTry again!", "Sign In Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (!App.checkMail(this.mail.getText())) {
				JOptionPane.showMessageDialog(null, "Your mail is not valid.\nTry again!", "Sign In Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (Query.checkMailDB(this.mail.getText())) {
				int reply = JOptionPane.showConfirmDialog(null, "This mail is alredy registered\nDo you want to Log In?",
						"Sign In Failed",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					new Signin();
					this.dispose();
				}
			} else if (!App.checkPasswd(String.valueOf(this.passwd.getPassword()))) {
				JOptionPane.showMessageDialog(null, "Your password is not valid.\nTry again!", "Sign In Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (!String.valueOf(this.passwd.getPassword())
					.equals(String.valueOf(this.confirmPasswd.getPassword()))) {
				JOptionPane.showMessageDialog(null, "Your password do not match.\nTry again!", "Sign In Failed",
						JOptionPane.WARNING_MESSAGE);
			} else {
				// Hash password
				String hashPasswd = App.hashPassword(String.valueOf(this.passwd.getPassword()));
				if (Query.createNewUser(this.name.getText(), this.surname.getText(), this.mail.getText(), hashPasswd,
						this.radioValue)) {
					int reply = JOptionPane.showConfirmDialog(null, "Account created Successfully!\nDo you want to Log In?",
							"Sign In Completed!",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {
						new Library(Query.returnNewUserId());
						this.dispose();
					} else {
						this.dispose();
						new Login();
					}
				}
			}

			this.name.setText("Name");
			this.name.setBackground(new Color(0xEFE5D5));
			this.name.setForeground(new Color(0xA6947D));
			this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.name.setOpaque(true);

			this.surname.setText("Surname");
			this.surname.setBackground(new Color(0xEFE5D5));
			this.surname.setForeground(new Color(0xA6947D));
			this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.surname.setOpaque(true);

			this.mail.setText("Enter your mail here");
			this.mail.setBackground(new Color(0xEFE5D5));
			this.mail.setForeground(new Color(0xA6947D));
			this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.mail.setOpaque(true);

			this.passwd.setText("Your Password");
			this.passwd.setBackground(new Color(0xEFE5D5));
			this.passwd.setForeground(new Color(0xA6947D));
			this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.passwd.setOpaque(true);

			this.confirmPasswd.setText("Your Password");
			this.confirmPasswd.setBackground(new Color(0xEFE5D5));
			this.confirmPasswd.setForeground(new Color(0xA6947D));
			this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.confirmPasswd.setOpaque(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Close Button Hover
		if (e.getSource() == this.close) {
			this.close.setBounds(20, 10, 50, 50);
			this.repaint();
		}

		// Submit Button Hover
		if (e.getSource() == this.signin) {
			this.signin.setBounds(235, 625, 120, 55);
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

		// Submit Button Exit Hover
		if (e.getSource() == this.signin) {
			this.signin.setBounds(230, 630, 120, 55);
			this.signin.setIcon(this.signinButtonIcon);
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
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeButtonPressedIcon);
			this.repaint();
		}

		// Show Password Event
		if (e.getSource() == this.showPasswd) {
			this.showPasswd.setIcon(this.openedEye);
			if (!String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("Your Password")) {
				this.passwd.setEchoChar((char) 0);
			}
			this.repaint();
		}

		// Show Confirm Password Event
		if (e.getSource() == this.showConfirmPasswd) {
			this.showConfirmPasswd.setIcon(this.openedEye);
			if (!String.valueOf(this.confirmPasswd.getPassword()).equalsIgnoreCase("Your Password")) {
				this.confirmPasswd.setEchoChar((char) 0);
			}
			this.repaint();
		}

		// Submit Button Pressed
		if (e.getSource() == this.signin) {
			this.signin.setBounds(230, 630, 120, 55);
			this.signin.setIcon(this.signinButtonPressedIcon);
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

		// Show Password Event
		if (e.getSource() == this.showPasswd) {
			this.showPasswd.setIcon(this.closedEye);
			this.passwd.setEchoChar('•');
			this.repaint();
		}

		// Show Confirm Password Event
		if (e.getSource() == this.showConfirmPasswd) {
			this.showConfirmPasswd.setIcon(this.closedEye);
			this.confirmPasswd.setEchoChar('•');
			this.repaint();
		}

		// Submit Button Released
		if (e.getSource() == this.signin) {
			this.signin.setBounds(230, 630, 120, 55);
			this.signin.setIcon(this.signinButtonIcon);
			this.repaint();
		}
	}

	// Mouse Motion Listener Methods
	@Override
	public void mouseDragged(MouseEvent e) {
		// In case the user tries to drag on close button
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeButton);
			this.repaint();
		}

		// Submit Button Dragged, just in case
		if (e.getSource() == this.signin) {
			this.signin.setBounds(230, 630, 120, 55);
			this.signin.setIcon(this.signinButtonIcon);
			this.repaint();
		}

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		this.setLocation(x - this.xMouse, y + this.yMouse); // TODO It's a little offset, but works for now 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// Action Listener Method
	@Override
	public void actionPerformed(ActionEvent e) {
		// Events for non selected RadioButtons -> Change icon
		if (e.getSource() == this.free) {
			this.pro.setIcon(this.radioIcon);
			this.premium.setIcon(this.radioIcon);
			this.repaint();
			this.radioValue = 1;
		}
		if (e.getSource() == this.pro) {
			this.free.setIcon(this.radioIcon);
			this.premium.setIcon(this.radioIcon);
			this.repaint();
			this.radioValue = 2;
		}
		if (e.getSource() == this.premium) {
			this.free.setIcon(this.radioIcon);
			this.pro.setIcon(this.radioIcon);
			this.repaint();
			this.radioValue = 3;
		}
	}

}
