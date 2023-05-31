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
import windows.MyLibrary;

public class BookCover implements MouseListener, ActionListener {
	// ATTRIBUTES
	private int x;
	private int y;
	private int pWidth;
	private int pHeight;
	private int book_id;
	private int type;
	private JLabel bookCover;
	private JLabel tier;
	private JCheckBox checkAdded;

	// CONSTRUCTORS
	public BookCover(int x, int y, int pWidth, int pHeight, int book_id, int type) {
		this.x = x;
		this.y = y;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.book_id = book_id;
		this.type = type;
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

		// Check case: 1->Add Book ; 2-> Read Book
		switch (this.type) {
			case 1:
			default:
				this.addCheckBoxAdd(bookCoverPanel);
				break;
			case 2:
				this.addCheckBoxRead(bookCoverPanel);
				break;
		}

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
		}

		return coverLabel;
	}

	// Add CheckBox for Add Book
	private void addCheckBoxAdd(JPanel cover) {
		ImageIcon addFalse = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_false.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		ImageIcon addTrue = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_true.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		this.checkAdded = new JCheckBox();
		this.checkAdded.setBackground(new Color(0, 0, 0, 0));
		// Set default icon for checkbox
		this.checkAdded.setIcon(addFalse);
		// Set selected icon when checkbox state is selected
		this.checkAdded.setSelectedIcon(addTrue);
		this.checkAdded.addActionListener(this);
		// Check if the Book is alredy in User's Library
		boolean state = Query.checkIfBookInLibrary(Library.userId, this.book_id);
		this.checkAdded.setSelected(!state);

		this.checkAdded.setBounds(15, 20, 45, 35);
		cover.add(this.checkAdded);
	}

	// Add CheckBox for Read Book
	private void addCheckBoxRead(JPanel cover) {
		ImageIcon notRead = new ImageIcon(
				new ImageIcon("icons/bookmark_not_read.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		ImageIcon read = new ImageIcon(
				new ImageIcon("icons/bookmark_read.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
		this.checkAdded = new JCheckBox();
		this.checkAdded.setBackground(new Color(0, 0, 0, 0));
		// Set default icon for checkbox
		this.checkAdded.setIcon(notRead);
		// Set selected icon when checkbox state is selected
		this.checkAdded.setSelectedIcon(read);
		this.checkAdded.addActionListener(this);
		// Check if the Book is alredy in User's Library
		int isRead = Query.checkIfRead(Library.userId, this.book_id);
		if (isRead == 0) { // Not read case
			this.checkAdded.setSelected(false);
		} else { // Read case
			this.checkAdded.setSelected(true);
		}

		this.checkAdded.setBounds(this.pWidth - 55, 0, 45, 60);
		cover.add(this.checkAdded);
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
			if (this.type == 1) {
				boolean newState = Library.checkBoxClick(this.book_id, !this.checkAdded.isSelected());
				this.checkAdded.setSelected(!newState);
			} else {
				int stateInt = this.checkAdded.isSelected() ? 1 : 0;
				boolean newState = MyLibrary.changeReadStatus(Library.userId, this.book_id, stateInt);
				this.checkAdded.setSelected(newState);
			}

		}
	}
}