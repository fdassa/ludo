package model;

import java.util.ArrayList;

public class Casa {
	public enum Tipo {
		INICIAL, SAIDA, NORMAL, ABRIGO, RETA_FINAL, FINAL
	}

	private Tipo tipo = Tipo.NORMAL;
	private int linha = -1;
	private int coluna = -1;
	private ArrayList<Pino> listaDePinos = new ArrayList<Pino>();

	public Casa(Tipo tipo, int linha, int coluna) {
		this.tipo = tipo;
		this.linha = linha;
		this.coluna = coluna;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public ArrayList<Pino> getListaDePinos() {
		return listaDePinos;
	}
}
