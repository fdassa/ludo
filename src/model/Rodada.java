package model;

import java.awt.Color;

public class Rodada {
	private static Rodada instance = null;
	public enum Vez {
		VERMELHO, VERDE, AMARELO, AZUL
	}
	private Vez vez = Vez.VERMELHO;
	
	private Rodada(){
	}
	
	public static Rodada getInstance() {
		if (instance == null) {
			instance = new Rodada();
		}
		return instance;
	}
	
	public void passaParaProximaRodada() {
		switch(vez) {
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
}
