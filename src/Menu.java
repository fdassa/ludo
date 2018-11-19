import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Menu implements ActionListener {
	private Box boxMenu;
	private JLabel imagemDado;
	private JButton botaoLancarDado;
	private Dado dado;

	public Menu() {
		dado = Dado.getInstace();
		boxMenu = Box.createVerticalBox();

		botaoLancarDado = new JButton("Lançar dado");
		botaoLancarDado.addActionListener(this);
		boxMenu.add(botaoLancarDado);

		imagemDado = new JLabel();
		boxMenu.add(imagemDado);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == botaoLancarDado) {
			dado.lancaDado();
			imagemDado.setIcon(dado.getImagemIcon());
		}
	}

	public Box getBoxMenu() {
		return boxMenu;
	}
}
