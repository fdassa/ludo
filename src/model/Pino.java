package model;

import java.awt.Color;

public class Pino {
	private int id = -1;
	private Color cor;
	/**
	 * Posição da Casa em que o pino se encontra no respectivo Caminho. Este
	 * valor deve ser -1 caso o pino esteja numa casa inicial.
	 */
	private int posicaoNoCaminho = -1;

	public Pino(int id, Color cor) {
		this.id = id;
		this.cor = cor;
	}

	public int getId() {
		return id;
	}

	public Color getCor() {
		return cor;
	}

	public int getPosicaoNoCaminho() {
		return posicaoNoCaminho;
	}

	public void setPosicaoNoCaminho(int posicaoNoCaminho) {
		this.posicaoNoCaminho = posicaoNoCaminho;
	}
}
