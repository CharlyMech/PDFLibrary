package windows;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Query;

public class MyLibrary extends JFrame implements MouseListener, MouseMotionListener {
	// ATTRIBUTES
	private int userId;
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeIcon;
	private ImageIcon closePressedIcon;
	private JLabel minimize;
	private ImageIcon minimizeIcon;
	private ImageIcon minimizePressedIcon;
	private JLabel user;
	private ImageIcon userIcon;
	private ImageIcon userPressedIcon;
	private JLabel goBack;
	private ImageIcon goBackIcon;
	private ImageIcon goBackIconPressed;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public MyLibrary(int user_id) {
		// Store USER_ID value for session
		this.userId = user_id;

		// Add JFrame to Array
		Library.openedWindows.add(this);

		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(1000, 800); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("ToBook App");
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon logoIcon = new ImageIcon("icons/logo_icon.png");
		this.setIconImage(logoIcon.getImage());

		// TOP BAR -
		this.topBar.setBounds(0, 0, 1200, 80);
		this.topBar.setLayout(null); // Set Layout manager to null

		// Close Button
		this.closeIcon = new ImageIcon("icons/LIGHT/close_button.png");
		this.closePressedIcon = new ImageIcon("icons/LIGHT/close_button_pressed.png");
		ImageIcon closeShadowIcon = new ImageIcon("icons/LIGHT/close_shadow.png");

		this.close = new JLabel(this.closeIcon); // CLOSE
		this.close.setBounds(15, 15, 50, 50);
		this.close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.close.addMouseListener(this); // Add Mouse Listener for close operation
		this.topBar.add(this.close);

		JLabel closeShadow = new JLabel(closeShadowIcon); // SHADOW
		closeShadow.setBounds(15, 15, 50, 50);
		this.topBar.add(closeShadow);

		// Minimize Button
		this.minimizeIcon = new ImageIcon("icons/LIGHT/minimize.png");
		this.minimizePressedIcon = new ImageIcon("icons/LIGHT/minimize_pressed.png");
		ImageIcon minimizeShadowIcon = new ImageIcon("icons/LIGHT/close_shadow.png"); // Same size as Close Button

		this.minimize = new JLabel(this.minimizeIcon);
		this.minimize.setBounds(95, 15, 50, 50);
		this.minimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set Mouse as if a button was
		this.minimize.addMouseListener(this); // Add Mouse Listener for minimize operation
		this.topBar.add(this.minimize);

		JLabel minimizeShadow = new JLabel(minimizeShadowIcon); // SHADOW
		minimizeShadow.setBounds(95, 15, 50, 50);
		this.topBar.add(minimizeShadow);

		// User Button //!
		this.userIcon = new ImageIcon("icons/LIGHT/user.png");
		ImageIcon userShadowIcon = new ImageIcon("icons/LIGHT/user_shadow.png");
		this.userPressedIcon = new ImageIcon("icons/LIGHT/user_pressed.png");

		this.user = new JLabel();
		this.user.setIcon(this.userIcon);
		this.user.setBounds(935, 15, 50, 50);
		this.user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.user.addMouseListener(this);
		this.topBar.add(this.user);

		JLabel userShadow = new JLabel();
		userShadow.setIcon(userShadowIcon);
		userShadow.setBounds(935, 15, 50, 50);
		this.topBar.add(userShadow);

		// Background Label and Image
		JLabel topLabel = new JLabel(new ImageIcon(
				new ImageIcon("img/library_myLibrary_topBar.jpg").getImage().getScaledInstance(1000, 80,
						Image.SCALE_DEFAULT)));
		topLabel.setBounds(0, 0, 1000, 80);
		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel
		this.topBar.add(topLabel);

		// - TOP BAR

		// MAIN -
		JPanel main = new JPanel(null);
		main.setBounds(0, 80, 1000, 720);
		main.setBackground(new Color(0, 0, 0, 0));

		// Go Back to Library button
		this.goBackIcon = new ImageIcon("icons/LIGHT/back_library.png");
		this.goBackIconPressed = new ImageIcon("icons/LIGHT/back_library_pressed.png");
		this.goBack = new JLabel(this.goBackIcon);
		this.goBack.setBounds(15, 15, 75, 50);
		this.goBack.addMouseListener(this);
		this.goBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		main.add(this.goBack);

		ImageIcon goBackIconShadow = new ImageIcon("icons/LIGHT/back_library_shadow.png");
		JLabel goBackShadow = new JLabel(goBackIconShadow);
		goBackShadow.setBounds(15, 15, 75, 50);
		main.add(goBackShadow);

		// User Name Label
		ArrayList<Object> userInfo = Query.returnUserInfo(this.userId);
		String displayName = "<html>" + userInfo.get(0).toString() + " " + userInfo.get(1).toString()
				+ "'s Library</html>";
		JLabel userNameSurname = new JLabel(displayName);
		userNameSurname.setBounds(175, 0, 650, 80);
		userNameSurname.setHorizontalAlignment(JLabel.CENTER);
		userNameSurname.setVerticalAlignment(JLabel.CENTER);
		userNameSurname.setBackground(new Color(0, 0, 0, 0));
		userNameSurname.setForeground(new Color(0x2e2e2e));
		userNameSurname.setFont(new Font("Roboto", Font.BOLD, 26));
		main.add(userNameSurname);

		// - MAIN

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(main);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);

	}

	// MOUSE LISTENER Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			Library.openedWindows.remove(this);
			this.dispose();
		}

		// Minimize Button event
		if (e.getSource() == this.minimize) {
			this.setState(Frame.ICONIFIED);
		}

		// User Button event
		if (e.getSource() == this.user && !Library.userFlag) {
			ArrayList<Object> userInfo = Query.returnUserInfo(Library.userId);
			if (userInfo.size() != 0) {
				String name = userInfo.get(0).toString();
				String surname = userInfo.get(1).toString();
				String mail = userInfo.get(2).toString();
				String tier_name = userInfo.get(3).toString();
				Library.userFlag = true;
				Library.openedWindows.add(new User(Library.userId, name, surname, mail, tier_name));
			} else {
				int reply = JOptionPane.showConfirmDialog(null, "Something went wrong\nTry again later",
						"Connection lost",
						JOptionPane.DEFAULT_OPTION);

				if (reply == JOptionPane.OK_OPTION) {
					Library.logout();
				}
			}

		}

		// Go Back Click
		if (e.getSource() == this.goBack) {
			Library.openedWindows.remove(this);
			Library.openedWindows.add(new Library(this.userId));
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

		// Minimize Button Hover
		if (e.getSource() == this.minimize) {
			this.minimize.setBounds(100, 10, 50, 50);
			this.repaint();
		}

		// User Hover
		if (e.getSource() == this.user) {
			this.user.setBounds(940, 10, 50, 50);
			this.repaint();
		}

		// Go Back Hover
		if (e.getSource() == this.goBack) {
			this.goBack.setBounds(20, 10, 75, 50);
			this.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Close Button Exit Hover
		if (e.getSource() == this.close) {
			this.close.setIcon(this.closeIcon);
			this.close.setBounds(15, 15, 50, 50);
			this.repaint();
		}

		// Minimize Button Exit Hover
		if (e.getSource() == this.minimize) {
			this.minimize.setIcon(this.minimizeIcon);
			this.minimize.setBounds(95, 15, 50, 50);
			this.repaint();
		}

		// User Exit Hover
		if (e.getSource() == this.user) {
			this.user.setIcon(this.userIcon);
			this.user.setBounds(935, 15, 50, 50);
			this.repaint();
		}

		// Go Back Exit Hover
		if (e.getSource() == this.goBack) {
			this.goBack.setIcon(this.goBackIcon);
			this.goBack.setBounds(15, 15, 75, 50);
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
			this.close.setIcon(this.closePressedIcon);
			this.repaint();
		}

		// Minimize Button Pressed
		if (e.getSource() == this.minimize) {
			this.minimize.setIcon(this.minimizePressedIcon);
			this.minimize.setBounds(95, 15, 50, 50);
			this.repaint();
		}

		// User Pressed
		if (e.getSource() == this.user) {
			this.user.setBounds(935, 15, 50, 50);
			this.user.setIcon(this.userPressedIcon);
			this.repaint();
		}

		// Go Back Pressed
		if (e.getSource() == this.goBack) {
			this.goBack.setIcon(this.goBackIconPressed);
			this.goBack.setBounds(15, 15, 75, 50);
			this.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Close Button Released
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeIcon);
			this.repaint();
		}

		// Minimize Button Pressed
		if (e.getSource() == this.minimize) {
			this.minimize.setIcon(this.minimizeIcon);
			this.minimize.setBounds(95, 15, 50, 50);
			this.repaint();
		}

		// User Released
		if (e.getSource() == this.user) {
			this.user.setBounds(935, 15, 50, 50);
			this.user.setIcon(this.userIcon);
			this.repaint();
		}

		// Go Back Released
		if (e.getSource() == this.goBack) {
			this.goBack.setIcon(this.goBackIcon);
			this.goBack.setBounds(15, 15, 75, 50);
			this.repaint();
		}
	}

	// MOUSE MOTION LISTENER Methods
	@Override
	public void mouseDragged(MouseEvent e) {
		// In case the user tries to drag on close button
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeIcon);
			this.repaint();
		}

		// In case the user tries to drag on minimize button
		if (e.getSource() == this.minimize) {
			this.minimize.setIcon(this.minimizeIcon);
			this.minimize.setBounds(95, 15, 50, 50);
			this.repaint();
		}

		// In case the user tries to drag on user
		if (e.getSource() == this.user) {
			this.user.setBounds(935, 15, 50, 50);
			this.user.setIcon(this.userIcon);
			this.repaint();
		}

		// In case the user tries to drag on go back
		if (e.getSource() == this.goBack) {
			this.goBack.setIcon(this.goBackIcon);
			this.goBack.setBounds(15, 15, 75, 50);
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
