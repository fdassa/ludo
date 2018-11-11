import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
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
		preencheTodosAbrigos();
		preencheTodasCasasIniciais();
		preencheTodasUltimasCasas();
	}

	private void secionaTabuleiro() {
		for (int column = 0; column < 15; column++) {
			for (int line = 0; line < 15; line++) {
				graphics.setPaint(Color.BLACK);
				graphics.draw(new Rectangle2D.Double(40 * line, 40 * column, 40, 40));
			}
		}
	}

	private void preencheTodosAbrigos() {
		preencheAbrigo(240, 40);
		preencheAbrigo(40, 320);
		preencheAbrigo(520, 240);
		preencheAbrigo(320, 520);
	}

	private void preencheTodasCasasIniciais() {
		preencheCasaInicial(0, 0, Color.GREEN);
		preencheCasaInicial(0, 360, Color.YELLOW);
		preencheCasaInicial(360, 0, Color.RED);
		preencheCasaInicial(360, 360, Color.BLUE);
	}
	
	private void preencheTodasUltimasCasas() {
		final int[] xVerde = { 240, 240, 300 };
		final int[] yVerde = { 360, 240, 300 };
		final int[] xAmarelo = { 360, 240, 300 };
		final int[] yAmarelo = { 360, 360, 300 };
		final int[] xVermelho = { 240, 360, 300 };
		final int[] yVermelho = { 240, 240, 300 };
		final int[] xAzul = { 360, 360, 300 };
		final int[] yAzul = { 360, 240, 300 };

		preencheUltimaCasa(xVerde, yVerde, Color.GREEN);
		preencheUltimaCasa(xAmarelo, yAmarelo, Color.YELLOW);
		preencheUltimaCasa(xVermelho, yVermelho, Color.RED);
		preencheUltimaCasa(xAzul, yAzul, Color.BLUE);
	}

	private void preencheAbrigo(int x, int y) {
		graphics.setPaint(Color.BLACK);
		graphics.fill(new Rectangle2D.Double(x, y, 40, 40));
	}

	private void preencheCasaInicial(int x, int y, Color cor) {
		graphics.setPaint(cor);
		graphics.fill(new Rectangle2D.Double(x, y, 240, 240));
		graphics.setPaint(Color.BLACK);
		graphics.draw(new Rectangle2D.Double(x, y, 240, 240));
		preenchePeca(x + 40, y + 40);
		preenchePeca(x + 160, y + 40);
		preenchePeca(x + 40, y + 160);
		preenchePeca(x + 160, y + 160);
	}
 
	private void preenchePeca(int x, int y) {
		graphics.setPaint(Color.WHITE);
		graphics.fill(new Ellipse2D.Double(x, y, 40, 40));
		graphics.setPaint(Color.BLACK);
		graphics.draw(new Ellipse2D.Double(x, y, 40, 40));
	}
	
	private void preencheUltimaCasa(int[] x, int[] y, Color cor) {
		graphics.setPaint(cor);
		graphics.fillPolygon(x, y, 3);
		graphics.setPaint(Color.BLACK);
		graphics.drawPolygon(x, y, 3);
	}
}