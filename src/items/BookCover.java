package items;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Query;
import windows.Library;

public class BookCover implements MouseListener, ActionListener {
	// ATTRIBUTES
	private int x;
	private int y;
	private int pWidth;
	private int pHeight;
	private int book_id;
	private JLabel bookCover;
	private JLabel tier;
	private JCheckBox checkAdded;

	// CONSTRUCTORS
	public BookCover(int x, int y, int pWidth, int pHeight, int book_id) {
		this.x = x;
		this.y = y;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.book_id = book_id;
	}

	public BookCover(int book_id) {
		this.book_id = book_id;
	}

	public JPanel createCover() {
		// Book Info by Its ID
		ArrayList<Object> bookInfo = Query.returnBookTier(this.book_id);

		// Panel &  BG Label with Icon
		JPanel bookCoverPanel = new JPanel(null);
		bookCoverPanel.setBounds(this.x, this.y, this.pWidth, this.pHeight);
		bookCoverPanel.setBackground(new Color(0, 0, 0, 0));
		bookCoverPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // ! This must be in myLibrary and random book, not in book window, for now let it be like this

		ImageIcon bookFrameIcon = new ImageIcon(
				new ImageIcon("icons/LIGHT/book_frame.png").getImage().getScaledInstance(this.pWidth, this.pHeight,
						Image.SCALE_DEFAULT));
		this.bookCover = new JLabel(bookFrameIcon);
		bookCover.setBounds(0, 0, this.pWidth, this.pHeight);

		// Tier Bordered Icon
		//! Scales for now -> Later implement method to set proper width&height depending on where is instanciated
		ImageIcon free = new ImageIcon(new ImageIcon("icons/free_tier_icon_bordered.png").getImage().getScaledInstance(35,
				35, Image.SCALE_DEFAULT));
		ImageIcon pro = new ImageIcon(new ImageIcon("icons/pro_tier_icon_bordered.png").getImage().getScaledInstance(35,
				35, Image.SCALE_DEFAULT));
		ImageIcon premium = new ImageIcon(
				new ImageIcon("icons/premium_tier_icon_bordered.png").getImage().getScaledInstance(35,
						35, Image.SCALE_DEFAULT));
		this.tier = new JLabel();
		switch ((int) bookInfo.get(0)) {
			case -1:
			case 1:
				this.tier.setIcon(free);
				break;
			case 2:
				this.tier.setIcon(pro);
				break;
			case 3:
				this.tier.setIcon(premium);
				break;
		}
		this.tier.setBounds(20, 345, 35, 35);
		bookCoverPanel.add(tier);

		// TODO -> Query if book is added to User's Library
		// ! Again scale is now only for Book object
		ImageIcon addFalse = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_false.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		ImageIcon addTrue = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_true.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		this.checkAdded = new JCheckBox();
		this.checkAdded.setBackground(new Color(0, 0, 0, 0));
		// Set default icon for checkbox
		this.checkAdded.setIcon(addFalse); //! Dafault until DB query
		// Set selected icon when checkbox state is selected
		this.checkAdded.setSelectedIcon(addTrue);
		this.checkAdded.addActionListener(this);
		// Check if the Book is alredy in User's Library
		boolean state = Query.checkIfBookInLibrary(Library.userId, this.book_id);
		this.checkAdded.setSelected(!state);

		// this.notAdded = new JLabel(new ImageIcon("icons/LIGHT/add_false.png"));
		this.checkAdded.setBounds(15, 20, 45, 35);
		bookCoverPanel.add(this.checkAdded);

		bookCoverPanel.add(createImageFromUrl(this.book_id));
		bookCoverPanel.add(bookCover);
		return bookCoverPanel;
	}

	private JLabel createImageFromUrl(int book_id) {
		JLabel coverLabel = new JLabel();
		coverLabel.setBounds(10, 10, pWidth - 20, pHeight - 20); // General Image Size & positioning

		try {
			Image coverImage = ImageIO.read(new URL(Query.returnBookCover(book_id)));
			ImageIcon coverIcon = new ImageIcon(
					new ImageIcon(coverImage).getImage().getScaledInstance(pWidth - 20, pHeight - 20, Image.SCALE_DEFAULT));

			coverLabel.setIcon(coverIcon);
		} catch (Exception e) {
			System.out.println(e);
		}

		return coverLabel;
	}

	// GETTER - BookID
	public int getBookId() {
		return this.book_id;
	}

	// MOUSE LISTENER METHODS
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	// Action Listener Method
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.checkAdded) {
			boolean newState = Library.checkBoxClick(this.book_id, !this.checkAdded.isSelected());
			this.checkAdded.setSelected(!newState);
		}
	}
}