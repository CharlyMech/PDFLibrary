package windows;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

import database.Query;
import items.BookCover;
import items.CategoryItem;

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
		main.setPreferredSize(new Dimension(480, 1175)); //! FOR NOW -> Need to do some calcs of height
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
		BookCover bCover = new BookCover(75, 200, 320, 400, this.bookId, 1);
		main.add(bCover.createCover());

		// Summary
		JLabel summaryLabel = new JLabel("Summary:");
		summaryLabel.setBounds(25, 625, 425, 30);
		summaryLabel.setBackground(new Color(0, 0, 0, 0));
		summaryLabel.setForeground(new Color(0x8E765F));
		summaryLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		summaryLabel.setHorizontalAlignment(JLabel.LEFT);
		main.add(summaryLabel);

		JTextArea summaryArea = new JTextArea(summary);
		summaryArea.setEditable(false);
		summaryArea.setLineWrap(true);
		summaryArea.setWrapStyleWord(true);
		summaryArea.setBackground(new Color(0, 0, 0, 0));
		summaryArea.setForeground(new Color(0x8E765F));
		summaryArea.setFont(new Font("Roboto", Font.PLAIN, 14));

		JScrollPane summaryScroller = new JScrollPane(summaryArea);
		summaryScroller.setBounds(25, 660, 425, 200);
		summaryScroller.setBackground(new Color(0, 0, 0, 0));
		summaryScroller.setPreferredSize(new Dimension(425, 200));
		summaryScroller.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0xA6947D)));
		summaryScroller.addMouseWheelListener(this);
		summaryScroller.getVerticalScrollBar().setUnitIncrement(10);
		// Bars Styling
		summaryScroller.getVerticalScrollBar().getComponent(0).setBackground(new Color(0xA2845E)); // Down bg
		summaryScroller.getVerticalScrollBar().getComponent(0).setForeground(new Color(0x2e2e2e)); // Down fg
		summaryScroller.getVerticalScrollBar().getComponent(1).setBackground(new Color(0xA2845E)); // Up bg
		summaryScroller.getVerticalScrollBar().getComponent(1).setForeground(new Color(0x2e2e2e)); // Up fg
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(0xA2845E))); // Scroller
		summaryScroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.trackColor = new Color(0xE0D5BF);
				this.thumbColor = new Color(0xA2845E);
			}
		}); // Scroller

		main.add(summaryScroller);

		// Details
		JLabel detailsLabel = new JLabel("Details:");
		detailsLabel.setBounds(25, 875, 425, 30);
		detailsLabel.setBackground(new Color(0, 0, 0, 0));
		detailsLabel.setForeground(new Color(0x8E765F));
		detailsLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		detailsLabel.setHorizontalAlignment(JLabel.LEFT);
		main.add(detailsLabel);

		// DETAILS PANEL -
		JPanel detailsPanel = new JPanel(null);
		detailsPanel.setBounds(25, 910, 425, 115);
		detailsPanel.setBackground(new Color(0, 0, 0, 0));

		// Year
		JLabel yearTitle = new JLabel("Year:", new ImageIcon("icons/LIGHT/dot.png"), JLabel.LEFT);
		yearTitle.setBounds(25, 0, 125, 25);
		yearTitle.setBackground(new Color(0, 0, 0, 0));
		yearTitle.setForeground(new Color(0x8E765F));
		yearTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		detailsPanel.add(yearTitle);

		JLabel yearValue = new JLabel(String.valueOf(year));
		yearValue.setBounds(175, 0, 125, 25);
		yearValue.setBackground(new Color(0, 0, 0, 0));
		yearValue.setForeground(new Color(0x2e2e2e));
		yearValue.setFont(new Font("Roboto", Font.PLAIN, 14));
		detailsPanel.add(yearValue);

		// Editor
		JLabel editorTitle = new JLabel("Editor:", new ImageIcon("icons/LIGHT/dot.png"), JLabel.LEFT);
		editorTitle.setBounds(25, 30, 125, 25);
		editorTitle.setBackground(new Color(0, 0, 0, 0));
		editorTitle.setForeground(new Color(0x8E765F));
		editorTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		detailsPanel.add(editorTitle);

		JLabel editorValue = new JLabel(publisher);
		editorValue.setBounds(175, 30, 125, 25);
		editorValue.setBackground(new Color(0, 0, 0, 0));
		editorValue.setForeground(new Color(0x2e2e2e));
		editorValue.setFont(new Font("Roboto", Font.PLAIN, 14));
		detailsPanel.add(editorValue);

		// Pages
		JLabel pagesTitle = new JLabel("Pages:", new ImageIcon("icons/LIGHT/dot.png"), JLabel.LEFT);
		pagesTitle.setBounds(25, 60, 125, 25);
		pagesTitle.setBackground(new Color(0, 0, 0, 0));
		pagesTitle.setForeground(new Color(0x8E765F));
		pagesTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		detailsPanel.add(pagesTitle);

		JLabel pagesValue = new JLabel(String.valueOf(pages));
		pagesValue.setBounds(175, 60, 125, 25);
		pagesValue.setBackground(new Color(0, 0, 0, 0));
		pagesValue.setForeground(new Color(0x2e2e2e));
		pagesValue.setFont(new Font("Roboto", Font.PLAIN, 14));
		detailsPanel.add(pagesValue);

		// Language
		JLabel langTitle = new JLabel("Language:", new ImageIcon("icons/LIGHT/dot.png"), JLabel.LEFT);
		langTitle.setBounds(25, 90, 125, 25);
		langTitle.setBackground(new Color(0, 0, 0, 0));
		langTitle.setForeground(new Color(0x8E765F));
		langTitle.setFont(new Font("Roboto", Font.BOLD, 16));
		detailsPanel.add(langTitle);

		JLabel langValue = new JLabel(lang);
		langValue.setBounds(175, 90, 125, 25);
		langValue.setBackground(new Color(0, 0, 0, 0));
		langValue.setForeground(new Color(0x2e2e2e));
		langValue.setFont(new Font("Roboto", Font.PLAIN, 14));
		detailsPanel.add(langValue);

		main.add(detailsPanel);
		// - DETAILS PANEL

		// Categories Label
		JLabel catLabel = new JLabel("Categories");
		catLabel.setBounds(25, 1050, 425, 30);
		catLabel.setBackground(new Color(0, 0, 0, 0));
		catLabel.setForeground(new Color(0x8E765F));
		catLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		catLabel.setHorizontalAlignment(JLabel.LEFT);
		main.add(catLabel);

		// CATEGORIES PANEL -
		ArrayList<String> goodNames = Book.returnGoodCatNames(this.bookId);

		JPanel catsPanel = new JPanel(null);
		catsPanel.setPreferredSize(new Dimension(goodNames.size() * 150, 75));
		catsPanel.setBackground(new Color(0, 0, 0, 0));

		JScrollPane catsScroller = new JScrollPane(catsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		catsScroller.setBounds(25, 1090, 425, 75);
		catsScroller.setPreferredSize(new Dimension(425, 75));
		catsScroller.setBorder(null);
		catsScroller.setBackground(new Color(0, 0, 0, 0));

		for (String n : goodNames) {
			int xCoor = goodNames.indexOf(n) * 150;
			catsPanel.add(new CategoryItem(xCoor, n).createItem());
		}

		// Bars Styling
		catsScroller.getHorizontalScrollBar().getComponent(0).setBackground(new Color(0xA2845E)); // Down bg
		catsScroller.getHorizontalScrollBar().getComponent(0).setForeground(new Color(0x2e2e2e)); // Down fg
		catsScroller.getHorizontalScrollBar().getComponent(1).setBackground(new Color(0xA2845E)); // Up bg
		catsScroller.getHorizontalScrollBar().getComponent(1).setForeground(new Color(0x2e2e2e)); // Up fg
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(0xA2845E))); // Scroller
		catsScroller.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.trackColor = new Color(0xE0D5BF);
				this.thumbColor = new Color(0xA2845E);
			}
		}); // Scroller

		main.add(catsScroller);
		// - CATEGORIES PANEL

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

		// Bars Styling
		scroller.getVerticalScrollBar().getComponent(0).setBackground(new Color(0xA2845E)); // Down bg
		scroller.getVerticalScrollBar().getComponent(0).setForeground(new Color(0x2e2e2e)); // Down fg
		scroller.getVerticalScrollBar().getComponent(1).setBackground(new Color(0xA2845E)); // Up bg
		scroller.getVerticalScrollBar().getComponent(1).setForeground(new Color(0x2e2e2e)); // Up fg
		UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(0xA2845E))); // Scroller
		scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.trackColor = new Color(0xE0D5BF);
				this.thumbColor = new Color(0xA2845E);
			}
		}); // Scroller
		// - SCROLLPANE

		// ADD ELEMENTS TO FRAME -
		this.add(this.topBar);
		this.add(scroller);
		// - ADD ELEMENTS TO FRAME

		// Set Window Visible
		this.setVisible(true);
	}

	private static ArrayList<String> returnGoodCatNames(int book_id) {
		ArrayList<String> names = Query.returnBookCategoriesName(book_id);
		for (String n : names) {
			String goodNameString = "";
			String[] tt = n.replace("_", " ").split(" ");
			for (String t : tt) {
				String capital = t.substring(0, 1).toUpperCase();
				goodNameString += capital + t.substring(1) + " ";
			}
			names.set(names.indexOf(n), goodNameString);
		}

		return names;
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
