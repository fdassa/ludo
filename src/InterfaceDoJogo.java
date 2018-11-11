import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InterfaceDoJogo extends JFrame {
	public InterfaceDoJogo() {
		this.setSize(768, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adiciona tabuleiro
		this.add(new Tabuleiro());

		// Centraliza na tela do computador
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		final int xPos = (dim.width / 2) - (this.getWidth() / 2);
		final int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		this.setResizable(false);

		// Exibe frame
		this.setVisible(true);
	}
}