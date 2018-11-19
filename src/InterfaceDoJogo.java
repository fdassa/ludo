import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InterfaceDoJogo extends JFrame {
	public InterfaceDoJogo() {
		this.setSize(768, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new Tabuleiro(), BorderLayout.CENTER);
		this.setVisible(true);
		centralizaNaTela();
		final Menu menu = new Menu();
		this.add(menu.getBoxMenu(), BorderLayout.EAST);
	}
	
	private void centralizaNaTela() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		final int xPos = (dim.width / 2) - (this.getWidth() / 2);
		final int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);
	}
}