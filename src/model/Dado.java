package model;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Dado {
	private static Dado instance = null;
	private int numeroDoDado = 0;

	private Dado() {
	}

	public static Dado getInstace() {
		if (instance == null) {
			instance = new Dado();
		}
		return instance;
	}

	public int lancaDado() {
		final Random rand = new Random();
		numeroDoDado = rand.nextInt(6) + 1;
		return numeroDoDado;
	}

	public ImageIcon getImagemIcon() {
		BufferedImage img = null;
		try {
			switch (numeroDoDado) {
			case 1:
				img = ImageIO.read(new File("src/images/Dado1.png"));
				break;

			case 2:
				img = ImageIO.read(new File("src/images/Dado2.png"));
				break;

			case 3:
				img = ImageIO.read(new File("src/images/Dado3.png"));
				break;

			case 4:
				img = ImageIO.read(new File("src/images/Dado4.png"));
				break;

			case 5:
				img = ImageIO.read(new File("src/images/Dado5.png"));
				break;

			default:
				img = ImageIO.read(new File("src/images/Dado6.png"));
				break;
			}
		} catch (IOException e) {
			System.out.println("Erro ao obter a imagem do dado!");
			System.exit(0);
		}
		return new ImageIcon(img);
	}
	
	public int getNumeroDoDado() {
		return numeroDoDado;
	}
}
