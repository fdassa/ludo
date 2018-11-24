package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Casa.Tipo;

public class Caminho {
	private Color cor;
	private List<Casa> listaDeCasas = criaListaDeCasasComuns();

	public Caminho(Color cor) {
		this.cor = cor;
		populaListaDeCasas(cor);
	}

	public List<Casa> getListaDeCasas() {
		return listaDeCasas;
	}
	
	public Color getCor() {
		return cor;
	}
	
	private void populaListaDeCasas(Color cor) {
		if (cor == Color.RED) {
			populaListaDeCasasVermelhas();
		} else if (cor == Color.GREEN) {
			populaListaDeCasasVerdes();
		} else if (cor == Color.YELLOW) {
			populaListaDeCasasAmarelas();
		} else {
			populaListaDeCasasAzuis();
		}
	}

	private ArrayList<Casa> criaListaDeCasasComuns() {
		final ArrayList<Casa> listaDeCasasComuns = new ArrayList<Casa>();
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 6, 1));
		for (int coluna = 2; coluna < 6; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 6, coluna));
		}
		for (int linha = 5; linha > 1; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 6));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 1, 6));
		for (int coluna = 6; coluna < 9; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 0, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 1, 8));
		for (int linha = 2; linha < 6; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 8));
		}
		for (int coluna = 9; coluna < 13; coluna++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 6, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 6, 13));
		for (int linha = 6; linha < 9; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 14));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 8, 13));
		for (int coluna = 12; coluna > 8; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 8, coluna));
		}
		for (int linha = 9; linha < 13; linha++) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 8));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 13, 8));
		for (int coluna = 8; coluna > 5; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 14, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.SAIDA, 13, 6));
		for (int linha = 12; linha > 8; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 6));
		}
		for (int coluna = 5; coluna > 1; coluna--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, 8, coluna));
		}
		listaDeCasasComuns.add(new Casa(Tipo.ABRIGO, 8, 1));
		for (int linha = 8; linha > 5; linha--) {
			listaDeCasasComuns.add(new Casa(Tipo.NORMAL, linha, 0));
		}
		return listaDeCasasComuns;
	}

	private void populaListaDeCasasVermelhas() {
		listaDeCasas.add(new Casa(Tipo.SAIDA, 6, 1));
		for (int coluna = 1; coluna < 6; coluna++) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, 7, coluna));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 7, 6));
	}

	private void populaListaDeCasasVerdes() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(13, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 13));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(new Casa(Tipo.SAIDA, 1, 8));
		for (int linha = 1; linha < 6; linha++) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, linha, 7));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 6, 7));
	}

	private void populaListaDeCasasAmarelas() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(26, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 26));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(new Casa(Tipo.SAIDA, 8, 13));
		for (int coluna = 13; coluna > 8; coluna--) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, 7, coluna));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 7, 8));
	}

	private void populaListaDeCasasAzuis() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(39, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 39));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(new Casa(Tipo.SAIDA, 13, 6));
		for (int linha = 13; linha > 8; linha--) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, linha, 7));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 8, 7));
	}
}
