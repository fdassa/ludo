package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Caminho;
import model.Casa;
import model.Pino;
import model.Casa.Tipo;

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel {
	private static Tabuleiro instance = null;

	private ArrayList<Pino> pinosVermelhos = criaListaDePinos(Color.RED);
	private ArrayList<Pino> pinosVerdes = criaListaDePinos(Color.GREEN);
	private ArrayList<Pino> pinosAmarelos = criaListaDePinos(Color.YELLOW);
	private ArrayList<Pino> pinosAzuis = criaListaDePinos(Color.BLUE);

	private ArrayList<Casa> casasIniciaisVermelhas = criaCasasIniciaisVermelhas();
	private ArrayList<Casa> casasIniciaisVerdes = criaCasasIniciaisVerdes();
	private ArrayList<Casa> casasIniciaisAmarelas = criaCasasIniciaisAmarelas();
	private ArrayList<Casa> casasIniciaisAzuis = criaCasasIniciaisAzuis();

	private Caminho caminhoVermelho;
	private Caminho caminhoVerde;
	private Caminho caminhoAmarelo;
	private Caminho caminhoAzul;

	private int ultimoPinoMovimentadoVermelho = -1;
	private int ultimoPinoMovimentadoVerde = -1;
	private int ultimoPinoMovimentadoAmarelo = -1;
	private int ultimoPinoMovimentadoAzul = -1;

	private Graphics2D graphics;

	private Tabuleiro() {
		this.setSize(640, 640);
		inicializaCaminhos();
		colocaPinosNasCasasIniciais();
	}

	public static Tabuleiro getInstance() {
		if (instance == null) {
			instance = new Tabuleiro();
		}
		return instance;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics = (Graphics2D) g;
		exibeTabuleiro();
		desenhaTodosPinos();
		revalidate();
	}

	public void atualizaUltimoPinoMovimentado(Pino ultimoPinoMovimentado) {
		Color cor = ultimoPinoMovimentado.getCor();
		if (cor.equals(Color.RED)) {
			ultimoPinoMovimentadoVermelho = ultimoPinoMovimentado.getId();
		} else if (cor.equals(Color.GREEN)) {
			ultimoPinoMovimentadoVerde = ultimoPinoMovimentado.getId();
		} else if (cor.equals(Color.YELLOW)) {
			ultimoPinoMovimentadoAmarelo = ultimoPinoMovimentado.getId();
		} else {
			ultimoPinoMovimentadoAzul = ultimoPinoMovimentado.getId();
		}
	}

	public int obtemUltimoPinoMovimentadoPelaCor(Color cor) {
		if (cor.equals(Color.RED)) {
			return ultimoPinoMovimentadoVermelho;
		} else if (cor.equals(Color.GREEN)) {
			return ultimoPinoMovimentadoVerde;
		} else if (cor.equals(Color.YELLOW)) {
			return ultimoPinoMovimentadoAmarelo;
		} else {
			return ultimoPinoMovimentadoAzul;
		}
	}

	public ArrayList<Casa> getCasasIniciaisVermelhas() {
		return casasIniciaisVermelhas;
	}

	public ArrayList<Casa> getCasasIniciaisVerdes() {
		return casasIniciaisVerdes;
	}

	public ArrayList<Casa> getCasasIniciaisAmarelas() {
		return casasIniciaisAmarelas;
	}

	public ArrayList<Casa> getCasasIniciaisAzuis() {
		return casasIniciaisAzuis;
	}

	public Caminho getCaminhoVermelho() {
		return caminhoVermelho;
	}

	public Caminho getCaminhoVerde() {
		return caminhoVerde;
	}

	public Caminho getCaminhoAmarelo() {
		return caminhoAmarelo;
	}

	public Caminho getCaminhoAzul() {
		return caminhoAzul;
	}

	public ArrayList<Pino> getPinosVermelhos() {
		return pinosVermelhos;
	}

	public ArrayList<Pino> getPinosVerdes() {
		return pinosVerdes;
	}

	public ArrayList<Pino> getPinosAmarelos() {
		return pinosAmarelos;
	}

	public ArrayList<Pino> getPinosAzuis() {
		return pinosAzuis;
	}

	private void inicializaCaminhos() {
		final ArrayList<Casa> listaDeCasasComuns = criaListaDeCasasComuns();
		caminhoVermelho = new Caminho(Color.RED, listaDeCasasComuns);
		caminhoVerde = new Caminho(Color.GREEN, listaDeCasasComuns);
		caminhoAmarelo = new Caminho(Color.YELLOW, listaDeCasasComuns);
		caminhoAzul = new Caminho(Color.BLUE, listaDeCasasComuns);
	}
	
	private ArrayList<Casa> criaListaDeCasasComuns() {
		final ArrayList<Casa> listaDeCasasComuns = new ArrayList<Casa>();
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 6, 1));
		for (int coluna = 2; coluna < 6; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 6, coluna));
		}
		for (int linha = 5; linha > 1; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 6));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 1, 6));
		for (int coluna = 6; coluna < 9; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 0, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 1, 8));
		for (int linha = 2; linha < 6; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 8));
		}
		for (int coluna = 9; coluna < 13; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 6, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 6, 13));
		for (int linha = 6; linha < 9; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 14));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 8, 13));
		for (int coluna = 12; coluna > 8; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 8, coluna));
		}
		for (int linha = 9; linha < 13; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 8));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 13, 8));
		for (int coluna = 8; coluna > 5; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 14, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 13, 6));
		for (int linha = 12; linha > 8; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 6));
		}
		for (int coluna = 5; coluna > 1; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 8, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 8, 1));
		for (int linha = 8; linha > 5; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 0));
		}
		return listaDeCasasComuns;
	}

	private void desenhaTodosPinos() {
		for (int i = 0; i < 4; i++) {
			desenhaPinosDeUmaCasa(casasIniciaisVermelhas.get(i));
			desenhaPinosDeUmaCasa(casasIniciaisVerdes.get(i));
			desenhaPinosDeUmaCasa(casasIniciaisAmarelas.get(i));
			desenhaPinosDeUmaCasa(casasIniciaisAzuis.get(i));
		}
		for (int i = 0; i < caminhoVermelho.getListaDeCasas().size(); i++) {
			desenhaPinosDeUmaCasa(caminhoVermelho.getListaDeCasas().get(i));
			desenhaPinosDeUmaCasa(caminhoVerde.getListaDeCasas().get(i));
			desenhaPinosDeUmaCasa(caminhoAmarelo.getListaDeCasas().get(i));
			desenhaPinosDeUmaCasa(caminhoAzul.getListaDeCasas().get(i));
		}
	}	
	private void desenhaPinosDeUmaCasa(Casa casa) {
		final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
		final int x = casa.getColuna() * 40;
		final int y = casa.getLinha() * 40;
		for (Pino pino : listaDePinos) {
			graphics.setPaint(pino.getCor());
			graphics.fill(new Ellipse2D.Double(x + 5, y + 5, 30, 30));
		}
	}

	private void colocaPinosNasCasasIniciais() {
		for (int i = 0; i < 4; i++) {
			casasIniciaisVermelhas.get(i).getListaDePinos().add(pinosVermelhos.get(i));
			casasIniciaisVerdes.get(i).getListaDePinos().add(pinosVerdes.get(i));
			casasIniciaisAmarelas.get(i).getListaDePinos().add(pinosAmarelos.get(i));
			casasIniciaisAzuis.get(i).getListaDePinos().add(pinosAzuis.get(i));
		}
	}

	private ArrayList<Pino> criaListaDePinos(Color cor) {
		final ArrayList<Pino> listaDePinos = new ArrayList<Pino>();
		for (int i = 0; i < 4; i++) {
			listaDePinos.add(new Pino(i, cor));
		}
		return listaDePinos;
	}

	private ArrayList<Casa> criaCasasIniciaisVermelhas() {
		final ArrayList<Casa> casasIniciaisVermelhas = new ArrayList<Casa>();
		casasIniciaisVermelhas.add(new Casa(Tipo.INICIAL, 1, 1));
		casasIniciaisVermelhas.add(new Casa(Tipo.INICIAL, 4, 1));
		casasIniciaisVermelhas.add(new Casa(Tipo.INICIAL, 1, 4));
		casasIniciaisVermelhas.add(new Casa(Tipo.INICIAL, 4, 4));
		return casasIniciaisVermelhas;
	}

	private ArrayList<Casa> criaCasasIniciaisVerdes() {
		final ArrayList<Casa> casasIniciaisVerdes = new ArrayList<Casa>();
		casasIniciaisVerdes.add(new Casa(Tipo.INICIAL, 1, 10));
		casasIniciaisVerdes.add(new Casa(Tipo.INICIAL, 4, 10));
		casasIniciaisVerdes.add(new Casa(Tipo.INICIAL, 1, 13));
		casasIniciaisVerdes.add(new Casa(Tipo.INICIAL, 4, 13));
		return casasIniciaisVerdes;
	}

	private ArrayList<Casa> criaCasasIniciaisAmarelas() {
		final ArrayList<Casa> casasIniciaisAmarelas = new ArrayList<Casa>();
		casasIniciaisAmarelas.add(new Casa(Tipo.INICIAL, 10, 10));
		casasIniciaisAmarelas.add(new Casa(Tipo.INICIAL, 13, 10));
		casasIniciaisAmarelas.add(new Casa(Tipo.INICIAL, 10, 13));
		casasIniciaisAmarelas.add(new Casa(Tipo.INICIAL, 13, 13));
		return casasIniciaisAmarelas;
	}

	private ArrayList<Casa> criaCasasIniciaisAzuis() {
		final ArrayList<Casa> casasIniciaisAzuis = new ArrayList<Casa>();
		casasIniciaisAzuis.add(new Casa(Tipo.INICIAL, 10, 1));
		casasIniciaisAzuis.add(new Casa(Tipo.INICIAL, 13, 1));
		casasIniciaisAzuis.add(new Casa(Tipo.INICIAL, 10, 4));
		casasIniciaisAzuis.add(new Casa(Tipo.INICIAL, 13, 4));
		return casasIniciaisAzuis;
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
		preencheTodasCasasIniciais(0, 0, Color.RED);
		preencheTodasCasasIniciais(0, 360, Color.BLUE);
		preencheTodasCasasIniciais(360, 0, Color.GREEN);
		preencheTodasCasasIniciais(360, 360, Color.YELLOW);
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

	private void preencheTodasCasasIniciais(int x, int y, Color cor) {
		graphics.setPaint(cor);
		graphics.fill(new Rectangle2D.Double(x, y, 240, 240));
		graphics.setPaint(Color.BLACK);
		graphics.draw(new Rectangle2D.Double(x, y, 240, 240));
		preencheCasaInicial(x + 40, y + 40);
		preencheCasaInicial(x + 160, y + 40);
		preencheCasaInicial(x + 40, y + 160);
		preencheCasaInicial(x + 160, y + 160);
	}

	private void preencheCasaInicial(int x, int y) {
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