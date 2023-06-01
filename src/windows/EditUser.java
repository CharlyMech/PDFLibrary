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
import java.util.ArrayList;

public class EditUser extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
	// ATTRIBUTES
	private int userId;
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close; // Close Button Label
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private JLabel changeImage;
	private ImageIcon changeImageIcon;
	private ImageIcon changeImageIconPressed;
	private JTextField name; // Name Input Text
	private String nameString;
	private JTextField surname; // Surname Input Text
	private String surnameString;
	private JTextField mail; // Mail Input
	private String mailString;
	private int tierId;
	private int selectTier = this.tierId;
	private JPasswordField passwd; // Password INput
	private JPasswordField confirmPasswd; // Confirm Password Input
	private JLabel showPasswd; // Show Password JLabel for Icon
	private JLabel showConfirmPasswd; // Show Confirm Password JLabel for Icon
	private ImageIcon closedEye;
	private ImageIcon openedEye;
	private JLabel passwdInfo;
	private ImageIcon radioIcon;
	private ImageIcon selectedRadioIcon;
	private ButtonGroup tiers;
	private JRadioButton free; // Free Tier JRadioButton
	private JRadioButton pro; // Pro Tier JRadioButton
	private JRadioButton premium; // Premium Tier JRadioButton
	private JLabel save;
	private ImageIcon saveIcon;
	private ImageIcon saveIconPressed;
	private int xMouse;// Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events 677386677

	// CONSTRUCTOR
	public EditUser(int user_id) {
		this.userId = user_id;

		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(600, 525); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library - Edit User");
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon wlogo = new ImageIcon("icons/user_icon.png");
		this.setIconImage(wlogo.getImage());

		// USER INFO -
		ArrayList<Object> userInfo = Query.returnUserInfo(this.userId);
		this.nameString = userInfo.get(0).toString();
		this.surnameString = userInfo.get(1).toString();
		this.mailString = userInfo.get(2).toString();
		this.tierId = (int) userInfo.get(4);
		// - USER INFO

		// TOP BAR -
		this.topBar.setBounds(0, 0, 600, 80);
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

		// Top bar Image
		JLabel topBarLabel = new JLabel(new ImageIcon(
				new ImageIcon("img/library_editUser_topBar.jpg").getImage().getScaledInstance(600, 80,
						Image.SCALE_DEFAULT)));
		topBarLabel.setBounds(0, 0, 600, 80);
		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel
		this.topBar.add(topBarLabel);

		// - TOP BAR

		// MAIN -
		JPanel main = new JPanel(null);
		main.setBounds(0, 80, 600, 450);
		main.setBackground(new Color(0, 0, 0, 0));

		// User Image -> //! For now default one, not able to change it
		JLabel userImage = new JLabel(new ImageIcon(
				new ImageIcon("img/default_user_image.png").getImage().getScaledInstance(100, 100,
						Image.SCALE_DEFAULT)));
		userImage.setBounds(25, 25, 100, 100);
		main.add(userImage);

		// Change Image Button
		this.changeImageIcon = new ImageIcon("icons/LIGHT/change_user_image.png");
		this.changeImageIconPressed = new ImageIcon("icons/LIGHT/change_user_image_pressed.png");
		ImageIcon changeImageIconShadow = new ImageIcon("icons/LIGHT/change_user_image_shadow.png");

		this.changeImage = new JLabel();
		this.changeImage.setIcon(this.changeImageIcon);
		this.changeImage.setBounds(20, 135, 110, 35);
		this.changeImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.changeImage.addMouseListener(this);
		main.add(this.changeImage);

		JLabel changeImageShadow = new JLabel();
		changeImageShadow.setIcon(changeImageIconShadow);
		changeImageShadow.setBounds(20, 135, 110, 35);
		main.add(changeImageShadow);

		// Name JLabel
		this.name = new JTextField();
		this.name.setBounds(175, 50, 150, 35);
		this.name.setText(this.nameString);
		this.name.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.name.setHorizontalAlignment(JTextField.CENTER);
		this.name.setForeground(new Color(0xA6947D));
		this.name.setBackground(new Color(0, 0, 0, 0));
		this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.name.addMouseListener(this); // Add Mouse Listener -> Text focus
		main.add(this.name);

		// Surname JLabel
		this.surname = new JTextField();
		this.surname.setBounds(350, 50, 225, 35);
		this.surname.setText(this.surnameString);
		this.surname.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.surname.setHorizontalAlignment(JTextField.CENTER);
		this.surname.setForeground(new Color(0xA6947D));
		this.surname.setBackground(new Color(0, 0, 0, 0));
		this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.surname.addMouseListener(this); // Add Mouse Listener -> Text focus
		main.add(this.surname);

		// Mail JLabel
		this.mail = new JTextField();
		this.mail.setBounds(175, 110, 400, 35);
		this.mail.setText(this.mailString);
		this.mail.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.mail.setHorizontalAlignment(JTextField.CENTER);
		this.mail.setForeground(new Color(0xA6947D));
		this.mail.setBackground(new Color(0, 0, 0, 0));
		this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.mail.addMouseListener(this); // Add Mouse Listener -> Text focus
		main.add(this.mail);

		// Password Info JLabel
		ImageIcon passwdInfoIcon = new ImageIcon("icons/LIGHT/password_info.png");
		this.passwdInfo = new JLabel(passwdInfoIcon);
		this.passwdInfo.setBounds(145, 175, 20, 20);
		this.passwdInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.passwdInfo.addMouseListener(this);
		main.add(this.passwdInfo);

		// Password JLabel
		this.passwd = new JPasswordField();
		this.passwd.setBounds(175, 170, 360, 35);
		this.passwd.setText("New Password"); // This will be displayed as "*"
		this.passwd.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.passwd.setHorizontalAlignment(JPasswordField.CENTER);
		this.passwd.setForeground(new Color(0xA6947D));
		this.passwd.setBackground(new Color(0, 0, 0, 0));
		this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.passwd.addMouseListener(this); // Add Mouse Listener -> Text focus
		main.add(this.passwd);

		// Show Password Icon
		this.closedEye = new ImageIcon("icons/LIGHT/closed_eye.png");
		this.openedEye = new ImageIcon("icons/LIGHT/opened_eye.png");

		this.showPasswd = new JLabel();
		this.showPasswd.setIcon(this.closedEye);
		this.showPasswd.setBounds(550, 175, 25, 18);
		this.showPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.showPasswd.addMouseListener(this);
		main.add(this.showPasswd);

		// Password Confirm JLabel
		this.confirmPasswd = new JPasswordField();
		this.confirmPasswd.setBounds(175, 230, 360, 35);
		this.confirmPasswd.setText("New Password"); // This will be displayed as "*"
		this.confirmPasswd.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.confirmPasswd.setHorizontalAlignment(JPasswordField.CENTER);
		this.confirmPasswd.setForeground(new Color(0xA6947D));
		this.confirmPasswd.setBackground(new Color(0, 0, 0, 0));
		this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.confirmPasswd.addMouseListener(this); // Add Mouse Listener -> Text focus
		main.add(this.confirmPasswd);

		// Show Password Confirmation Icon
		this.showConfirmPasswd = new JLabel();
		this.showConfirmPasswd.setIcon(this.closedEye);
		this.showConfirmPasswd.setBounds(550, 240, 25, 18);
		this.showConfirmPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.showConfirmPasswd.addMouseListener(this);
		main.add(this.showConfirmPasswd);

		// RADIO BUTTONS -
		// Icons
		this.radioIcon = new ImageIcon("icons/LIGHT/radio_button.png");
		this.selectedRadioIcon = new ImageIcon("icons/LIGHT/radio_button_selected.png");

		// Free tier
		this.free = new JRadioButton("Free Tier");
		this.free.setBounds(75, 290, 150, 55);
		this.free.setBackground(new Color(0, 0, 0, 0));
		this.free.setForeground(new Color(0x2E2E2E));
		this.free.setFont(new Font("Roboto", Font.BOLD, 14));
		this.free.setIcon(this.radioIcon);
		this.free.setSelectedIcon(this.selectedRadioIcon);
		this.free.setRolloverIcon(this.radioIcon);
		this.free.addActionListener(this);
		main.add(this.free);

		// Pro tier
		this.pro = new JRadioButton("Pro Tier");
		this.pro.setBounds(225, 290, 150, 55);
		this.pro.setBackground(new Color(0, 0, 0, 0));
		this.pro.setForeground(new Color(0x2E2E2E));
		this.pro.setFont(new Font("Roboto", Font.BOLD, 14));
		this.pro.setIcon(this.radioIcon);
		this.pro.setSelectedIcon(this.selectedRadioIcon);
		this.pro.setRolloverIcon(this.radioIcon);
		this.pro.addActionListener(this);
		main.add(this.pro);

		// Premium tier
		this.premium = new JRadioButton("Premium Tier");
		this.premium.setBounds(375, 290, 150, 55);
		this.premium.setBackground(new Color(0, 0, 0, 0));
		this.premium.setForeground(new Color(0x2E2E2E));
		this.premium.setFont(new Font("Roboto", Font.BOLD, 14));
		this.premium.setIcon(this.radioIcon);
		this.premium.setSelectedIcon(this.selectedRadioIcon);
		this.premium.setRolloverIcon(this.radioIcon);
		this.premium.addActionListener(this);
		main.add(this.premium);

		// Check tier and set Icons to Radio Buttons
		switch (this.tierId) {
			case 1:
				this.free.setSelected(true);
				this.pro.setSelected(false);
				this.premium.setSelected(false);
				break;
			case 2:
				this.free.setSelected(false);
				this.pro.setSelected(true);
				this.premium.setSelected(false);
				break;
			case 3:
				this.free.setSelected(false);
				this.pro.setSelected(false);
				this.premium.setSelected(true);
				break;
		}

		// Add all Radio Button to Group
		this.tiers = new ButtonGroup();
		this.tiers.add(this.free);
		this.tiers.add(this.pro);
		this.tiers.add(this.premium);

		// - RADIO BUTTONS

		// Save Button
		this.saveIcon = new ImageIcon("icons/LIGHT/save.png");
		this.saveIconPressed = new ImageIcon("icons/LIGHT/save_pressed.png");
		ImageIcon saveIconShadow = new ImageIcon("icons/LIGHT/save_shadow.png");

		this.save = new JLabel(this.saveIcon);
		this.save.setBounds(240, 365, 120, 55);
		this.save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.save.addMouseListener(this);
		main.add(this.save);

		JLabel saveShadow = new JLabel(saveIconShadow);
		saveShadow.setBounds(240, 365, 120, 55);
		saveShadow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveShadow.addMouseListener(this);
		main.add(saveShadow);

		// - MAIN

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(main);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	//Mouse Listener Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			Library.openedWindows.remove(this);
			Library.userFlag = false;
			this.dispose();
		}

		// Save Button  event
		if (e.getSource() == this.save) {
			// Check possible cases
			if (!String.valueOf(this.passwd.getPassword()).equals("New Password")
					&& String.valueOf(this.confirmPasswd.getPassword()).equals("New Password")
					|| String.valueOf(this.passwd.getPassword()).equals("New Password")
							&& !String.valueOf(this.confirmPasswd.getPassword()).equals("New Password")) { // One password set and the other default
				JOptionPane.showMessageDialog(null, "You must fill both passwords if you want to re-new it.\nTry again!",
						"Save Info Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (!String.valueOf(this.passwd.getPassword())
					.equals(String.valueOf(this.confirmPasswd.getPassword()))) { // Passwords different
				JOptionPane.showMessageDialog(null, "Passwords do not match.\nTry again!",
						"Save Info Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (!App.checkPasswd(String.valueOf(this.passwd.getPassword()))
					&& !String.valueOf(this.passwd.getPassword()).equals("New Password")
					|| !App.checkPasswd(String.valueOf(this.confirmPasswd.getPassword()))
							&& !String.valueOf(this.confirmPasswd.getPassword()).equals("New Password")) { // Not valid Password
				JOptionPane.showMessageDialog(null, "Your password is not valid.\nTry again!", "Save Info Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (!App.checkMail(this.mail.getText())) { // Bad Mail
				JOptionPane.showMessageDialog(null, "Your mail is not valid.\nTry again!", "Save Info Failed",
						JOptionPane.WARNING_MESSAGE);
			} else if (Query.checkMailDB(this.mail.getText()) && !this.mail.getText().equals(this.mailString)) { // Mail Already registered
				JOptionPane.showMessageDialog(null, "Mail alredy registered.\nTry again!", "Save Info Failed",
						JOptionPane.WARNING_MESSAGE);
			} else { // All is OK

				if (String.valueOf(this.passwd.getPassword()).equals("New Password")) { // Case user do not change passwd
					if (!Query.updateUserInfoNoPasswd(this.userId, this.name.getText(), this.surname.getText(),
							this.mail.getText(), this.selectTier)) {
						int reply = JOptionPane.showConfirmDialog(null,
								"Something went wrong with the update\nPlease try later",
								"Save Info Failed",
								JOptionPane.DEFAULT_OPTION);

						if (reply == JOptionPane.OK_OPTION) {
							Library.openedWindows.remove(this);
							Library.userFlag = false;
							this.dispose();
						}
					}

					int reply = JOptionPane.showConfirmDialog(null,
							"Your information has been updated\nClick to return",
							"Save Info Success",
							JOptionPane.DEFAULT_OPTION);

					if (reply == JOptionPane.OK_OPTION) {
						Library.openedWindows.remove(this);
						Library.userFlag = false;
						this.dispose();
					}
				} else {
					String hash = App.hashPassword(String.valueOf(this.passwd.getPassword()));
					if (!Query.updateUserInfo(this.userId, this.name.getText(), this.surname.getText(), this.mail.getText(),
							hash, this.selectTier)) {
						int reply = JOptionPane.showConfirmDialog(null,
								"Something went wrong with the update\nPlease try later",
								"Save Info Failed",
								JOptionPane.DEFAULT_OPTION);

						if (reply == JOptionPane.OK_OPTION) {
							Library.openedWindows.remove(this);
							Library.userFlag = false;
							this.dispose();
						}
					}

					int reply = JOptionPane.showConfirmDialog(null,
							"Your information has been updated\nClick to return",
							"Save Info Success",
							JOptionPane.DEFAULT_OPTION);

					if (reply == JOptionPane.OK_OPTION) {
						Library.openedWindows.remove(this);
						Library.userFlag = false;
						this.dispose();
					}
				}
			}

			this.name.setText(this.nameString);
			this.name.setBackground(new Color(0xEFE5D5));
			this.name.setForeground(new Color(0xA6947D));
			this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.name.setOpaque(true);

			this.surname.setText(this.surnameString);
			this.surname.setBackground(new Color(0xEFE5D5));
			this.surname.setForeground(new Color(0xA6947D));
			this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.surname.setOpaque(true);

			this.mail.setText(this.mailString);
			this.mail.setBackground(new Color(0xEFE5D5));
			this.mail.setForeground(new Color(0xA6947D));
			this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.mail.setOpaque(true);

			this.passwd.setText("New Password");
			this.passwd.setBackground(new Color(0xEFE5D5));
			this.passwd.setForeground(new Color(0xA6947D));
			this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.passwd.setOpaque(true);

			this.confirmPasswd.setText("New Password");
			this.confirmPasswd.setBackground(new Color(0xEFE5D5));
			this.confirmPasswd.setForeground(new Color(0xA6947D));
			this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
			this.confirmPasswd.setOpaque(true);
		}

		// Password Info Event
		if (e.getSource() == this.passwdInfo) {
			JOptionPane.showMessageDialog(null,
					"Password must have a lenth of 8 to 64 characters and contain at least:\n\t- 1 Capital letter (A-Z)\n\t- 1 Number (0-9)\n\t- 1 Special character (!@#$%^&*)");
		}

		// Inputs Text Events // 
		// Name
		if (e.getSource() == this.name) {
			// Name
			if (this.name.getText().equalsIgnoreCase(this.nameString)) {
				this.name.setText("");
				this.name.setBackground(new Color(0xE9C8A7));
				this.name.setForeground(new Color(0x2E2E2E));
				this.name.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText(this.surnameString);
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText(this.mailString);
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("New Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("New Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		// Surname
		if (e.getSource() == this.surname) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText(this.nameString);
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().equalsIgnoreCase(this.surnameString)) {
				this.surname.setText("");
				this.surname.setBackground(new Color(0xE9C8A7));
				this.surname.setForeground(new Color(0x2E2E2E));
				this.surname.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText(this.mailString);
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("New Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("New Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		// Mail
		if (e.getSource() == this.mail) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText(this.nameString);
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText(this.nameString);
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().equalsIgnoreCase(this.mailString)) {
				this.mail.setText("");
				this.mail.setBackground(new Color(0xE9C8A7));
				this.mail.setForeground(new Color(0x2E2E2E));
				this.mail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("New Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("New Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		// Password
		if (e.getSource() == this.passwd) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText(this.nameString);
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText(this.surnameString);
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText(this.mailString);
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).equals("New Password")) {
				this.passwd.setText("");
				this.passwd.setBackground(new Color(0xE9C8A7));
				this.passwd.setForeground(new Color(0x2E2E2E));
				this.passwd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).isEmpty()) {
				this.confirmPasswd.setText("New Password");
				this.confirmPasswd.setBackground(new Color(0xEFE5D5));
				this.confirmPasswd.setForeground(new Color(0xA6947D));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}

		// Confirm Password
		if (e.getSource() == this.confirmPasswd) {
			// Name
			if (this.name.getText().isEmpty()) {
				this.name.setText(this.nameString);
				this.name.setBackground(new Color(0xEFE5D5));
				this.name.setForeground(new Color(0xA6947D));
				this.name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.name.setOpaque(true);
			}

			// Surname
			if (this.surname.getText().isEmpty()) {
				this.surname.setText(this.surnameString);
				this.surname.setBackground(new Color(0xEFE5D5));
				this.surname.setForeground(new Color(0xA6947D));
				this.surname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.surname.setOpaque(true);
			}

			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText(this.mailString);
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password 1
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("New Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			// Password 2
			if (String.valueOf(this.confirmPasswd.getPassword()).equals("New Password")) {
				this.confirmPasswd.setText("");
				this.confirmPasswd.setBackground(new Color(0xE9C8A7));
				this.confirmPasswd.setForeground(new Color(0x2E2E2E));
				this.confirmPasswd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.confirmPasswd.setOpaque(true);
			}

			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Close Button Hover
		if (e.getSource() == this.close) {
			this.close.setBounds(20, 10, 50, 50);
			this.repaint();
		}

		// Change Image Hover
		if (e.getSource() == this.changeImage) {
			this.changeImage.setBounds(25, 130, 110, 35);
			this.repaint();
		}

		// Save Hover
		if (e.getSource() == this.save) {
			this.save.setBounds(245, 360, 120, 55);
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

		// Change Image Exit Hover
		if (e.getSource() == this.changeImage) {
			this.changeImage.setIcon(this.changeImageIcon);
			this.changeImage.setBounds(20, 135, 110, 35);
			this.repaint();
		}

		// Save Exit Hover
		if (e.getSource() == this.save) {
			this.save.setIcon(this.saveIcon);
			this.save.setBounds(240, 365, 120, 55);
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

		// Change Image Pressed
		if (e.getSource() == this.changeImage) {
			this.changeImage.setIcon(this.changeImageIconPressed);
			this.changeImage.setBounds(20, 135, 110, 35);
			this.repaint();
		}

		// Show Password Event
		if (e.getSource() == this.showPasswd) {
			this.showPasswd.setIcon(this.openedEye);
			if (!String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("New Password")) {
				this.passwd.setEchoChar((char) 0);
			}
			this.repaint();
		}

		// Show Confirm Password Event
		if (e.getSource() == this.showConfirmPasswd) {
			this.showConfirmPasswd.setIcon(this.openedEye);
			if (!String.valueOf(this.confirmPasswd.getPassword()).equalsIgnoreCase("New Password")) {
				this.confirmPasswd.setEchoChar((char) 0);
			}
			this.repaint();
		}

		// Save Pressed
		if (e.getSource() == this.save) {
			this.save.setIcon(this.saveIconPressed);
			this.save.setBounds(240, 365, 120, 55);
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

		// Change Image Released
		if (e.getSource() == this.changeImage) {
			this.changeImage.setIcon(this.changeImageIcon);
			this.changeImage.setBounds(20, 135, 110, 35);
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

		// Save Released
		if (e.getSource() == this.save) {
			this.save.setIcon(this.saveIcon);
			this.save.setBounds(240, 365, 120, 55);
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

		// In case the user tries to drag on change image button
		if (e.getSource() == this.changeImage) {
			this.changeImage.setIcon(this.changeImageIcon);
			this.changeImage.setBounds(20, 135, 110, 35);
			this.repaint();
		}

		// In case the user tries to drag on save button
		if (e.getSource() == this.save) {
			this.save.setIcon(this.saveIcon);
			this.save.setBounds(240, 365, 120, 55);
			this.repaint();
		}

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		this.setLocation(x - this.xMouse, y - this.yMouse);
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
			this.selectTier = 1;
			this.repaint();
		}
		if (e.getSource() == this.pro) {
			this.free.setIcon(this.radioIcon);
			this.premium.setIcon(this.radioIcon);
			this.selectTier = 2;
			this.repaint();
		}
		if (e.getSource() == this.premium) {
			this.free.setIcon(this.radioIcon);
			this.pro.setIcon(this.radioIcon);
			this.selectTier = 3;
			this.repaint();
		}
	}

}
