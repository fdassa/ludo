import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Dado;
import model.Rodada;
import model.Rodada.Vez;

public class Menu implements ActionListener {
	private Box boxMenu;
	private JLabel imagemDado;
	private JButton botaoLancarDado;
	private Dado dado;

	public Menu() {
		dado = Dado.getInstace();
		boxMenu = Box.createVerticalBox();

		botaoLancarDado = new JButton("Lan�ar dado");
		botaoLancarDado.addActionListener(this);
		boxMenu.add(botaoLancarDado);

		imagemDado = new JLabel();
		boxMenu.add(imagemDado);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == botaoLancarDado) {
			final Rodada rodada = Rodada.getInstance();
			dado.lancaDado();
			imagemDado.setIcon(dado.getImagemIcon());
			imagemDado.setBorder(BorderFactory.createEtchedBorder());
			imagemDado.setBackground(obtemCorDaVez(rodada.getVez()));
			botaoLancarDado.setEnabled(false);
		}
	}

	public Box getBoxMenu() {
		return boxMenu;
	}
	
	public void habilitaBotaoLancarDado() {
		botaoLancarDado.setEnabled(true);
	}
	
	private Color obtemCorDaVez(Vez vez) {
		Color cor;
		switch(vez) {
		case VERMELHO:
			cor = Color.RED;
			break;
		case VERDE:
			cor = Color.GREEN;
			break;
		case AMARELO:
			cor = Color.YELLOW;
			break;
		default:
			cor = Color.BLUE;
			break;
		}
		return cor;
	}
}
