package book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Query;

public class BookRow implements MouseListener {
	// ATTRIBUTES
	private int x;
	private int y;
	private int book_id;
	private JLabel bookRow;
	private JLabel tier;
	private JLabel notAdded;

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
		bookRow.setBounds(0, 0, 750, 50);

		// Tier Bordered Icon
		ImageIcon free = new ImageIcon("icons/free_tier_icon_bordered.png");
		ImageIcon pro = new ImageIcon("icons/pro_tier_icon_bordered.png");
		ImageIcon premium = new ImageIcon("icons/premium_tier_icon_bordered.png");
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
		// Add Icon //! This is for now, just for testing
		this.notAdded = new JLabel(new ImageIcon("icons/LIGHT/add_false.png"));
		this.notAdded.setBounds(715, 13, 25, 25);
		bookRowPanel.add(notAdded);

		bookRowPanel.add(bookRow);
		return bookRowPanel;
	}

	// MOUSE LISTENER METHODS 
	@Override
	public void mouseClicked(MouseEvent e) {
		// Click On Panel
		if (e.getSource() == this.bookRow) {
			System.out.println(this.book_id);
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
	}
}