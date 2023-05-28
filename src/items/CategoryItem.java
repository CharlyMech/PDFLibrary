package items;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryItem {
	// ATTRIBUTES
	private final int WIDTH = 150;
	private final int HEIGHT = 55;
	private int x;
	private String name;

	// CONSTRUCTOR
	public CategoryItem(int x, String name) {
		this.x = x;
		this.name = name;
	}

	public JPanel createItem() {
		JPanel catItemPanel = new JPanel(null);
		catItemPanel.setBounds(this.x, 0, this.WIDTH, this.HEIGHT);
		catItemPanel.setBackground(new Color(0, 0, 0, 0));

		ImageIcon catItemIcon = new ImageIcon("icons/LIGHT/category_item.png");
		JLabel catItemLabel = new JLabel(catItemIcon);
		catItemLabel.setBounds(0, 0, this.WIDTH, this.HEIGHT);

		JLabel catName = new JLabel(this.name);
		catName.setBounds(10, 10, this.WIDTH - 20, this.HEIGHT - 20);
		catName.setHorizontalAlignment(JLabel.CENTER);
		catName.setVerticalAlignment(JLabel.CENTER);

		catItemPanel.add(catName);
		catItemPanel.add(catItemLabel);

		return catItemPanel;
	}
}