package model;
import java.awt.Color;

public class Pino {
	private int id = -1;
	private Color cor;
	
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
}
