import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Library extends JFrame implements MouseListener, MouseMotionListener {
	// ATTRIBUTES
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeIcon;
	private ImageIcon closePressedIcon;
	private JLabel minimize;
	private ImageIcon minimizeIcon;
	private ImageIcon minimizePressedIcon;
	private JLabel settings;
	protected static boolean settingsFlag = false;
	private ImageIcon settingsIcon;
	private ImageIcon settingsPressedIcon;
	private JLabel user;
	protected static boolean userFlag = false;
	private ImageIcon userIcon;
	private ImageIcon userPressedIcon;
	private JPanel main;
	private JTextField search;
	private JComboBox searchBy;
	private JLabel searchButton;
	private ImageIcon searchButtonIcon;
	private ImageIcon searchButtonPressedIcon;
	private JLabel myLibrary;
	private ImageIcon myLibraryIcon;
	private ImageIcon myLibraryIconPressed;
	private JLabel randomBook;
	private ImageIcon randomBookIcon;
	private ImageIcon randomBookIconPressed;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR
	public Library() {
		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(1200, 800); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle("PDF Library");
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

		// Settings Button
		this.settingsIcon = new ImageIcon("icons/LIGHT/settings.png");
		ImageIcon settingsShadowIcon = new ImageIcon("icons/LIGHT/settings_shadow.png");
		this.settingsPressedIcon = new ImageIcon("icons/LIGHT/settings_pressed.png");

		this.settings = new JLabel();
		this.settings.setIcon(this.settingsIcon);
		this.settings.setBounds(1135, 15, 50, 50);
		this.settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.settings.addMouseListener(this);
		this.topBar.add(this.settings);

		JLabel settingsShadow = new JLabel();
		settingsShadow.setIcon(settingsShadowIcon);
		settingsShadow.setBounds(1135, 15, 50, 50);
		this.topBar.add(settingsShadow);

		// User Button
		this.userIcon = new ImageIcon("icons/LIGHT/user.png");
		ImageIcon userShadowIcon = new ImageIcon("icons/LIGHT/user_shadow.png");
		this.userPressedIcon = new ImageIcon("icons/LIGHT/user_pressed.png");

		this.user = new JLabel();
		this.user.setIcon(this.userIcon);
		this.user.setBounds(1075, 15, 50, 50);
		this.user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.user.addMouseListener(this);
		this.topBar.add(this.user);

		JLabel userShadow = new JLabel();
		userShadow.setIcon(userShadowIcon);
		userShadow.setBounds(1075, 15, 50, 50);
		this.topBar.add(userShadow);

		// Background Label and Image
		JLabel topLabel = new JLabel(new ImageIcon(
				new ImageIcon("img/library_main_topBar.jpg").getImage().getScaledInstance(1200, 80, Image.SCALE_DEFAULT)));
		topLabel.setBounds(0, 0, 1200, 80);
		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel
		this.topBar.add(topLabel);

		// - TOP BAR

		// MAIN PANEL -
		this.main = new JPanel();
		this.main.setBounds(0, 80, 1200, 720);
		this.main.setLayout(null);
		this.main.setBackground(new Color(0xEFE5D5));
		this.main.setOpaque(true);

		// Search Text Field
		this.search = new JTextField();
		this.search.setBounds(225, 35, 545, 50);
		this.search.setText("Search");
		this.search.setFont(new Font("Roboto", Font.PLAIN, 22));
		this.search.setHorizontalAlignment(JTextField.CENTER);
		this.search.setForeground(new Color(0xA6947D));
		this.search.setBackground(new Color(0, 0, 0, 0));
		this.search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		this.search.addMouseListener(this);
		this.main.add(this.search);

		// Search By JComboBox
		String[] searchByItems = { "Title", "Category", "Author", "Year" };
		this.searchBy = new JComboBox<String>(searchByItems);
		this.searchBy.setBounds(775, 35, 135, 50);
		this.searchBy.setBackground(new Color(0xA2845E));
		this.searchBy.setForeground(new Color(0x232323));
		this.searchBy.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
		this.main.add(this.searchBy);

		// Search Button
		this.searchButtonIcon = new ImageIcon("icons/LIGHT/searchButton.png");
		this.searchButtonPressedIcon = new ImageIcon("icons/LIGHT/searchButton_pressed.png");
		this.searchButton = new JLabel(this.searchButtonIcon);
		this.searchButton.setBounds(925, 35, 50, 50);
		this.searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.searchButton.addMouseListener(this);
		this.main.add(this.searchButton);

		ImageIcon searchButtonShadowIcon = new ImageIcon("icons/LIGHT/searchButton_shadow.png");
		JLabel searchButtonShadow = new JLabel(searchButtonShadowIcon);
		searchButtonShadow.setBounds(925, 35, 50, 50);
		this.main.add(searchButtonShadow);

		// - SLIDER PANEL
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBounds(800, 120, 400, 600);
		sliderPanel.setLayout(null); // Set Layout manager to null
		sliderPanel.setBackground(new Color(0, 0, 0, 0));

		// My Library Button
		this.myLibraryIcon = new ImageIcon("icons/LIGHT/my_library.png");
		this.myLibraryIconPressed = new ImageIcon("icons/LIGHT/my_library_pressed.png");
		this.myLibrary = new JLabel();
		this.myLibrary.setIcon(this.myLibraryIcon);
		this.myLibrary.setBounds(122, 23, 175, 55);
		this.myLibrary.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.myLibrary.addMouseListener(this);
		sliderPanel.add(myLibrary);

		ImageIcon myLibraryShadowIcon = new ImageIcon("icons/LIGHT/my_library_shadow.png");
		JLabel myLibraryShadow = new JLabel(myLibraryShadowIcon);
		myLibraryShadow.setBounds(122, 23, 175, 55);
		sliderPanel.add(myLibraryShadow);

		// RANDOM BOOK PANEL -
		JPanel randomBookPanel = new JPanel();
		randomBookPanel.setBounds(40, 100, 320, 400);
		randomBookPanel.setLayout(null); // Set Layout manager to null

		ImageIcon randomBookBGIcon = new ImageIcon("icons/LIGHT/book_frame_320x400.png");
		JLabel randomBookBG = new JLabel(randomBookBGIcon);
		randomBookBG.setBounds(0, 0, 320, 400);
		randomBookPanel.add(randomBookBG);

		// - RANDOM BOOK PANEL
		sliderPanel.add(randomBookPanel);

		// Random book button
		this.randomBookIcon = new ImageIcon("icons/LIGHT/refresh.png");
		this.randomBookIconPressed = new ImageIcon("icons/LIGHT/refresh_pressed.png");
		this.randomBook = new JLabel();
		this.randomBook.setIcon(this.randomBookIcon);
		this.randomBook.setBounds(120, 520, 160, 55);
		this.randomBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.randomBook.addMouseListener(this);
		sliderPanel.add(this.randomBook);

		ImageIcon randomBookShadowIcon = new ImageIcon("icons/LIGHT/refresh_shadow.png");
		JLabel randomBookShadow = new JLabel(randomBookShadowIcon);
		randomBookShadow.setBounds(120, 520, 160, 55);
		sliderPanel.add(randomBookShadow);

		// - SLIDER PANEL

		this.main.add(sliderPanel);

		// - MAIN PANEL

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
			this.dispose();
		}

		// Minimize Button event
		if (e.getSource() == this.minimize) {
			this.setState(Frame.ICONIFIED);
		}

		// Settings Button event
		if (e.getSource() == this.settings && !Library.settingsFlag) {
			Library.settingsFlag = true;
			new Settings();
		}

		// Search Bar Clicked
		if (e.getSource() == this.search) {
			this.search.setText("");
			this.search.setBackground(new Color(0xE9C8A7));
			this.search.setForeground(new Color(0x2E2E2E));
			this.search.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
			this.search.setOpaque(true);
		}
		// Detect if the user has clicked outside the searchbar & it's not empty -> return colors to default
		// TODO -> Implement click outside search bar (also on login&signin) return to placeholder styles

		// Search Button Clicked -> Get Search By item filter
		if (e.getSource() == this.searchButton) { // TEST
			String searchFilter = String.valueOf(this.searchBy.getSelectedItem());
			System.out.println(searchFilter);
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

		// Settings Hover
		if (e.getSource() == this.settings) {
			this.settings.setBounds(1140, 10, 50, 50);
			this.repaint();
		}

		// User Hover
		if (e.getSource() == this.user) {
			this.user.setBounds(1080, 10, 50, 50);
			this.repaint();
		}

		// Search Button Hover
		if (e.getSource() == this.searchButton) {
			this.searchButton.setBounds(930, 30, 50, 50);
			this.repaint();
		}

		// My Library Button Hover
		if (e.getSource() == this.myLibrary) {
			this.myLibrary.setBounds(127, 18, 175, 55);
			this.repaint();
		}

		// Random Book Button Hover 
		if (e.getSource() == this.randomBook) {
			this.randomBook.setBounds(125, 515, 160, 55);
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

		// Settings Exit Hover
		if (e.getSource() == this.settings) {
			this.settings.setIcon(this.settingsIcon);
			this.settings.setBounds(1135, 15, 50, 50);
			this.repaint();
		}

		// User Exit Hover
		if (e.getSource() == this.user) {
			this.user.setIcon(this.userIcon);
			this.user.setBounds(1075, 15, 50, 50);
			this.repaint();
		}

		// Search Button Exit Hover
		if (e.getSource() == this.searchButton) {
			this.searchButton.setBounds(925, 35, 50, 50);
			this.searchButton.setIcon(this.searchButtonIcon);
			this.repaint();
		}

		// My Library Button Exit Hover
		if (e.getSource() == this.myLibrary) {
			this.myLibrary.setBounds(122, 23, 175, 55);
			this.myLibrary.setIcon(this.myLibraryIcon);
			this.repaint();
		}

		// Random Book Button Exit Hover 
		if (e.getSource() == this.randomBook) {
			this.randomBook.setBounds(120, 520, 160, 55);
			this.randomBook.setIcon(randomBookIcon);
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

		// Settings Pressed
		if (e.getSource() == this.settings) {
			this.settings.setBounds(1135, 15, 50, 50);
			this.settings.setIcon(this.settingsPressedIcon);
			this.repaint();
		}

		// User Pressed
		if (e.getSource() == this.user) {
			this.user.setBounds(1075, 15, 50, 50);
			this.user.setIcon(this.userPressedIcon);
			this.repaint();
		}

		// Search Button Pressed
		if (e.getSource() == this.searchButton) {
			this.searchButton.setBounds(925, 35, 50, 50);
			this.searchButton.setIcon(this.searchButtonPressedIcon);
			this.repaint();
		}

		// My Library Button Pressed
		if (e.getSource() == this.myLibrary) {
			this.myLibrary.setBounds(122, 23, 175, 55);
			this.myLibrary.setIcon(this.myLibraryIconPressed);
			this.repaint();
		}

		// Random Book Button Pressed
		if (e.getSource() == this.randomBook) {
			this.randomBook.setBounds(120, 520, 160, 55);
			this.randomBook.setIcon(randomBookIconPressed);
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

		// Settings Released
		if (e.getSource() == this.settings) {
			this.settings.setBounds(1135, 15, 50, 50);
			this.settings.setIcon(this.settingsIcon);
			this.repaint();
		}

		// User Released
		if (e.getSource() == this.user) {
			this.user.setBounds(1075, 15, 50, 50);
			this.user.setIcon(this.userIcon);
			this.repaint();
		}

		// Search Button Pressed
		if (e.getSource() == this.searchButton) {
			this.searchButton.setBounds(925, 35, 50, 50);
			this.searchButton.setIcon(this.searchButtonIcon);
			this.repaint();
		}

		// My Library Button Released
		if (e.getSource() == this.myLibrary) {
			this.myLibrary.setBounds(122, 23, 175, 55);
			this.myLibrary.setIcon(this.myLibraryIcon);
			this.repaint();
		}

		// Random Book Button Released
		if (e.getSource() == this.randomBook) {
			this.randomBook.setBounds(120, 520, 160, 55);
			this.randomBook.setIcon(this.randomBookIcon);
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

		// In case the user tries to drag on settings
		if (e.getSource() == this.settings) {
			this.settings.setBounds(1135, 15, 50, 50);
			this.settings.setIcon(this.settingsIcon);
			this.repaint();
		}

		// In case the user tries to drag on user
		if (e.getSource() == this.user) {
			this.user.setBounds(1075, 15, 50, 50);
			this.user.setIcon(this.userIcon);
			this.repaint();
		}

		// In case the user tries to drag on search button
		if (e.getSource() == this.searchButton) {
			this.searchButton.setBounds(925, 35, 50, 50);
			this.searchButton.setIcon(this.searchButtonIcon);
			this.repaint();
		}

		// In case the user tries to drag on my library button
		if (e.getSource() == this.myLibrary) {
			this.myLibrary.setBounds(122, 23, 175, 55);
			this.myLibrary.setIcon(this.myLibraryIcon);
			this.repaint();
		}

		// In case the user tries to drag on random book
		if (e.getSource() == this.randomBook) {
			this.randomBook.setBounds(120, 520, 160, 55);
			this.randomBook.setIcon(this.randomBookIcon);
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
