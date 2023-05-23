package windows;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Conn;
import database.Query;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Login extends JFrame implements MouseListener, MouseMotionListener {
	// ATTRIBUTES
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private JPanel left = new JPanel(); // Left Panel -> only for image
	private JPanel right = new JPanel(); // Right Panel -> Contains 
	private JTextField mail;
	private JPasswordField passwd;
	private JLabel showPasswd;
	private ImageIcon closedEye;
	private ImageIcon openedEye;
	private JLabel login;
	private ImageIcon loginButtonIcon;
	private ImageIcon loginButtonPressedIcon;
	private JLabel goSignin;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public Login() {
		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(900, 550); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library - Log In");
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
		this.left.setBounds(0, 0, 300, 600); // Panel properties
		this.left.setLayout(null);

		// Background Image
		JLabel leftImage = new JLabel(); // Icon label
		ImageIcon loginImage = new ImageIcon(
				new ImageIcon("img/library_login.jpg").getImage().getScaledInstance(300, 600,
						Image.SCALE_DEFAULT));
		leftImage.setBounds(0, 0, 300, 600);
		leftImage.setIcon(loginImage);

		this.left.add(leftImage); // Add Label to the Panel

		// - LEFT PANEL

		// RIGHT PANEL -
		this.right.setBounds(300, 0, 600, 600);
		this.right.setLayout(null);
		this.right.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		// Icon and JLabel for logo
		ImageIcon logoIcon = new ImageIcon(
				new ImageIcon("img/logo.png").getImage().getScaledInstance(550, 150, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(logoIcon, JLabel.CENTER);
		logo.setBounds(0, 65, 600, 150);
		this.right.add(logo); // Add Logo to the Panel

		// Mail
		this.mail = new JTextField();
		this.mail.setBounds(75, 235, 450, 55);
		this.mail.setText("Enter your mail here");
		this.mail.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.mail.setHorizontalAlignment(JTextField.CENTER);
		this.mail.setForeground(new Color(0xA6947D));
		this.mail.setBackground(new Color(0, 0, 0, 0));
		this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.mail.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.right.add(this.mail);

		// Password 
		this.passwd = new JPasswordField();
		this.passwd.setBounds(75, 325, 450, 55);
		this.passwd.setText("Your Password"); // This will be displayed as "*"
		this.passwd.setFont(new Font("Roboto", Font.PLAIN, 16));
		this.passwd.setHorizontalAlignment(JPasswordField.CENTER);
		this.passwd.setForeground(new Color(0xA6947D));
		this.passwd.setBackground(new Color(0, 0, 0, 0));
		this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.passwd.addMouseListener(this); // Add Mouse Listener -> Text focus
		this.right.add(this.passwd);

		// Show Password Icon
		this.closedEye = new ImageIcon("icons/LIGHT/closed_eye.png");
		this.openedEye = new ImageIcon("icons/LIGHT/opened_eye.png");
		this.showPasswd = new JLabel();
		this.showPasswd.setBounds(535, 340, 25, 20);
		this.showPasswd.setBackground(new Color(0, 0, 0, 0));
		this.showPasswd.setIcon(closedEye);
		this.showPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.showPasswd.addMouseListener(this);
		this.right.add(this.showPasswd);

		// Log in
		// Declare Icons
		this.loginButtonIcon = new ImageIcon("icons/LIGHT/login_button.png");
		this.loginButtonPressedIcon = new ImageIcon("icons/LIGHT/login_button_pressed.png");
		ImageIcon loginShadowIcon = new ImageIcon("icons/LIGHT/login_button_shadow.png");

		// Declare JLabels and assign Icons
		this.login = new JLabel(); // LOGIN
		this.login.setBounds(235, 420, 130, 65);
		this.login.setIcon(this.loginButtonIcon);
		this.login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.login.addMouseListener(this); // Add Mouse Listener -> Click
		this.right.add(this.login);

		JLabel loginShadow = new JLabel(); // SHADOW
		loginShadow.setBounds(235, 420, 130, 65);
		loginShadow.setIcon(loginShadowIcon);
		this.right.add(loginShadow);

		// Sign In Message & Redirect
		JLabel notRegisteredMessage = new JLabel(); // Create a label to display the message (the "link" will be the -Sign In [icon]- label)
		notRegisteredMessage.setBounds(180, 515, 160, 35);
		notRegisteredMessage.setText("Still not registered?");
		notRegisteredMessage.setFont(new Font("Roboto", Font.BOLD, 14));
		notRegisteredMessage.setForeground(new Color(0x2E2E2E));
		notRegisteredMessage.setBackground(new Color(0, 0, 0, 0));
		this.right.add(notRegisteredMessage);

		ImageIcon signinIcon = new ImageIcon( // Sign In icon -> resized to 13x13
				new ImageIcon("icons/png/up-right-from-square-solid.png").getImage().getScaledInstance(13, 13,
						Image.SCALE_DEFAULT));
		this.goSignin = new JLabel();
		this.goSignin.setBounds(340, 515, 80, 35);
		this.goSignin.setText("Sign In!");
		this.goSignin.setFont(new Font("Roboto", Font.BOLD, 14));
		this.goSignin.setIcon(signinIcon); // Set the icon to the label
		this.goSignin.setHorizontalTextPosition(JLabel.LEFT); // Set Label positioning by Icon
		this.goSignin.setForeground(new Color(0x2E2E2E));
		this.goSignin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.goSignin.addMouseListener(this);
		this.right.add(this.goSignin);

		// - RIGHT PANEL

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(this.left);
		this.add(this.right);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	// MOUSE LISTENER Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			this.dispose();
			Conn.closeConnection();
			System.exit(0);
		}

		// Log In Button event
		if (e.getSource() == this.login) {
			// Empty or default text mail&password
			if (this.mail.getText().equalsIgnoreCase("Enter your mail here")
					|| String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("Your Password") ||
					this.mail.getText().isEmpty() || String.valueOf(this.passwd.getPassword()).isEmpty()) { // Default text case
				JOptionPane.showMessageDialog(null, "All fields must be filled.\nTry again!", "Log In Failed",
						JOptionPane.ERROR_MESSAGE);
			} else if (!App.checkMail(this.mail.getText())) {
				JOptionPane.showMessageDialog(null, "Your mail is not valid.\nTry again!", "Log In Failed",
						JOptionPane.ERROR_MESSAGE);
			} else if (!Query.checkMailDB(this.mail.getText())) {
				int reply = JOptionPane.showConfirmDialog(null, "This mail is not registered\nDo you want to Sign In?",
						"Log In Failed",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					new Signin();
					this.dispose();
				}
			} else {
				if (Query.checkLogin(this.mail.getText(), App.hashPassword(String.valueOf(this.passwd.getPassword())))) {
					if (Query.returnUserIdfromMail(this.mail.getText()) == -1) {
						JOptionPane.showMessageDialog(null, "Something went wrong in Log In.\nTry again!", "Log In Failed",
								JOptionPane.ERROR_MESSAGE);
					} else {
						this.dispose();
						new Library(Query.returnUserIdfromMail(this.mail.getText()));
					}
				} else {
					JOptionPane.showMessageDialog(null, "Mail or Password incorrect.\nTry again!",
							"Login Failed", JOptionPane.ERROR_MESSAGE);
				}
			}

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
		}

		// Go Sign In Window
		if (e.getSource() == this.goSignin) {
			this.dispose();
			new Signin();
		}

		// Input Text events 
		if (e.getSource() == this.mail) {
			// Mail
			if (this.mail.getText().equalsIgnoreCase("Enter your mail here")) {
				this.mail.setText("");
				this.mail.setBackground(new Color(0xE9C8A7));
				this.mail.setForeground(new Color(0x2E2E2E));
				this.mail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password
			if (String.valueOf(this.passwd.getPassword()).isEmpty()) {
				this.passwd.setText("Your Password");
				this.passwd.setBackground(new Color(0xEFE5D5));
				this.passwd.setForeground(new Color(0xA6947D));
				this.passwd.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
			}

			this.repaint();
		}

		if (e.getSource() == this.passwd) {
			// Mail
			if (this.mail.getText().isEmpty()) {
				this.mail.setText("Enter your mail here");
				this.mail.setBackground(new Color(0xEFE5D5));
				this.mail.setForeground(new Color(0xA6947D));
				this.mail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
				this.mail.setOpaque(true);
			}

			// Password
			if (String.valueOf(this.passwd.getPassword()).equalsIgnoreCase("Your Password")) {
				this.passwd.setText("");
				this.passwd.setBackground(new Color(0xE9C8A7));
				this.passwd.setForeground(new Color(0x2E2E2E));
				this.passwd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
				this.passwd.setOpaque(true);
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

		// Log In Button Hover
		if (e.getSource() == this.login) {
			this.login.setBounds(240, 415, 130, 65);
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

		// Log In Button Exit Hover
		if (e.getSource() == this.login) {
			this.login.setBounds(235, 420, 130, 65);
			this.login.setIcon(this.loginButtonIcon);
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

		// Log In Button Pressed Event
		if (e.getSource() == this.login) {
			this.login.setBounds(235, 420, 130, 65);
			this.login.setIcon(this.loginButtonPressedIcon);
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

		// Log In Button Released
		if (e.getSource() == this.login) {
			this.login.setBounds(235, 420, 130, 65);
			this.login.setIcon(this.loginButtonIcon);
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

		// Log In Button Released, just in case
		if (e.getSource() == this.login) {
			this.login.setBounds(235, 420, 130, 65);
			this.login.setIcon(this.loginButtonIcon);
			this.repaint();
		}

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		this.setLocation(x - this.xMouse, y + this.yMouse); // TODO It's a little offset, but works for now 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
