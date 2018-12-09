package view;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

import controller.FacadeMovimento;
import model.Dado;
import model.Rodada;
import model.Rodada.Vez;

public class Menu implements ActionListener {
	private Box boxMenu;
	private JLabel imagemDado;
	private JButton botaoLancarDado;
	private JButton botaoLancarDado1;
	private JButton botaoLancarDado2;
	private JButton botaoLancarDado3;
	private JButton botaoLancarDado4;
	private JButton botaoLancarDado5;
	private JButton botaoLancarDado6;
	private Dado dado;
	private FacadeMovimento facadeMovimento;

	public Menu() {
		dado = Dado.getInstace();
		facadeMovimento = FacadeMovimento.getInstace();
		boxMenu = Box.createVerticalBox();

		botaoLancarDado = new JButton("Lançar dado");
		botaoLancarDado.addActionListener(this);
		boxMenu.add(botaoLancarDado);

		botaoLancarDado1 = new JButton("Tira 1 no dado");
		botaoLancarDado1.addActionListener(this);
		boxMenu.add(botaoLancarDado1);

		botaoLancarDado2 = new JButton("Tira 2 no dado");
		botaoLancarDado2.addActionListener(this);
		boxMenu.add(botaoLancarDado2);

		botaoLancarDado3 = new JButton("Tira 3 no dado");
		botaoLancarDado3.addActionListener(this);
		boxMenu.add(botaoLancarDado3);

		botaoLancarDado4 = new JButton("Tira 4 no dado");
		botaoLancarDado4.addActionListener(this);
		boxMenu.add(botaoLancarDado4);

		botaoLancarDado5 = new JButton("Tira 5 no dado");
		botaoLancarDado5.addActionListener(this);
		boxMenu.add(botaoLancarDado5);

		botaoLancarDado6 = new JButton("Tira 6 no dado");
		botaoLancarDado6.addActionListener(this);
		boxMenu.add(botaoLancarDado6);

		imagemDado = new JLabel();
		boxMenu.add(imagemDado);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == botaoLancarDado) {
			dado.lancaDado();
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado1) {
			dado.lancaDado(1);
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado2) {
			dado.lancaDado(2);
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado3) {
			dado.lancaDado(3);
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado4) {
			dado.lancaDado(4);
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado5) {
			dado.lancaDado(5);
			trataLancamentoDeDado();
		} else if (event.getSource() == botaoLancarDado6) {
			dado.lancaDado(6);
			trataLancamentoDeDado();
		}
	}

	public Box getBoxMenu() {
		return boxMenu;
	}

	public void habilitaBotaoLancarDado() {
		dado.setFoiLancado(false);
		botaoLancarDado.setEnabled(true);
	}

	private void trataLancamentoDeDado() {
		final Rodada rodada = Rodada.getInstance();
		imagemDado.setIcon(dado.getImagemIcon());
		imagemDado.setBorder(BorderFactory.createEtchedBorder());
		imagemDado.setBackground(obtemCorDaVez(rodada.getVez()));
		botaoLancarDado.setEnabled(false);

		if (facadeMovimento.realizaJogadasAutomaticas()) {
			final Tabuleiro tabuleiro = Tabuleiro.getInstance();
			rodada.passaParaProximaRodada();
			habilitaBotaoLancarDado();
			tabuleiro.repaint();
		} else if (!facadeMovimento.existeJogadasPossiveis()) {
			final Tabuleiro tabuleiro = Tabuleiro.getInstance();
			if (dado.getNumeroDoDado() != 6) {
				rodada.passaParaProximaRodada();
			}
			habilitaBotaoLancarDado();
			tabuleiro.repaint();
		}
	}

	private Color obtemCorDaVez(Vez vez) {
		Color cor;
		switch (vez) {
		case VERMELHO:
			cor = Color.RED;
			break;
		case VERDE:
			cor = Color.GREEN;
			break;
		case AMARELO:
			cor = Color.YELLOW;
			break;
		default:
			cor = Color.BLUE;
			break;
		}
		return cor;
	}
}
