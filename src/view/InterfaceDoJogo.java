package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import controller.FacadeMovimento;
import model.Caminho;
import model.Casa;
import model.Dado;
import model.Pino;
import model.Rodada;
import model.Rodada.Vez;

@SuppressWarnings("serial")
public class InterfaceDoJogo extends JFrame implements MouseListener {
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();
	private Dado dado = Dado.getInstace();
	private Menu menu;

	public InterfaceDoJogo() {
		this.setSize(768, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabuleiro.addMouseListener(this);
		this.add(tabuleiro, BorderLayout.CENTER);
		this.setVisible(true);
		centralizaNaTela();
		menu = Menu.getInstance();
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

	private Pino encontraPinoPelaCor(Color cor, Casa casa) {
		final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
		for (int j = 0; j < listaDePinos.size(); j++) {
			if (listaDePinos.get(j).getCor().equals(cor)) {
				return listaDePinos.get(j);
			}
		}
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		final Rodada rodada = Rodada.getInstance();
		if (!dado.foiLancado() && !rodada.isRodadaExtra()) {
			return;
		}
		final FacadeMovimento facadeMovimento = FacadeMovimento.getInstace();
		final int numeroDoDado = dado.getNumeroDoDado();
		final Vez vez = rodada.getVez();
		final Caminho caminho = obtemCaminhoDaVez(vez);
		final Color cor = obtemCorDaVez(vez);
		final int clickedX = event.getX();
		final int clickedY = event.getY();
		Casa casaClicada = obtemCasaClicada(clickedX, clickedY, cor, caminho.getListaDeCasas());
		if (casaClicada != null) {
			final Pino pino = encontraPinoPelaCor(cor, casaClicada);
			if (pino != null && facadeMovimento.movimentoValido(pino)) {
				casaClicada.getListaDePinos().remove(pino);
				final int posicao = caminho.getListaDeCasas().indexOf(casaClicada);
				final int numeroDePassos = rodada.isRodadaExtra() ? 6 : numeroDoDado;
				final boolean rodadaExtra = facadeMovimento.inserePinoNaCasa(posicao + numeroDePassos, pino);
				rodada.setRodadaExtra(rodadaExtra);
				tabuleiro.atualizaUltimoPinoMovimentado(pino);
				if (!rodada.isRodadaExtra()) {
					if (numeroDoDado != 6) {
						rodada.passaParaProximaRodada();
					}
					menu.habilitaBotaoLancarDado();
				}
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