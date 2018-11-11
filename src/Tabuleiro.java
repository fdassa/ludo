import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel {
	Graphics2D graphics;

	public Tabuleiro() {
		this.setSize(768, 640);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = (Graphics2D) g;
		exibeTabuleiro();
	}

	private void exibeTabuleiro() {
		this.setVisible(true);
		graphics.setBackground(Color.WHITE);
		secionaTabuleiro();
	}

	private void secionaTabuleiro() {
		for (int column = 0; column < 15; column++) {
			for (int line = 0; line < 15; line++) {
				graphics.setPaint(Color.black);
				graphics.draw(new Rectangle2D.Double(40 * line, 40 * column, 40, 40));
			}
		}
	}
}