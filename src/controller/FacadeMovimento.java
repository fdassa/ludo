package controller;

import java.util.ArrayList;

import model.Caminho;
import model.Casa;
import model.Dado;
import model.Pino;
import model.Rodada;
import model.Rodada.Vez;
import view.Tabuleiro;

public class FacadeMovimento {
	private static FacadeMovimento instance = null;
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();
	private Rodada rodada = Rodada.getInstance();
	private Dado dado = Dado.getInstace();

	private FacadeMovimento() {
	}

	public static FacadeMovimento getInstace() {
		if (instance == null) {
			instance = new FacadeMovimento();
		}
		return instance;
	}
	
	public boolean existeJogadasPossiveis() {
		final int numeroDoDado = dado.getNumeroDoDado();
		if (numeroDoDado != 5) {
			final Vez vez = rodada.getVez();
			final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
			for (Casa casa : casasIniciais) {
				final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
				if (listaDePinos.size() == 0) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public boolean realizaJogadasAutomaticas() {
		final Vez vez = rodada.getVez();
		final int numeroDoDado = dado.getNumeroDoDado();
		final Caminho caminho = obtemCaminhoDaVez(vez);
		if (numeroDoDado == 5 && caminho.getListaDeCasas().get(0).getListaDePinos().size() == 0) {
			final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
			for (Casa casa : casasIniciais) {
				final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
				if (listaDePinos.size() > 0) {
					final Pino pino = listaDePinos.get(0);
					listaDePinos.remove(0);
					caminho.getListaDeCasas().get(0).getListaDePinos().add(pino);
					return true;
				}
			}
		}
		return false;
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
}
