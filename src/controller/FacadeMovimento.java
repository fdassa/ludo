package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Caminho;
import model.Casa;
import model.Casa.Tipo;
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
		final Vez vez = rodada.getVez();
		if (numeroDoDado != 5 && todosOsPinosEstaoNasCasasIniciais(vez)) {
			rodada.passaParaProximaRodada();
			return false;
		}
		final ArrayList<Pino> pinosDaVez = obtemPinosDaVez(vez);
		for (Pino pino : pinosDaVez) {
			int posicaoDoPino = pino.getPosicaoNoCaminho();
			if (posicaoDoPino != -1 && movimentoValido(pino)) {
				return true;
			}
		}

		if (numeroDoDado != 6) {
			rodada.passaParaProximaRodada();
		}
		return false;
	}

	public boolean realizaJogadasAutomaticas() {
		final Vez vez = rodada.getVez();
		final int numeroDoDado = dado.getNumeroDoDado();
		final Caminho caminho = obtemCaminhoDaVez(vez);
		final ArrayList<Pino> pinosDaCasaSaida = caminho.getListaDeCasas().get(0).getListaDePinos();
		final boolean podeSairComPino = pinosDaCasaSaida.isEmpty()
				|| !pinosDaCasaSaida.get(0).getCor().equals(caminho.getCor());
		if (numeroDoDado == 5 && podeSairComPino) {
			final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
			for (Casa casa : casasIniciais) {
				final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
				if (!listaDePinos.isEmpty()) {
					final Pino pino = listaDePinos.get(0);
					listaDePinos.remove(0);
					final boolean rodadaExtra = inserePinoNaCasa(0, pino);
					rodada.setRodadaExtra(rodadaExtra);
					if (!rodada.isRodadaExtra()) {
						rodada.passaParaProximaRodada();
					}
					return true;
				}
			}
		} else if (dado.limiteDe6Seguidos()) {
			final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
			final ArrayList<Pino> listaDePinos = obtemPinosDaVez(vez);
			final Color corDaVez = listaDePinos.get(0).getCor();
			final int pinoId = tabuleiro.obtemUltimoPinoMovimentadoPelaCor(corDaVez);
			if (pinoId != -1) {
				final Pino ultimoPinoMovimentado = listaDePinos.get(pinoId);
				final Casa casaDoPino = caminho.getListaDeCasas().get(ultimoPinoMovimentado.getPosicaoNoCaminho());
				if (casaDoPino.getTipo() != Tipo.RETA_FINAL && casaDoPino.getTipo() != Tipo.FINAL) {
					casaDoPino.getListaDePinos().remove(ultimoPinoMovimentado);
					for (Casa casa : casasIniciais) {
						if (casa.getListaDePinos().isEmpty()) {
							casa.getListaDePinos().add(ultimoPinoMovimentado);
							ultimoPinoMovimentado.setPosicaoNoCaminho(-1);
							break;
						}
					}
				}
			}
			dado.resetaContagemDe6();
			rodada.passaParaProximaRodada();
			rodada.setRodadaExtra(false);
			return true;
		} else if (numeroDoDado == 6) {
			final ArrayList<Casa> casasComBarreira = obtemCasaComBarreiraPelaCor(caminho.getListaDeCasas(),
					caminho.getCor());
			for (Casa casa : casasComBarreira) {
				final Pino pino = casa.getListaDePinos().get(0);
				if (movimentoValido(pino)) {
					casa.getListaDePinos().remove(pino);
					final int posicao = caminho.getListaDeCasas().indexOf(casa);
					final boolean rodadaExtra = inserePinoNaCasa(posicao + numeroDoDado, pino);
					tabuleiro.atualizaUltimoPinoMovimentado(pino);
					rodada.setRodadaExtra(rodadaExtra);
					return true;
				}
			}
		}
		return false;
	}

	public boolean movimentoValido(Pino pino) {
		final Vez vez = rodada.getVez();
		final int numeroDePassos = rodada.isRodadaExtra() ? 6 : dado.getNumeroDoDado();
		final Caminho caminho = obtemCaminhoDaVez(vez);
		final int posicaoDoPino = pino.getPosicaoNoCaminho();
		final Tipo tipoDaCasaDoPino = caminho.getListaDeCasas().get(posicaoDoPino).getTipo();
		if (tipoDaCasaDoPino == Tipo.RETA_FINAL ) {
			return caminho.getListaDeCasas().size() < posicaoDoPino + numeroDePassos - 1;
		}
		if (tipoDaCasaDoPino == Tipo.FINAL) {
			return false;
		}
		final List<Casa> caminhoPercorrido = caminho.getListaDeCasas().subList(posicaoDoPino + 1,
				posicaoDoPino + numeroDePassos + 1);
		final Casa casaDestino = caminhoPercorrido.get(caminhoPercorrido.size() - 1);
		return !temBarreiraNoCaminho(caminhoPercorrido) && !casaCheiaProPino(casaDestino, pino, caminho.getCor());
	}

	private boolean todosOsPinosEstaoNasCasasIniciais(Vez vez) {
		final ArrayList<Casa> casasIniciais = obtemCasasIniciaisDaVez(vez);
		for (Casa casa : casasIniciais) {
			final ArrayList<Pino> listaDePinos = casa.getListaDePinos();
			if (listaDePinos.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean inserePinoNaCasa(int posicaoDaCasaDestino, Pino pino) {
		final Vez vez = rodada.getVez();
		final Caminho caminho = obtemCaminhoDaVez(vez);
		final Casa casaDestino = caminho.getListaDeCasas().get(posicaoDaCasaDestino);
		final Color corDaCasa = caminho.getCor();
		final ArrayList<Pino> listaDePinos = casaDestino.getListaDePinos();
		
		if (casaDestino.getTipo() == Tipo.FINAL) {
			listaDePinos.add(pino);
			pino.setPosicaoNoCaminho(posicaoDaCasaDestino);
			return true;
		}

		if (listaDePinos.isEmpty()) {
			listaDePinos.add(pino);
			pino.setPosicaoNoCaminho(posicaoDaCasaDestino);
			return false;
		}

		final Pino pinoDaCasa = listaDePinos.get(0);
		final boolean pinosDaMesmaCor = pinoDaCasa.getCor().equals(pino.getCor());
		final boolean casaPossuiPinoDaSuaCor = pinoDaCasa.getCor().equals(corDaCasa);

		if (pinosDaMesmaCor || casaDestino.getTipo() == Tipo.ABRIGO
				|| casaDestino.getTipo() == Tipo.SAIDA && casaPossuiPinoDaSuaCor) {
			listaDePinos.add(pino);
			pino.setPosicaoNoCaminho(posicaoDaCasaDestino);
			return false;
		}
		
		final ArrayList<Casa> casasIniciais = obtemCasasIniciaisPelaCor(pinoDaCasa.getCor());
		for (Casa casaInicial : casasIniciais) {
			if (casaInicial.getListaDePinos().isEmpty()) {
				listaDePinos.remove(pinoDaCasa);
				casaInicial.getListaDePinos().add(pinoDaCasa);
				pino.setPosicaoNoCaminho(-1);

				listaDePinos.add(pino);
				pino.setPosicaoNoCaminho(posicaoDaCasaDestino);
				return true;
			}
		}
		return false;
	}

	private ArrayList<Casa> obtemCasaComBarreiraPelaCor(List<Casa> listaDeCasas, Color cor) {
		final ArrayList<Casa> casasComBarreira = new ArrayList<Casa>();
		for (Casa casa : listaDeCasas) {
			final List<Pino> listaDePinos = casa.getListaDePinos();
			if (listaDePinos.size() > 1 && listaDePinos.get(0).getCor().equals(cor)
					&& listaDePinos.get(0).getCor().equals(listaDePinos.get(1).getCor())) {
				casasComBarreira.add(casa);
			}
		}
		return casasComBarreira;
	}

	private boolean temBarreiraNoCaminho(List<Casa> listaDeCasas) {
		for (Casa casa : listaDeCasas) {
			final List<Pino> listaDePinos = casa.getListaDePinos();
			if (listaDePinos.size() > 1 && listaDePinos.get(0).getCor().equals(listaDePinos.get(1).getCor())) {
				return true;
			}
		}
		return false;
	}

	private boolean casaCheiaProPino(Casa casa, Pino pino, Color corDaCasa) {
		final List<Pino> listaDePinosDaCasa = casa.getListaDePinos();

		switch (listaDePinosDaCasa.size()) {
		case 0:
			return false;

		case 1:
			if (casa.getTipo() == Tipo.SAIDA) {
				final Pino pinoDaCasa = listaDePinosDaCasa.get(0);
				final boolean pinosDaMesmaCor = pinoDaCasa.getCor().equals(pino.getCor());
				return pinosDaMesmaCor;
			}
			return false;

		default:
			return true;
		}
	}

	private ArrayList<Pino> obtemPinosDaVez(Vez vez) {
		switch (vez) {
		case VERMELHO:
			return tabuleiro.getPinosVermelhos();
		case VERDE:
			return tabuleiro.getPinosVerdes();
		case AMARELO:
			return tabuleiro.getPinosAmarelos();
		default:
			return tabuleiro.getPinosAzuis();
		}
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

	private ArrayList<Casa> obtemCasasIniciaisPelaCor(Color cor) {
		if (cor.equals(Color.RED)) {
			return tabuleiro.getCasasIniciaisVermelhas();
		} else if (cor.equals(Color.GREEN)) {
			return tabuleiro.getCasasIniciaisVerdes();
		} else if (cor.equals(Color.YELLOW)) {
			return tabuleiro.getCasasIniciaisAmarelas();
		} else {
			return tabuleiro.getCasasIniciaisAzuis();
		}
	}
}
