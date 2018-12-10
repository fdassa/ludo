package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Casa.Tipo;

public class Caminho {
	private Color cor;
	private List<Casa> listaDeCasas = new ArrayList<Casa>();

	public Caminho(Color cor, List<Casa> listaDeCasasComuns) {
		this.cor = cor;
		this.listaDeCasas.addAll(listaDeCasasComuns);
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
	
	private void populaListaDeCasasVermelhas() {
		listaDeCasas.add(listaDeCasas.get(0));
		for (int coluna = 1; coluna < 6; coluna++) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, 7, coluna));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 7, 6));
	}

	private void populaListaDeCasasVerdes() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(13, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 13));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(listaDeCasas.get(0));
		for (int linha = 1; linha < 6; linha++) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, linha, 7));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 6, 7));
	}

	private void populaListaDeCasasAmarelas() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(26, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 26));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(listaDeCasas.get(0));
		for (int coluna = 13; coluna > 8; coluna--) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, 7, coluna));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 7, 8));
	}

	private void populaListaDeCasasAzuis() {
		final List<Casa> listaAuxiliar = listaDeCasas.subList(39, listaDeCasas.size());
		listaAuxiliar.addAll(listaDeCasas.subList(0, 39));
		listaDeCasas = listaAuxiliar;
		listaDeCasas.add(listaDeCasas.get(0));
		for (int linha = 13; linha > 8; linha--) {
			listaDeCasas.add(new Casa(Tipo.RETA_FINAL, linha, 7));
		}
		listaDeCasas.add(new Casa(Tipo.FINAL, 8, 7));
	}
}
