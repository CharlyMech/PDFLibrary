package items;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.Query;
import windows.Book;
import windows.Library;

public class BookRow implements MouseListener, ActionListener {
	// ATTRIBUTES
	private int x;
	private int y;
	private int book_id;
	private JLabel bookRow;
	private JLabel tier;
	private JCheckBox checkAdded;

	// CONSTRUCTOR
	public BookRow(int x, int y, int book_id) {
		this.x = x;
		this.y = y;
		this.book_id = book_id;
	}

	public JPanel createMainRow() {
		// Book Info by Its ID
		ArrayList<Object> bookInfo = Query.returnBookTier(this.book_id);

		// Panel
		JPanel bookRowPanel = new JPanel(null);
		bookRowPanel.setBounds(this.x, this.y, 750, 50);
		bookRowPanel.setBackground(new Color(0, 0, 0, 0));
		bookRowPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.bookRow = new JLabel(new ImageIcon("icons/LIGHT/book_row.png"));
		this.bookRow.addMouseListener(this);
		bookRow.setBounds(0, 0, 750, 50);

		// Tier Bordered Icon
		ImageIcon free = new ImageIcon(new ImageIcon("icons/free_tier_icon_bordered.png").getImage().getScaledInstance(25,
				25, Image.SCALE_DEFAULT));
		ImageIcon pro = new ImageIcon(new ImageIcon("icons/pro_tier_icon_bordered.png").getImage().getScaledInstance(25,
				25, Image.SCALE_DEFAULT));
		ImageIcon premium = new ImageIcon(
				new ImageIcon("icons/premium_tier_icon_bordered.png").getImage().getScaledInstance(25,
						25, Image.SCALE_DEFAULT));
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
		this.tier.setBounds(10, 13, 25, 25);
		bookRowPanel.add(tier);

		// Book Title 
		JLabel title = new JLabel(bookInfo.get(1).toString());
		title.setBounds(45, 0, 540, 50);
		title.setBackground(new Color(0, 0, 0, 0));
		title.setForeground(new Color(0x2e2e2e));
		title.setFont(new Font("Roboto", Font.BOLD, 16));
		title.setHorizontalAlignment(JLabel.LEFT);
		bookRowPanel.add(title);

		// Book Author 
		JLabel author = new JLabel(bookInfo.get(2).toString());
		author.setBounds(585, 0, 125, 50);
		author.setBackground(new Color(0, 0, 0, 0));
		author.setForeground(new Color(0x8E765F));
		author.setFont(new Font("Roboto", Font.PLAIN, 9));
		author.setHorizontalAlignment(JLabel.RIGHT);
		bookRowPanel.add(author);

		// TODO -> Query if book is added to User's Library
		// CheckBox
		ImageIcon addFalse = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_false.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		ImageIcon addTrue = new ImageIcon(
				new ImageIcon("icons/LIGHT/add_true.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
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
		this.checkAdded.setBounds(710, 13, 30, 25);
		bookRowPanel.add(this.checkAdded);

		bookRowPanel.add(bookRow);
		return bookRowPanel;
	}

	// MOUSE LISTENER METHODS 
	@Override
	public void mouseClicked(MouseEvent e) {
		// Click On Panel
		if (e.getSource() == this.bookRow) {
			if (Library.nBooksWindows < 5) {
				Library.nBooksWindows++;
				Library.openedWindows.add(new Book(this.book_id));
			} else {
				JOptionPane.showMessageDialog(null,
						"You already have 5 Book windows opened\nClose one if you want to open more",
						"Max Book Windows Reached!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
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