package model;

public class Rodada {
	private static Rodada instance = null;

	public enum Vez {
		VERMELHO, VERDE, AMARELO, AZUL
	}

	private Vez vez = Vez.VERMELHO;
	private boolean rodadaExtra = false;

	private Rodada() {
	}

	public static Rodada getInstance() {
		if (instance == null) {
			instance = new Rodada();
		}
		return instance;
	}

	public void passaParaProximaRodada() {
		switch (vez) {
		case VERMELHO:
			vez = Vez.VERDE;
			break;
		case VERDE:
			vez = Vez.AMARELO;
			break;
		case AMARELO:
			vez = Vez.AZUL;
			break;
		default:
			vez = Vez.VERMELHO;
			break;
		}
	}

	public Vez getVez() {
		return vez;
	}

	public boolean isRodadaExtra() {
		return rodadaExtra;
	}

	public void setRodadaExtra(boolean rodadaExtra) {
		this.rodadaExtra = rodadaExtra;
	}
}
