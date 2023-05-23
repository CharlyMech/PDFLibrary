package windows;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import book.BookCover;
import database.Query;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class Book extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener {
	// ATTRIBUTES
	private JPanel topBar = new JPanel(); // Top Bar Panel -> Contains close button and Mouse Motion Listener to move window
	private JLabel close;
	private ImageIcon closeButton;
	private ImageIcon closeButtonPressedIcon;
	private int bookId;
	private int xMouse; // Get mouse X coordinate for mouse events
	private int yMouse; // Get mouse Y coordinate for mouse events

	// CONSTRUCTOR -> Book ID as a needed parameter to display Book INFO
	public Book(int book_id) {
		this.bookId = book_id;
		ArrayList<Object> bookInfo = Query.returnBookInfo(this.bookId);
		String title = bookInfo.get(0).toString();
		String author = bookInfo.get(1).toString();
		String publisher = bookInfo.get(2).toString();
		int year = (int) bookInfo.get(3);
		String lang = bookInfo.get(4).toString();
		String summary = bookInfo.get(5).toString();
		int pages = (int) bookInfo.get(6);
		int tier = (int) bookInfo.get(7);

		// Window -
		this.setLayout(null); // No Layout Manager
		this.setSize(500, 800); // WIDTH - HEIGHT
		this.setResizable(false); // Not Resizable
		this.setUndecorated(true); // No Upper OS bar -> Set Manually
		this.setLocationByPlatform(true); // The window will not start at upper left corner
		this.setTitle(title);
		this.getContentPane().setBackground(new Color(0xEFE5D5)); // Frame BG-Color
		//- Window

		// Set Window Icon 
		ImageIcon wlogo = new ImageIcon("icons/logo_icon.png");
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
				new ImageIcon("img/library_book_topBar.jpg").getImage().getScaledInstance(500, 80,
						Image.SCALE_DEFAULT)));
		topBarLabel.setBounds(0, 0, 500, 80);
		this.topBar.addMouseListener(this); // Add Mouse Listener to topBar Panel
		this.topBar.addMouseMotionListener(this); // Add Mouse Motion Listener to topBar Panel
		this.topBar.add(topBarLabel);

		// - TOP BAR

		// MAIN -
		// Panel
		JPanel main = new JPanel(null);
		main.setPreferredSize(new Dimension(480, 1200)); //! FOR NOW -> Need to do some calcs of height
		main.setBackground(new Color(0, 0, 0, 0));

		// Title Label
		JLabel titleLabel = new JLabel("<html>" + title + "</html>");
		titleLabel.setBounds(25, 25, 430, 100);
		titleLabel.setBackground(new Color(0, 0, 0, 0));
		titleLabel.setForeground(new Color(0x2e2e2e));
		titleLabel.setFont(new Font("Roboto", Font.BOLD, 26));
		titleLabel.setHorizontalAlignment(JLabel.LEFT);
		main.add(titleLabel);

		// Author Label
		JLabel authorLabel = new JLabel("<html>" + author + "</html>");
		authorLabel.setBounds(25, 125, 425, 60);
		authorLabel.setBackground(new Color(0, 0, 0, 0));
		authorLabel.setForeground(new Color(0x8E765F));
		authorLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
		authorLabel.setHorizontalAlignment(JLabel.LEFT);
		authorLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0xA6947D)));
		main.add(authorLabel);

		// BookCover Object
		BookCover bCover = new BookCover(75, 200, 320, 400, this.bookId);
		main.add(bCover.createCover());

		// Summary
		JLabel summaryLabel = new JLabel("Summary:");
		summaryLabel.setBounds(25, 625, 425, 30);
		summaryLabel.setBackground(new Color(0, 0, 0, 0));
		summaryLabel.setForeground(new Color(0x8E765F));
		summaryLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		summaryLabel.setHorizontalAlignment(JLabel.LEFT);
		main.add(summaryLabel);

		JTextArea summaryArea = new JTextArea();
		summaryArea.setText(summary);
		summaryArea.setEditable(false);
		summaryArea.setLineWrap(true);
		summaryArea.setBounds(25, 660, 425, 200);
		summaryArea.setBackground(new Color(0, 0, 0, 0));
		summaryArea.setForeground(new Color(0x8E765F));
		summaryArea.setFont(new Font("Roboto", Font.PLAIN, 14));

		JScrollPane summaryScroller = new JScrollPane(summaryArea);
		main.add(summaryScroller);
		// - MAIN

		// SCROLLPANE -
		JScrollPane scroller = new JScrollPane(main, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBounds(0, 80, 500, 720);
		scroller.setPreferredSize(new Dimension(500, 720));
		scroller.setBackground(new Color(0, 0, 0, 0));
		scroller.setBorder(null);
		scroller.getVerticalScrollBar().setUnitIncrement(20);
		scroller.addMouseWheelListener(this);
		// - SCROLLPANE

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(scroller);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	// MOUSE LISTENER Methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// Close Button event
		if (e.getSource() == this.close) {
			Library.nBooksWindows -= 1;
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Close Button Exit Hover
		if (e.getSource() == this.close) {
			this.close.setIcon(this.closeButton);
			this.close.setBounds(15, 15, 50, 50);
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Close Button Released
		if (e.getSource() == this.close) {
			this.close.setBounds(15, 15, 50, 50);
			this.close.setIcon(this.closeButton);
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

		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		this.setLocation(x - this.xMouse, y + this.yMouse); // TODO It's a little offset, but works for now 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// Mouse Wheel Listener -> Repaint window while users scrolls
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.repaint();
	}

}
