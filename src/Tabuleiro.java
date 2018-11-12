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
		this.setSize(640, 640);
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
		preencheTodasCasasFinais();
		preencheTodasRetasFinais();
		preencheTodasCasasSaidas();
	}

	private void secionaTabuleiro() {
		for (int coluna = 0; coluna < 15; coluna++) {
			for (int linha = 0; linha < 15; linha++) {
				graphics.setPaint(Color.BLACK);
				graphics.draw(new Rectangle2D.Double(40 * linha, 40 * coluna, 40, 40));
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
		preencheCasaInicial(0, 0, Color.RED);
		preencheCasaInicial(0, 360, Color.BLUE);
		preencheCasaInicial(360, 0, Color.GREEN);
		preencheCasaInicial(360, 360, Color.YELLOW);
	}

	private void preencheTodasCasasFinais() {
		final int[] xVermelho = { 240, 240, 300 };
		final int[] yVermelho = { 360, 240, 300 };
		final int[] xAzul = { 360, 240, 300 };
		final int[] yAzul = { 360, 360, 300 };
		final int[] xVerde = { 240, 360, 300 };
		final int[] yVerde = { 240, 240, 300 };
		final int[] xAmarelo = { 360, 360, 300 };
		final int[] yAmarelo = { 360, 240, 300 };

		preencheUltimaCasa(xVermelho, yVermelho, Color.RED);
		preencheUltimaCasa(xAzul, yAzul, Color.BLUE);
		preencheUltimaCasa(xVerde, yVerde, Color.GREEN);
		preencheUltimaCasa(xAmarelo, yAmarelo, Color.YELLOW);
	}

	private void preencheTodasRetasFinais() {
		for (int coluna = 0; coluna < 15; coluna++) {
			for (int linha = 0; linha < 15; linha++) {
				if (linha > 0 && linha < 6 && coluna == 7) {
					preencheCasa(40 * linha, 40 * coluna, Color.RED);
				} else if (linha > 8 && linha < 14 && coluna == 7) {
					preencheCasa(40 * linha, 40 * coluna, Color.YELLOW);
				} else if (linha == 7 && coluna > 0 && coluna < 6) {
					preencheCasa(40 * linha, 40 * coluna, Color.GREEN);
				} else if (linha == 7 && coluna > 8 && coluna < 14) {
					preencheCasa(40 * linha, 40 * coluna, Color.BLUE);
				}
			}
		}
	}

	private void preencheTodasCasasSaidas() {
		final int[] xVermelho = { 50, 50, 70 };
		final int[] yVermelho = { 250, 270, 260 };
		final int[] xAzul = { 250, 270, 260 };
		final int[] yAzul = { 550, 550, 530 };
		final int[] xVerde = { 330, 350, 340 };
		final int[] yVerde = { 50, 50, 70 };
		final int[] xAmarelo = { 550, 550, 530 };
		final int[] yAmarelo = { 330, 350, 340 };

		preencheCasa(40, 240, Color.RED);
		preencheTriangulo(xVermelho, yVermelho);
		preencheCasa(240, 520, Color.BLUE);
		preencheTriangulo(xAzul, yAzul);
		preencheCasa(320, 40, Color.GREEN);
		preencheTriangulo(xVerde, yVerde);
		preencheCasa(520, 320, Color.YELLOW);
		preencheTriangulo(xAmarelo, yAmarelo);
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

	private void preencheCasa(int x, int y, Color cor) {
		graphics.setPaint(cor);
		graphics.fill(new Rectangle2D.Double(x, y, 40, 40));
		graphics.setPaint(Color.BLACK);
		graphics.draw(new Rectangle2D.Double(x, y, 40, 40));
	}

	private void preencheTriangulo(int[] x, int[] y) {
		graphics.setPaint(Color.WHITE);
		graphics.fillPolygon(x, y, 3);
	}
}