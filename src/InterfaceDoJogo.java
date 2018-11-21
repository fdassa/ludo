import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
		for (int i = 0; i < casasIniciais.size(); i++) {
			final Casa casa = casasIniciais.get(i);
			final int casaX = casa.getColuna() * 40;
			final int casaY = casa.getLinha() * 40;
			if (clickedX > casaX && clickedX < casaX + 40 && clickedY > casaY && clickedY < casaY + 40) {
				Pino pino = null;
				for (int j = 0; i < casa.getListaDePinos().size(); i++) {
					if (casa.getListaDePinos().get(j).getCor() == cor) {
						pino = casa.getListaDePinos().get(j);
						break;
					}
				}
				if(pino != null) {
					caminho.getListaDeCasas().get(numeroDoDado - 1).getListaDePinos().add(pino);
					casa.getListaDePinos().remove(pino);
					rodada.passaParaProximaRodada();
					menu.habilitaBotaoLancarDado();
					tabuleiro.repaint();
					return;
				}
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