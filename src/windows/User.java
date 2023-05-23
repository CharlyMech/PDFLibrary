package windows;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class User extends JFrame implements MouseListener, MouseMotionListener {
	// ATTRIBUTES
	private int userId;
	private String name;
	private String surname;
	private String mail;
	private String tierName;
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private JPanel main = new JPanel();
	private JLabel userNameSurname;
	private JLabel editUser;
	private JLabel userMail;
	private JLabel userTier;
	private ImageIcon editUserIcon;
	private ImageIcon editUserHoverIcon;
	private JLabel booksAdded;
	private JLabel booksRead;
	private JLabel mostReadCat;
	private JLabel logout;
	private ImageIcon logoutIcon;
	private ImageIcon logoutPressedIcon;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public User(int user_id, String name, String surname, String mail, String tier_name) {
		this.userId = user_id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.tierName = tier_name;

		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(500, 510); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library - User");
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon wlogo = new ImageIcon("icons/user_icon.png");
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
		this.main.setBounds(0, 80, 500, 430);
		this.main.setLayout(null); // Set Layout manager to null
		this.main.setBackground(new Color(0, 0, 0, 0)); // Make the panel transparent

		// USER INFO -
		JPanel userInfo = new JPanel();
		userInfo.setLayout(null);
		userInfo.setBounds(0, 0, 500, 175);
		userInfo.setBackground(new Color(0, 0, 0, 0));

		// User Image -> //! For now default one, not able to change it
		JLabel userImage = new JLabel(new ImageIcon(
				new ImageIcon("img/default_user_image.png").getImage().getScaledInstance(128, 128,
						Image.SCALE_DEFAULT)));
		userImage.setBounds(30, 35, 128, 128);
		userInfo.add(userImage);

		// User Name and Surname
		this.userNameSurname = new JLabel(this.name + " " + this.surname);
		this.userNameSurname.setBounds(172, 55, 270, 30);
		this.userNameSurname.setBackground(new Color(0, 0, 0, 0));
		this.userNameSurname.setForeground(new Color(0x232323));
		this.userNameSurname.setFont(new Font("Roboto", Font.BOLD, 18));
		userInfo.add(userNameSurname);

		// Edit User Info
		this.editUserIcon = new ImageIcon("icons/LIGHT/edit.png");
		this.editUserHoverIcon = new ImageIcon("icons/LIGHT/edit_hover.png");

		this.editUser = new JLabel(this.editUserIcon);
		this.editUser.setBounds(450, 55, 25, 25);
		this.editUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.editUser.addMouseListener(this);
		userInfo.add(this.editUser);

		// User Mail
		this.userMail = new JLabel(this.mail);
		this.userMail.setBounds(175, 85, 270, 30);
		this.userMail.setBackground(new Color(0, 0, 0, 0));
		this.userMail.setForeground(new Color(0x8E765F));
		this.userMail.setFont(new Font("Roboto", Font.PLAIN, 14));
		userInfo.add(this.userMail);

		// User Tier
		ImageIcon freeTierIcon = new ImageIcon("icons/free_tier_icon.png");
		ImageIcon proTierIcon = new ImageIcon("icons/pro_tier_icon.png");
		ImageIcon premiumTierIcon = new ImageIcon("icons/premium_tier_icon.png");

		ImageIcon displayedTierIcon = new ImageIcon(freeTierIcon.getImage());

		switch (this.tierName.toLowerCase()) {
			case "free":
				displayedTierIcon = new ImageIcon(freeTierIcon.getImage());
				break;
			case "pro":
				displayedTierIcon = new ImageIcon(proTierIcon.getImage());
				break;
			case "premium":
				displayedTierIcon = new ImageIcon(premiumTierIcon.getImage());
				break;
		}

		this.userTier = new JLabel();
		this.userTier.setBounds(375, 135, 100, 20);
		this.userTier.setText(this.tierName);
		this.userTier.setIcon(displayedTierIcon); // SAMPLE
		this.userTier.setHorizontalTextPosition(JLabel.RIGHT);
		this.userTier.setHorizontalAlignment(JLabel.CENTER);
		this.userTier.setBackground(new Color(0, 0, 0, 0));
		this.userTier.setForeground(new Color(0x8E765F));
		this.userTier.setFont(new Font("Roboto", Font.BOLD, 14));
		userInfo.add(this.userTier);

		this.main.add(userInfo);
		// - USER INFO

		// USER STATS -
		JPanel userStats = new JPanel();
		userStats.setBounds(0, 175, 500, 175);
		userStats.setLayout(null);
		userStats.setBackground(new Color(0, 0, 0, 0));

		// Title
		ImageIcon statsIcon = new ImageIcon("icons/LIGHT/stats.png");
		JLabel statsTitle = new JLabel();
		statsTitle.setBounds(30, 30, 115, 30);
		statsTitle.setText("User Stats");
		statsTitle.setIcon(statsIcon);
		statsTitle.setHorizontalTextPosition(JLabel.RIGHT);
		statsTitle.setBackground(new Color(0, 0, 0, 0));
		statsTitle.setForeground(new Color(0x232323));
		statsTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		userStats.add(statsTitle);

		ImageIcon dot = new ImageIcon("icons/LIGHT/dot.png");
		// Books Added
		JLabel booksAddedLabel = new JLabel();
		booksAddedLabel.setBounds(60, 75, 130, 25);
		booksAddedLabel.setText("Books Added:");
		booksAddedLabel.setIcon(dot);
		booksAddedLabel.setHorizontalTextPosition(JLabel.RIGHT);
		booksAddedLabel.setBackground(new Color(0, 0, 0, 0));
		booksAddedLabel.setForeground(new Color(0x232323));
		booksAddedLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		userStats.add(booksAddedLabel);

		this.booksAdded = new JLabel("NN");
		this.booksAdded.setBounds(190, 75, 25, 25);
		this.booksAdded.setBackground(new Color(0, 0, 0, 0));
		this.booksAdded.setForeground(new Color(0x8E765F));
		this.booksAdded.setFont(new Font("Roboto", Font.BOLD, 14));
		userStats.add(this.booksAdded);

		// Books Read
		JLabel booksReadLabel = new JLabel();
		booksReadLabel.setBounds(60, 100, 115, 25);
		booksReadLabel.setText("Books Read:");
		booksReadLabel.setIcon(dot);
		booksReadLabel.setHorizontalTextPosition(JLabel.RIGHT);
		booksReadLabel.setBackground(new Color(0, 0, 0, 0));
		booksReadLabel.setForeground(new Color(0x232323));
		booksReadLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		userStats.add(booksReadLabel);

		this.booksRead = new JLabel("NN");
		this.booksRead.setBounds(175, 100, 25, 25);
		this.booksRead.setBackground(new Color(0, 0, 0, 0));
		this.booksRead.setForeground(new Color(0x8E765F));
		this.booksRead.setFont(new Font("Roboto", Font.BOLD, 14));
		userStats.add(this.booksRead);

		// Most Read Category
		JLabel mostReadCatLabel = new JLabel();
		mostReadCatLabel.setBounds(60, 125, 180, 25);
		mostReadCatLabel.setText("Most Read Category:");
		mostReadCatLabel.setIcon(dot);
		mostReadCatLabel.setHorizontalTextPosition(JLabel.RIGHT);
		mostReadCatLabel.setBackground(new Color(0, 0, 0, 0));
		mostReadCatLabel.setForeground(new Color(0x232323));
		mostReadCatLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		userStats.add(mostReadCatLabel);

		this.mostReadCat = new JLabel("most read category name");
		this.mostReadCat.setBounds(240, 125, 240, 25);
		this.mostReadCat.setBackground(new Color(0, 0, 0, 0));
		this.mostReadCat.setForeground(new Color(0x8E765F));
		this.mostReadCat.setFont(new Font("Roboto", Font.ITALIC, 12));
		this.mostReadCat.setHorizontalAlignment(JLabel.CENTER);
		userStats.add(this.mostReadCat);

		this.main.add(userStats);
		// - USER STATS

		// Logout Button
		this.logoutIcon = new ImageIcon("icons/LIGHT/logout_button.png");
		this.logoutPressedIcon = new ImageIcon("icons/LIGHT/logout_button_pressed.png");
		ImageIcon logoutShadowIcon = new ImageIcon("icons/LIGHT/login_button_shadow.png"); // Same Dimensions as Login

		this.logout = new JLabel(this.logoutIcon);
		this.logout.setBounds(190, 350, 120, 55);
		this.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.logout.addMouseListener(this);
		this.main.add(this.logout);

		JLabel logoutShadow = new JLabel(logoutShadowIcon);
		logoutShadow.setBounds(190, 350, 120, 55);
		this.main.add(logoutShadow);

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
			Library.userFlag = false;
			Library.openedWindows.remove(this);
			this.dispose();
		}

		// Edit User Info
		if (e.getSource() == this.editUser) {
			Library.openedWindows.remove(this);
			this.dispose();
			Library.openedWindows.add(new EditUser(this.userId));
		}

		// Logout Click
		if (e.getSource() == this.logout) {
			Library.logout();
			new Login();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Close Button Hover
		if (e.getSource() == this.close) {
			this.close.setBounds(20, 10, 50, 50);
			this.repaint();
		}

		// Edit User Info Hover
		if (e.getSource() == this.editUser) {
			this.editUser.setIcon(this.editUserHoverIcon);
			this.repaint();
		}

		// Logout Hover
		if (e.getSource() == this.logout) {
			this.logout.setBounds(195, 345, 120, 55);
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

		// Edit User Info Hover
		if (e.getSource() == this.editUser) {
			this.editUser.setIcon(this.editUserIcon);
			this.repaint();
		}

		// Logout Exit Hover
		if (e.getSource() == this.logout) {
			this.logout.setBounds(190, 350, 120, 55);
			this.logout.setIcon(this.logoutIcon);
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

		// Logout Pressed
		if (e.getSource() == this.logout) {
			this.logout.setBounds(190, 350, 120, 55);
			this.logout.setIcon(this.logoutPressedIcon);
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

		// Logout Pressed
		if (e.getSource() == this.logout) {
			this.logout.setBounds(190, 350, 120, 55);
			this.logout.setIcon(this.logoutIcon);
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

		// Edit User Info Hover
		if (e.getSource() == this.editUser) {
			this.editUser.setIcon(this.editUserIcon);
			this.repaint();
		}

		// Logout Pressed
		if (e.getSource() == this.logout) {
			this.logout.setBounds(190, 350, 120, 55);
			this.logout.setIcon(this.logoutIcon);
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
