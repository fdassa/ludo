package controller;

import view.Tabuleiro;

public class FacadeMovimento {
	private static FacadeMovimento instance = null;
	private Tabuleiro tabuleiro = Tabuleiro.getInstance();

	private FacadeMovimento() {
	}

	public static FacadeMovimento getInstace() {
		if (instance == null) {
			instance = new FacadeMovimento();
		}
		return instance;
	}

}
