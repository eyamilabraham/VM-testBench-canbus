import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DaltaLog extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DaltaLog dialog = new DaltaLog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DaltaLog() {
		setBounds(100, 100, 678, 302);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(22, 218, 612, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JEditorPane dtrpnSeleccionarEn = new JEditorPane();
		dtrpnSeleccionarEn.setEditable(false);
		dtrpnSeleccionarEn.setText
				(
				  "1- Seleccionar en el menu la casilla New Log\n "
				+ "2- Ingresar el peso del vehiculo en la casilla de texto que se encuentra en la parte inferior derecha de la pantalla principal\n"
				+ "3-Seleccionar El tipo de motor a testear\n"
				+ "4- Establecer conexion con la placa de adquision de datos\n   "
				+ "* seleccionar el puerto correspondiente\n   "
				+ "* presionar el boton conectar y eperar que el texto conexion cambie al color verde.\n"
				+ "5- Espere 30 segundos hasta que el acelerometro haga un autoajuste de su posicion.\n"
				+ "6- En el menu datalog presionar la opcion Record Data"
				);
		
		dtrpnSeleccionarEn.setBounds(12, 12, 636, 179);
		getContentPane().add(dtrpnSeleccionarEn);
	}
}
