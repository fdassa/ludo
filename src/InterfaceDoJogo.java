import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Caminho;
import model.Casa;
import model.Dado;
import model.Pino;
import model.Rodada;
import model.Rodada.Vez;

@SuppressWarnings("serial")
public class InterfaceDoJogo extends JFrame implements MouseListener {
	private Tabuleiro tabuleiro;
	private Menu menu;

	public InterfaceDoJogo() {
		this.setSize(768, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabuleiro = new Tabuleiro();
		tabuleiro.addMouseListener(this);
		this.add(tabuleiro, BorderLayout.CENTER);
		this.setVisible(true);
		centralizaNaTela();
		menu = new Menu();
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

	private ArrayList<Casa> obtemCasasIniciaisDaVez(Vez vez) {
		switch (vez) {
		case VERMELHO:
			return tabuleiro.getCasasIniciaisVermelhas();
		case VERDE:
			return tabuleiro.getCasasIniciaisVerdes();
		case AMARELO:
			return tabuleiro.getCasasIniciaisAmarelas();
		default:
			return tabuleiro.getCasasIniciaisAzuis();
		}
	}

	private Caminho obtemCaminhoDaVez(Vez vez) {
		switch (vez) {
		case VERMELHO:
			return tabuleiro.getCaminhoVermelho();
		case VERDE:
			return tabuleiro.getCaminhoVerde();
		case AMARELO:
			return tabuleiro.getCaminhoAmarelo();
		default:
			return tabuleiro.getCaminhoAzul();
		}
	}

	private Color obtemCorDaVez(Vez vez) {
		switch (vez) {
		case VERMELHO:
			return Color.RED;
		case VERDE:
			return Color.GREEN;
		case AMARELO:
			return Color.YELLOW;
		default:
			return Color.BLUE;
		}
	}

	private Casa obtemCasaClicada(int clickedX, int clickedY, Color corDaVez, List<Casa> listaDeCasas) {
		Casa casaClicada = null;
		for (int i = 0; i < listaDeCasas.size(); i++) {
			final Casa casa = listaDeCasas.get(i);
			final int casaX = casa.getColuna() * 40;
			final int casaY = casa.getLinha() * 40;
			if (clickedX > casaX && clickedX < casaX + 40 && clickedY > casaY && clickedY < casaY + 40) {
				casaClicada = casa;
			}
		}
		return casaClicada;
	}

	private Pino removePino(Color cor, Casa casa) {
		Pino pino = null;
		final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
		for (int j = 0; j < listaDePinos.size(); j++) {
			if (listaDePinos.get(j).getCor().equals(cor)) {
				pino = listaDePinos.get(j);
				listaDePinos.remove(j);
				break;
			}
		}
		return pino;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		final int numeroDoDado = Dado.getInstace().getNumeroDoDado();
		final Rodada rodada = Rodada.getInstance();
		final Vez vez = rodada.getVez();
		final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
		final Caminho caminho = obtemCaminhoDaVez(vez);
		final Color cor = obtemCorDaVez(vez);
		final int clickedX = event.getX();
		final int clickedY = event.getY();
		Casa casaClicada = obtemCasaClicada(clickedX, clickedY, cor, casasIniciais);
		if (casaClicada != null) {
			final Pino pino = removePino(cor, casaClicada);
			if (pino != null) {
				caminho.getListaDeCasas().get(numeroDoDado - 1).getListaDePinos().add(pino);
				rodada.passaParaProximaRodada();
				menu.habilitaBotaoLancarDado();
				tabuleiro.repaint();
				return;
			}
		}
		casaClicada = obtemCasaClicada(clickedX, clickedY, cor, caminho.getListaDeCasas());
		if (casaClicada != null) {
			final Pino pino = removePino(cor, casaClicada);
			if (pino != null) {
				final int position = caminho.getListaDeCasas().indexOf(casaClicada);
				caminho.getListaDeCasas().get(position + numeroDoDado).getListaDePinos().add(pino);
				rodada.passaParaProximaRodada();
				menu.habilitaBotaoLancarDado();
				tabuleiro.repaint();
				return;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}