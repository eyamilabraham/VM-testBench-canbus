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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalityType;

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
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 678, 302);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setBounds(584, 227, 64, 35);
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
		dtrpnSeleccionarEn.setBackground(Color.LIGHT_GRAY);
		dtrpnSeleccionarEn.setEditable(false);
		dtrpnSeleccionarEn.setText
				(
				  "1- Seleccionar en el menu la casilla New Log\n"+
				  "2- Ingresar el peso del vehiculo en la casilla de texto que se encuentra en la parte inferior derecha     de la pantalla principal\n"
				 +"3-Seleccionar el tipo de motor y tipo de bateria a testear\n"
				 + "4- Establecer conexion con la placa de adquision de datos\n  "
				 + " * seleccionar el puerto correspondiente\n   "
				 + "* presionar el boton conectar y eperar que el texto conexion cambie a color verde.\n"
				 + "5- En el menu datalog presionar la opcion Record Data"
				);
		
		dtrpnSeleccionarEn.setBounds(12, 12, 636, 157);
		getContentPane().add(dtrpnSeleccionarEn);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/volt/Escritorio/java serial/ConexionSerial/images/image72.png"));
		lblNewLabel.setBounds(12, 167, 138, 95);
		getContentPane().add(lblNewLabel);
	}
}
