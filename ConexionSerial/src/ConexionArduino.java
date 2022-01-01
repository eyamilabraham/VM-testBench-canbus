import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
//import sun.rmi.runtime.Log;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D.Float;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

import javax.swing.event.ChangeEvent;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JToggleButton;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Window.Type;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Canvas;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.border.MatteBorder;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Cursor;
//import JFreeChart.*;


public class ConexionArduino extends JFrame implements SerialPortEventListener {



	private OutputStream output = null;
	private static InputStream input = null;

	private static final int TIME_OUT = 2000; //tiempo de espera para establecer la conexion
	private static final int DATA_RATE =4800; //velocidad de transmision de datos en baudios

	static SerialPort serialport;

	String PORT_NAME = null;

	static JPanel window;

	CommPortIdentifier port = null;

	Thread t;
	Thread th;
	static int dato;
	private static JLabel lblBATT_TEMP;
	private static JLabel lblHUM;
	private static JLabel lblTEMP;
	private static JLabel lblSys2;

	ArrayList<String> SerialList = new ArrayList<String>();

	public String fecha;

	public int  vel = 0;
	public static int bateria;
	private static JLabel lbl4;
	private static JLabel lblVOLT;
	boolean con = false;
	boolean measure = false;
	private static JLabel lblCURR;
	private static JLabel lblSOC;
	static long  countPacketReceived = 0;
	static long  countPacketSend = 0;
	private static JLabel lbl10;
	private static JLabel lbl11;
	String namePort;
	private JLabel labelCOM;
	private static JLabel lblATM_PRESS;
	private static JLabel lblSys1;
	private final JSeparator separator = new JSeparator();
	private JSeparator separator_1;
	public JLabel labelDate;
	public  static JLabel labelClock;
	private String hour;
	private String minute;
	private String seconds;
	private JComboBox comboSerial;
	private static JLabel rpmLeft;
	private static JLabel currentLeft;
	private static JLabel voltageLeft;
	private static JLabel torqueLeft;
	private static JLabel speedLeft;
	private static JLabel motorTempLeft;
	private static JLabel contTempLeft;
	private static JLabel status1Left;
	private static JLabel status2Left;
	private static JLabel rpmRight;
	private static JLabel currentRight;
	private static JLabel voltageRight;
	private static JLabel torqueRight;
	private static JLabel speedRight;
	private static JLabel tpsRight;
	private static JLabel motorTempRight;
	private static JLabel contTempRight;
	private static JLabel status1Right;
	private static JLabel status2Right;
	private static JLabel lblLongitude;
	private static JLabel lblpitch;
	private static JLabel pitch;
	private static JLabel roll;
	private static JLabel kmh;
	private static JLabel distance;
	private static JLabel tpsLeft;
	public static int soc;
	public static int current;
	public static int bTemp;
	public static int Pat;
	public static int humedad;
	public static int temperatura;
	public static String msg2;
	public static String msg;
	static String vin_car = null;
	private static String CarSelected;
	String ruta = null;
	public static  JLabel ClockLabel;
	private static JLabel labelDate_1;
	private static JLabel Gx_1;
	private static JLabel Gy_1;
	private static JRadioButtonMenuItem qs2kw;
	private JMenu mnEngine;
	private static JRadioButtonMenuItem qs4kw;
	private static JRadioButtonMenuItem qs6kw;
	private static JRadioButtonMenuItem qs8kw;
	private static JRadioButtonMenuItem pe3kw;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JCheckBoxMenuItem datalog;
	public static String hrs;
	public static JTextField weightCar;
	public static plot plt;
	public static void main( String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionArduino frame = new ConexionArduino();
					frame.setVisible(true);
					
					
					DigitalClock reloj = new DigitalClock(543,286,90,24);
					reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					reloj.setFont(new Font("Comfortaa Light", Font.BOLD | Font.ITALIC, 15));
					reloj.setForeground(Color.white);
					reloj.setVisible(true);
					hrs = reloj.getText();
					frame.getContentPane().add(reloj);	
					
					

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		});

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ConexionArduino() throws IOException {

		super();
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Volt Motors Test Bench");
		
		


		
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/volt/Escritorio/java serial/ConexionSerial/images/image72.png"));
		
		setFont(new Font("OCR-B 10 BT", Font.BOLD, 15));
		t = new Thread(new leerSerial());
		t.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,794,475);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnEngine = new JMenu("Engine");
		menuBar.add(mnEngine);

		qs2kw = new JRadioButtonMenuItem("2kw QS");
		buttonGroup.add(qs2kw);
		mnEngine.add(qs2kw);

		qs4kw = new JRadioButtonMenuItem("4kw QS");
		qs4kw.setSelected(true);
		buttonGroup.add(qs4kw);
		mnEngine.add(qs4kw);

		qs6kw = new JRadioButtonMenuItem("6kw QS");
		buttonGroup.add(qs6kw);
		mnEngine.add(qs6kw);

		qs8kw = new JRadioButtonMenuItem("8kw QS");
		buttonGroup.add(qs8kw);
		mnEngine.add(qs8kw);

		pe3kw = new JRadioButtonMenuItem("3kw PE");
		buttonGroup.add(pe3kw);
		mnEngine.add(pe3kw);
		
		mnBattery = new JMenu("Battery");
		menuBar.add(mnBattery);
		
		batt10kw = new JRadioButtonMenuItem("72V 135A (10Kw/h)");
		buttonGroup_1.add(batt10kw);
		mnBattery.add(batt10kw);
		
		batt20kw = new JRadioButtonMenuItem("72V 270A (20Kw/h)");
		batt20kw.setSelected(true);
		buttonGroup_1.add(batt20kw);
		mnBattery.add(batt20kw);
		
		batt22kw = new JRadioButtonMenuItem("72v 300A (22Kw/h)");
		buttonGroup_1.add(batt22kw);
		mnBattery.add(batt22kw);

		JMenu mnDatalog = new JMenu("DataLog");
		menuBar.add(mnDatalog);

		newLog = new JCheckBoxMenuItem("New Log");
		mnDatalog.add(newLog);

		datalog = new JCheckBoxMenuItem("Record Data");
		datalog.setEnabled(false);
		mnDatalog.add(datalog);
		
		mntmHowToUse = new JMenuItem("About DataLog");
		mntmHowToUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaltaLog about = new DaltaLog();
				about.setVisible(true);
				
			}
		});
		mnDatalog.add(mntmHowToUse);

		JMenu mnPlot = new JMenu("Plot");
		mnPlot.setEnabled(false);
		menuBar.add(mnPlot);

		newPlot = new JCheckBoxMenuItem("new plot");
		mnPlot.add(newPlot);

		plotBatt = new JCheckBoxMenuItem("Battery");
		mnPlot.add(plotBatt);
		window =   new JPanel();
		window.setForeground(new Color(255, 255, 255));
		window.setBackground(new Color(32,7,39));
		window.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLocation(null);
		setContentPane(window);


		JButton btnConnect = new JButton("CONNECT");
		btnConnect.setBounds(657, 46, 119, 24);
		btnConnect.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnConnect.setBackground(UIManager.getColor("Button.background"));
		btnConnect.addActionListener(new ActionListener() {
			
		
			
			public void actionPerformed(ActionEvent arg0) {
				
				weightCar.setEditable(false);
				String portConect = (String) comboSerial.getSelectedItem();
				try {

					if (newLog.isSelected()) {
						createfile();
						datalog.setEnabled(true);

					}

					if (newPlot.isSelected()) {
						
						plt = new plot("Battery","Voltage");
						plt.pack();
						RefineryUtilities.centerFrameOnScreen(plt);
						plt.setVisible(true);
						
						
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (portConect.equals(""))							
				{
					showError("NOT AVAILABLE PORTS");
				}
				else
					if (con == false){
						btnConnect.setText("DISCONNECT");
						lbl4.setForeground(Color.YELLOW);
						lbl4.setText("CONNECTING");
						conectar();
						lblTEMP.setForeground(Color.GREEN);
						lblHUM.setForeground(Color.GREEN);
						lblBATT_TEMP.setForeground(Color.GREEN);
						lblVOLT.setForeground(Color.GREEN);
						lblCURR.setForeground(Color.GREEN);
						lblSOC.setForeground(Color.GREEN);
						con = true;
					}
					else {
						desconectar();
						btnConnect.setText("CONNECT");
						lbl4.setForeground(Color.RED);
						lbl4.setText("DISCONNECT");
						lblTEMP.setForeground(Color.RED);
						lblHUM.setForeground(Color.RED);
						lblBATT_TEMP.setForeground(Color.RED);
						lblVOLT.setForeground(Color.RED);
						lblCURR.setForeground(Color.RED);
						lblSOC.setForeground(Color.RED);
						lblATM_PRESS.setForeground(Color.RED);
						lblSys1.setForeground(Color.RED);
						lblSys2.setForeground(Color.RED);

						con = false;
					}
			}
		});

		lblSys2 = new JLabel("");
		lblSys2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSys2.setBounds(201, 85, 281, 24);
		lblSys2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSys2.setHorizontalAlignment(SwingConstants.LEFT);
		lblSys2.setForeground(new Color(0, 128, 0));
		lblSys2.setBackground(Color.CYAN);

		JLabel lblNewLabel = new JLabel("BAT TEMP:");
		lblNewLabel.setBounds(483, 35, 104, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));

		lblTEMP = new JLabel("");
		lblTEMP.setBounds(5, 32, 103, 23);
		lblTEMP.setForeground(new Color(0, 128, 0));
		lblTEMP.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblTEMP.setBackground(Color.GRAY);
		lblTEMP.setHorizontalAlignment(SwingConstants.CENTER);

		lblHUM = new JLabel("");
		lblHUM.setBounds(5, 60, 103, 24);
		lblHUM.setForeground(new Color(0, 128, 0));
		lblHUM.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblHUM.setBackground(Color.GRAY);
		lblHUM.setHorizontalAlignment(SwingConstants.CENTER);

		lblBATT_TEMP = new JLabel("");
		lblBATT_TEMP.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBATT_TEMP.setBounds(584, 4, 68, 45);
		lblBATT_TEMP.setForeground(new Color(0, 128, 0));
		lblBATT_TEMP.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		lblBATT_TEMP.setBackground(Color.GRAY);
		lblBATT_TEMP.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblTelemetry = new JLabel("Weather");
		lblTelemetry.setBounds(5, 4, 103, 18);
		lblTelemetry.setForeground(new Color(102, 255, 102));
		lblTelemetry.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelemetry.setFont(new Font("Comfortaa", Font.BOLD, 16));
		window.setLayout(null);
		window.add(btnConnect);
		window.add(lblSys2);
		window.add(lblNewLabel);
		window.add(lblTEMP);
		window.add(lblHUM);
		window.add(lblBATT_TEMP);
		window.add(lblTelemetry);

		lbl4 = new JLabel("DISCONNECTED");
		lbl4.setBounds(638, 72, 159, 24);
		lbl4.setForeground(Color.RED);
		lbl4.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		window.add(lbl4);

		JLabel lblBattery = new JLabel("Battery");
		lblBattery.setBounds(277, 4, 182, 24);
		lblBattery.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattery.setForeground(new Color(153, 255, 102));
		lblBattery.setFont(new Font("Comfortaa", Font.BOLD, 16));
		window.add(lblBattery);

		lblVOLT = new JLabel("");
		lblVOLT.setVerticalAlignment(SwingConstants.BOTTOM);
		lblVOLT.setBounds(145, 4, 51, 45);
		lblVOLT.setForeground(new Color(0, 128, 0));
		lblVOLT.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		lblVOLT.setHorizontalAlignment(SwingConstants.LEFT);
		window.add(lblVOLT);

		JLabel lblGiroscopo = new JLabel("AMP:");
		lblGiroscopo.setBounds(212, 32, 68, 14);
		lblGiroscopo.setForeground(new Color(255, 255, 255));
		lblGiroscopo.setFont(new Font("Dialog", Font.BOLD, 16));
		window.add(lblGiroscopo);

		JLabel lblAcelerometro = new JLabel("V:");
		lblAcelerometro.setBounds(120, 32, 30, 14);
		lblAcelerometro.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAcelerometro.setForeground(new Color(255, 255, 255));
		window.add(lblAcelerometro);

		lblCURR = new JLabel("");
		lblCURR.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCURR.setBounds(259, 4, 68, 45);
		lblCURR.setHorizontalAlignment(SwingConstants.LEFT);
		lblCURR.setForeground(new Color(0, 128, 0));
		lblCURR.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		window.add(lblCURR);

		lblSOC = new JLabel("");
		lblSOC.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSOC.setBounds(408, 4, 61, 45);
		lblSOC.setHorizontalAlignment(SwingConstants.LEFT);
		lblSOC.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		lblSOC.setForeground(new Color(0, 128, 0));
		window.add(lblSOC);

		lbl10 = new JLabel("send: 0");
		lbl10.setFont(new Font("Dialog", Font.BOLD, 10));
		lbl10.setForeground(new Color(255, 255, 255));
		lbl10.setBounds(657, 95, 135, 14);
		window.add(lbl10);

		lbl11 = new JLabel("rec: 0");
		lbl11.setFont(new Font("Dialog", Font.BOLD, 10));
		lbl11.setForeground(new Color(255, 255, 255));
		lbl11.setBounds(657, 108, 147, 14);
		window.add(lbl11);

		labelCOM = new JLabel("");
		labelCOM.setBounds(638, 660, 159, 24);
		labelCOM.setBackground(Color.BLACK);
		labelCOM.setHorizontalAlignment(SwingConstants.CENTER);
		labelCOM.setFont(new Font("Space Ranger", Font.PLAIN, 18));
		labelCOM.setForeground(Color.CYAN);
		window.add(labelCOM);

		lblATM_PRESS = new JLabel("");
		lblATM_PRESS.setHorizontalAlignment(SwingConstants.CENTER);
		lblATM_PRESS.setBounds(0, 89, 108, 24);
		lblATM_PRESS.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblATM_PRESS.setForeground(new Color(0, 128, 0));
		window.add(lblATM_PRESS);

		JLabel lbl13 = new JLabel("SOC%:");
		lbl13.setBounds(350, 32, 68, 14);
		lbl13.setFont(new Font("Dialog", Font.BOLD, 16));
		lbl13.setForeground(new Color(255, 255, 255));
		window.add(lbl13);

		lblSys1 = new JLabel("");
		lblSys1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSys1.setBounds(201, 51, 281, 24);
		lblSys1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSys1.setForeground(new Color(0, 128, 0));
		window.add(lblSys1);
		separator.setBounds(108, -1, 9, 123);
		separator.setBackground(new Color(0, 191, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		window.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(5, 121, 792, 16);
		separator_1.setBackground(new Color(0, 191, 255));
		window.add(separator_1);

		JLabel lblNewLabel_2 = new JLabel("Status 1:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(120, 60, 96, 15);
		window.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Status 2:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(120, 89, 96, 15);
		window.add(lblNewLabel_2_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 191, 255));
		separator_2.setBounds(648, 4, 33, 422);
		window.add(separator_2);

		labelDate_1 = new JLabel("");
		labelDate_1.setVerticalAlignment(SwingConstants.BOTTOM);
		labelDate_1.setToolTipText("");
		labelDate_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate_1.setForeground(Color.WHITE);
		labelDate_1.setFont(new Font("Comfortaa Light", Font.BOLD | Font.ITALIC, 15));
		labelDate_1.setBounds(432, 283, 96, 24);
		window.add(labelDate_1);

		Date fecha = new Date();
		SimpleDateFormat fechFormat = new SimpleDateFormat("dd/MM/YYYY");
		date = fechFormat.format(new Date());
		labelDate_1.setText(date);

		comboSerial = new JComboBox();
		comboSerial.setBounds(657, 16, 119, 24);
		window.add(comboSerial);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBackground(new Color(0, 191, 255));
		separator_3.setBounds(211, 121, 9, 305);
		window.add(separator_3);

		JLabel lblLeftHub = new JLabel("Left HUB");
		lblLeftHub.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftHub.setForeground(new Color(102, 255, 102));
		lblLeftHub.setFont(new Font("Comfortaa", Font.BOLD, 16));
		lblLeftHub.setBounds(5, 124, 182, 36);
		window.add(lblLeftHub);

		JLabel lblRightHub = new JLabel("Right HUB");
		lblRightHub.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightHub.setForeground(new Color(102, 255, 102));
		lblRightHub.setFont(new Font("Comfortaa", Font.BOLD, 16));
		lblRightHub.setBounds(211, 121, 182, 36);
		window.add(lblRightHub);

		JLabel lblGps = new JLabel("RPM SENSOR");
		lblGps.setHorizontalAlignment(SwingConstants.CENTER);
		lblGps.setForeground(new Color(102, 255, 102));
		lblGps.setFont(new Font("Comfortaa", Font.BOLD, 16));
		lblGps.setBounds(443, 125, 187, 35);
		window.add(lblGps);

		JLabel lblRpm = new JLabel("RPM:");
		lblRpm.setForeground(Color.WHITE);
		lblRpm.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRpm.setBounds(10, 158, 51, 23);
		window.add(lblRpm);

		JLabel lblCurrent = new JLabel("CURRENT:");
		lblCurrent.setForeground(Color.WHITE);
		lblCurrent.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCurrent.setBounds(10, 183, 89, 23);
		window.add(lblCurrent);

		JLabel lblVoltage = new JLabel("VOLTAGE:");
		lblVoltage.setForeground(Color.WHITE);
		lblVoltage.setFont(new Font("Dialog", Font.BOLD, 15));
		lblVoltage.setBounds(10, 208, 89, 23);
		window.add(lblVoltage);

		JLabel lblTorque = new JLabel("TORQUE:");
		lblTorque.setForeground(Color.WHITE);
		lblTorque.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTorque.setBounds(10, 233, 80, 23);
		window.add(lblTorque);

		JLabel lblSpeed = new JLabel("SPEED:");
		lblSpeed.setForeground(Color.WHITE);
		lblSpeed.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSpeed.setBounds(10, 258, 68, 23);
		window.add(lblSpeed);

		JLabel lblTps = new JLabel("TPS %:");
		lblTps.setForeground(Color.WHITE);
		lblTps.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTps.setBounds(10, 283, 68, 23);
		window.add(lblTps);

		JLabel lblMotorTemp = new JLabel("MOTOR T:");
		lblMotorTemp.setForeground(Color.WHITE);
		lblMotorTemp.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMotorTemp.setBounds(10, 308, 98, 23);
		window.add(lblMotorTemp);

		JLabel lblContT = new JLabel("CONT T:");
		lblContT.setForeground(Color.WHITE);
		lblContT.setFont(new Font("Dialog", Font.BOLD, 15));
		lblContT.setBounds(10, 333, 74, 23);
		window.add(lblContT);

		JLabel lblStatus1Left = new JLabel("STATUS 1:");
		lblStatus1Left.setForeground(Color.WHITE);
		lblStatus1Left.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStatus1Left.setBounds(10, 358, 92, 23);
		window.add(lblStatus1Left);

		JLabel lblStatus_1 = new JLabel("STATUS 2:");
		lblStatus_1.setForeground(Color.WHITE);
		lblStatus_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStatus_1.setBounds(10, 383, 96, 23);
		window.add(lblStatus_1);

		JLabel lblRpm_1 = new JLabel("RPM:");
		lblRpm_1.setForeground(Color.WHITE);
		lblRpm_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRpm_1.setBounds(221, 158, 70, 23);
		window.add(lblRpm_1);

		JLabel lblCurrent_1 = new JLabel("CURRENT:");
		lblCurrent_1.setForeground(Color.WHITE);
		lblCurrent_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCurrent_1.setBounds(221, 183, 103, 23);
		window.add(lblCurrent_1);

		JLabel lblVoltage_1 = new JLabel("VOLTAGE:");
		lblVoltage_1.setForeground(Color.WHITE);
		lblVoltage_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblVoltage_1.setBounds(221, 208, 96, 23);
		window.add(lblVoltage_1);

		JLabel lblTorque_1 = new JLabel("TORQUE:");
		lblTorque_1.setForeground(Color.WHITE);
		lblTorque_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTorque_1.setBounds(221, 233, 89, 23);
		window.add(lblTorque_1);

		JLabel lblSpeed_1 = new JLabel("SPEED:");
		lblSpeed_1.setForeground(Color.WHITE);
		lblSpeed_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSpeed_1.setBounds(221, 258, 68, 23);
		window.add(lblSpeed_1);

		JLabel lblTps_1 = new JLabel("TPS %:");
		lblTps_1.setForeground(Color.WHITE);
		lblTps_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTps_1.setBounds(221, 283, 68, 23);
		window.add(lblTps_1);

		JLabel lblMotorTemp_1 = new JLabel("MOTOR T:");
		lblMotorTemp_1.setForeground(Color.WHITE);
		lblMotorTemp_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMotorTemp_1.setBounds(221, 308, 89, 23);
		window.add(lblMotorTemp_1);

		JLabel lblContT_1 = new JLabel("CONT T:");
		lblContT_1.setForeground(Color.WHITE);
		lblContT_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblContT_1.setBounds(221, 333, 82, 23);
		window.add(lblContT_1);

		JLabel lblStatus_2 = new JLabel("STATUS 1:");
		lblStatus_2.setForeground(Color.WHITE);
		lblStatus_2.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStatus_2.setBounds(221, 358, 96, 23);
		window.add(lblStatus_2);

		JLabel lblStatus_1_1 = new JLabel("STATUS 2:");
		lblStatus_1_1.setForeground(Color.WHITE);
		lblStatus_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStatus_1_1.setBounds(221, 383, 96, 23);
		window.add(lblStatus_1_1);

		lblpitch = new JLabel("PITCH:");
		lblpitch.setEnabled(false);
		lblpitch.setForeground(Color.WHITE);
		lblpitch.setFont(new Font("Dialog", Font.BOLD, 16));
		lblpitch.setBounds(543, 354, 70, 23);
		window.add(lblpitch);

		JLabel lblSpeedKmh = new JLabel("RPM RIGHT");
		lblSpeedKmh.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedKmh.setForeground(Color.WHITE);
		lblSpeedKmh.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSpeedKmh.setBounds(420, 157, 232, 23);
		window.add(lblSpeedKmh);

		JLabel lbldistance = new JLabel("RPM LEFT");
		lbldistance.setHorizontalAlignment(SwingConstants.CENTER);
		lbldistance.setForeground(Color.WHITE);
		lbldistance.setFont(new Font("Dialog", Font.BOLD, 16));
		lbldistance.setBounds(420, 207, 232, 23);
		window.add(lbldistance);

		lblLongitude = new JLabel("ROLL:");
		lblLongitude.setEnabled(false);
		lblLongitude.setForeground(Color.WHITE);
		lblLongitude.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLongitude.setBounds(543, 383, 68, 23);
		window.add(lblLongitude);

		pitch = new JLabel("");
		pitch.setEnabled(false);
		pitch.setHorizontalAlignment(SwingConstants.LEFT);
		pitch.setVerticalAlignment(SwingConstants.BOTTOM);
		pitch.setFont(new Font("Dialog", Font.BOLD, 15));
		pitch.setForeground(new Color(0, 191, 255));
		pitch.setBounds(609, 356, 33, 21);
		window.add(pitch);

		roll = new JLabel("");
		roll.setEnabled(false);
		roll.setHorizontalAlignment(SwingConstants.LEFT);
		roll.setForeground(new Color(0, 191, 255));
		roll.setFont(new Font("Dialog", Font.BOLD, 15));
		roll.setBounds(609, 385, 33, 21);
		window.add(roll);

		kmh = new JLabel("");
		kmh.setHorizontalAlignment(SwingConstants.CENTER);
		kmh.setForeground(new Color(0, 191, 255));
		kmh.setFont(new Font("Dialog", Font.BOLD, 15));
		kmh.setBounds(420, 183, 232, 21);
		window.add(kmh);

		distance = new JLabel("");
		distance.setHorizontalAlignment(SwingConstants.CENTER);
		distance.setForeground(new Color(0, 191, 255));
		distance.setFont(new Font("Dialog", Font.BOLD, 15));
		distance.setBounds(420, 233, 232, 21);
		window.add(distance);

		contTempLeft = new JLabel("");
		contTempLeft.setHorizontalAlignment(SwingConstants.CENTER);
		contTempLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		contTempLeft.setForeground(new Color(255, 165, 0));
		contTempLeft.setBounds(97, 333, 77, 21);
		window.add(contTempLeft);

		motorTempLeft = new JLabel("");
		motorTempLeft.setHorizontalAlignment(SwingConstants.CENTER);
		motorTempLeft.setForeground(new Color(255, 165, 0));
		motorTempLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		motorTempLeft.setBounds(97, 308, 77, 21);
		window.add(motorTempLeft);

		speedLeft = new JLabel("");
		speedLeft.setHorizontalAlignment(SwingConstants.CENTER);
		speedLeft.setForeground(new Color(255, 165, 0));
		speedLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		speedLeft.setBounds(97, 258, 77, 21);
		window.add(speedLeft);

		torqueLeft = new JLabel("");
		torqueLeft.setHorizontalAlignment(SwingConstants.CENTER);
		torqueLeft.setForeground(new Color(255, 165, 0));
		torqueLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		torqueLeft.setBounds(97, 233, 77, 21);
		window.add(torqueLeft);

		voltageLeft = new JLabel("");
		voltageLeft.setHorizontalAlignment(SwingConstants.CENTER);
		voltageLeft.setForeground(new Color(255, 165, 0));
		voltageLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		voltageLeft.setBounds(97, 208, 77, 21);
		window.add(voltageLeft);

		currentLeft = new JLabel("");
		currentLeft.setHorizontalAlignment(SwingConstants.CENTER);
		currentLeft.setForeground(new Color(255, 165, 0));
		currentLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		currentLeft.setBounds(97, 183, 77, 21);
		window.add(currentLeft);

		rpmLeft = new JLabel("");
		rpmLeft.setHorizontalAlignment(SwingConstants.CENTER);
		rpmLeft.setForeground(new Color(255, 165, 0));
		rpmLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		rpmLeft.setBounds(97, 158, 77, 21);
		window.add(rpmLeft);

		status1Left = new JLabel("");
		status1Left.setForeground(new Color(255, 165, 0));
		status1Left.setFont(new Font("Dialog", Font.BOLD, 15));
		status1Left.setBounds(97, 358, 104, 21);
		window.add(status1Left);

		status2Left = new JLabel("");
		status2Left.setForeground(new Color(255, 165, 0));
		status2Left.setFont(new Font("Dialog", Font.BOLD, 15));
		status2Left.setBounds(97, 383, 99, 21);
		window.add(status2Left);

		speedRight = new JLabel("");
		speedRight.setHorizontalAlignment(SwingConstants.CENTER);
		speedRight.setForeground(new Color(255, 215, 0));
		speedRight.setFont(new Font("Dialog", Font.BOLD, 15));
		speedRight.setBounds(331, 260, 77, 21);
		window.add(speedRight);

		torqueRight = new JLabel("");
		torqueRight.setHorizontalAlignment(SwingConstants.CENTER);
		torqueRight.setForeground(new Color(255, 215, 0));
		torqueRight.setFont(new Font("Dialog", Font.BOLD, 15));
		torqueRight.setBounds(331, 235, 77, 21);
		window.add(torqueRight);

		voltageRight = new JLabel("");
		voltageRight.setHorizontalAlignment(SwingConstants.CENTER);
		voltageRight.setForeground(new Color(255, 215, 0));
		voltageRight.setFont(new Font("Dialog", Font.BOLD, 15));
		voltageRight.setBounds(331, 210, 77, 21);
		window.add(voltageRight);

		currentRight = new JLabel("");
		currentRight.setHorizontalAlignment(SwingConstants.CENTER);
		currentRight.setForeground(new Color(255, 215, 0));
		currentRight.setFont(new Font("Dialog", Font.BOLD, 15));
		currentRight.setBounds(331, 185, 77, 21);
		window.add(currentRight);

		rpmRight = new JLabel("");
		rpmRight.setHorizontalAlignment(SwingConstants.CENTER);
		rpmRight.setForeground(new Color(255, 215, 0));
		rpmRight.setFont(new Font("Dialog", Font.BOLD, 15));
		rpmRight.setBounds(331, 162, 77, 21);
		window.add(rpmRight);

		status2Right = new JLabel("");
		status2Right.setForeground(new Color(255, 215, 0));
		status2Right.setFont(new Font("Dialog", Font.BOLD, 15));
		status2Right.setBounds(313, 385, 105, 21);
		window.add(status2Right);

		status1Right = new JLabel("");
		status1Right.setForeground(new Color(255, 215, 0));
		status1Right.setFont(new Font("Dialog", Font.BOLD, 15));
		status1Right.setBounds(312, 360, 104, 21);
		window.add(status1Right);

		contTempRight = new JLabel("");
		contTempRight.setHorizontalAlignment(SwingConstants.CENTER);
		contTempRight.setForeground(new Color(255, 215, 0));
		contTempRight.setFont(new Font("Dialog", Font.BOLD, 15));
		contTempRight.setBounds(331, 335, 77, 21);
		window.add(contTempRight);

		motorTempRight = new JLabel("");
		motorTempRight.setHorizontalAlignment(SwingConstants.CENTER);
		motorTempRight.setForeground(new Color(255, 215, 0));
		motorTempRight.setFont(new Font("Dialog", Font.BOLD, 15));
		motorTempRight.setBounds(331, 310, 77, 21);
		window.add(motorTempRight);

		tpsRight = new JLabel("");
		tpsRight.setForeground(new Color(255, 215, 0));
		tpsRight.setHorizontalAlignment(SwingConstants.CENTER);
		//		tpsRight.setForeground(new Color(255, 215, 0));
		tpsRight.setFont(new Font("Dialog", Font.BOLD, 15));
		tpsRight.setBounds(331, 285, 77, 21);
		window.add(tpsRight);

		tpsLeft = new JLabel("");
		tpsLeft.setHorizontalAlignment(SwingConstants.CENTER);
		tpsLeft.setFont(new Font("Dialog", Font.BOLD, 15));
		tpsLeft.setForeground(new Color(255, 140, 0));
		tpsLeft.setBackground(Color.DARK_GRAY);
		tpsLeft.setBounds(97, 283, 77, 21);
		window.add(tpsLeft);

		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setOrientation(SwingConstants.VERTICAL);
		separator_3_1.setBackground(new Color(0, 191, 255));
		separator_3_1.setBounds(422, 122, 9, 304);
		window.add(separator_3_1);

		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Comfortaa", Font.BOLD | Font.ITALIC, 12));
		lblPort.setForeground(new Color(102, 255, 102));
		lblPort.setBounds(678, 1, 70, 15);
		window.add(lblPort);

       
		//ClockLabel = new JLabel("");
		//ClockLabel.setForeground(Color.WHITE);
		//ClockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//ClockLabel.setFont(new Font("Comfortaa", Font.BOLD | Font.ITALIC, 15));
		//ClockLabel.setBounds(543, 279, 90, 24);
	    //ClockLabel.setText();
		//window.add(ClockLabel);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBackground(new Color(0, 191, 255));
		separator_1_1.setBounds(422, 313, 227, 2);
		window.add(separator_1_1);

		JLabel accell = new JLabel("Inclination");
		accell.setEnabled(false);
		accell.setFont(new Font("Comfortaa", Font.BOLD, 13));
		accell.setHorizontalAlignment(SwingConstants.LEFT);
		accell.setForeground(new Color(0, 128, 128));
		accell.setBounds(543, 337, 104, 15);
		window.add(accell);

		JLabel lblGforce = new JLabel("G-Force");
		lblGforce.setEnabled(false);
		lblGforce.setForeground(new Color(0, 128, 128));
		lblGforce.setFont(new Font("Comfortaa", Font.BOLD, 12));
		lblGforce.setHorizontalAlignment(SwingConstants.LEFT);
		lblGforce.setBounds(436, 338, 70, 15);
		window.add(lblGforce);

		Gx_1 = new JLabel("Gx:");
		Gx_1.setEnabled(false);
		Gx_1.setFont(new Font("Dialog", Font.BOLD, 16));
		Gx_1.setForeground(new Color(255, 255, 255));
		Gx_1.setBounds(436, 358, 70, 15);
		window.add(Gx_1);

		Gy_1 = new JLabel("Gy:");
		Gy_1.setEnabled(false);
		Gy_1.setForeground(new Color(255, 255, 255));
		Gy_1.setFont(new Font("Dialog", Font.BOLD, 16));
		Gy_1.setBounds(436, 387, 70, 15);
		window.add(Gy_1);

		JLabel lblNewLabel_1 = new JLabel("IMU Values");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setFont(new Font("Comfortaa", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setBounds(426, 320, 183, 15);
		window.add(lblNewLabel_1);

		btnD = new JButton("D");
		btnD.setBackground(UIManager.getColor("Button.background"));
		btnD.setEnabled(false);
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnR.setEnabled(false);
				   sendData("d");		
			}
		});
		btnD.setFont(new Font("Comfortaa", Font.BOLD, 20));
		btnD.setBounds(659, 130, 117, 57);
		window.add(btnD);

		btnN = new JButton("N");
		btnN.setEnabled(false);
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnR.setEnabled(true);
				btnD.setEnabled(true);
					   sendData("m");		
			}
		});
		btnN.setFont(new Font("Comfortaa", Font.BOLD, 20));
		btnN.setBounds(659, 190, 117, 57);
		window.add(btnN);

		btnR = new JButton("R");
		btnR.setEnabled(false);
		btnR.setFont(new Font("Comfortaa", Font.BOLD, 20));
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnD.setEnabled(false);
					   sendData("r");
			}
		});
		btnR.setBounds(659, 250, 117, 57);
		window.add(btnR);
		
		JLabel lblSetWeightCar = new JLabel("set car's  weight");
		lblSetWeightCar.setForeground(Color.WHITE);
		lblSetWeightCar.setBounds(657, 375, 119, 15);
		window.add(lblSetWeightCar);
		
		weightCar = new JTextField();
		weightCar.setHorizontalAlignment(SwingConstants.CENTER);
		weightCar.setFont(new Font("Dialog", Font.BOLD, 14));
		weightCar.setForeground(Color.BLACK);
		weightCar.setBounds(687, 395, 68, 19);
		window.add(weightCar);
		weightCar.setColumns(10);
		
		
		
		btnOn = new JButton("ON");
		btnOn.addActionListener(new ActionListener() {
			boolean ButtonState = true;
			public void actionPerformed(ActionEvent arg0) {
			if (ButtonState) {
				btnN.setEnabled(true);
				btnD.setEnabled(true);
				btnR.setEnabled(true);
				sendData("n");
				btnOn.setText("OFF");
				ButtonState = false;
			}
			else
				if(!ButtonState) {
					btnN.setEnabled(false);
					btnD.setEnabled(false);
					btnR.setEnabled(false);
			        sendData("f");
					btnOn.setText("ON");
					ButtonState = true;
				}
				
			}
		});
		btnOn.setBounds(659, 310, 117, 57);
		window.add(btnOn);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBackground(new Color(0, 191, 255));
		separator_1_1_1.setBounds(422, 279, 227, 2);
		window.add(separator_1_1_1);
		


		portConection();



	}

	public void SendDataInteger (int dataInt) {

		countPacketSend ++;
		lbl10.setText("send: " + String.valueOf(countPacketSend));
		int dataInteger = (int) dataInt;

		try {			  
			
			output.write(dataInteger);
		   
			
		} 
		catch (Exception e) {
			showError("Don't can data send");
			System.exit(ERROR);
		} 

	}


	public void portConection()
	{

		Enumeration puertos = CommPortIdentifier.getPortIdentifiers();


		while(puertos.hasMoreElements()){

			CommPortIdentifier port = (CommPortIdentifier) puertos.nextElement();
			System.out.println(port.getName());
			String listSerial =  port.getName();
			SerialList.add(listSerial);
			comboSerial.addItem(listSerial);
			PORT_NAME = port.getName();

		}

	}


	public void desconectar(){

		serialport.removeEventListener();
		serialport.close();
		t.interrupt();


	}

	public void conectar ()
	{
		Enumeration puertos = CommPortIdentifier.getPortIdentifiers();


		while(puertos.hasMoreElements()){

			CommPortIdentifier port = (CommPortIdentifier) puertos.nextElement();
			System.out.println(port.getName());

			PORT_NAME = (String) comboSerial.getSelectedItem();
			labelCOM.setText(PORT_NAME);
			if(PORT_NAME.equals(port.getName())){			

				try{
					serialport = (SerialPort)port.open(this.getClass().getName(),TIME_OUT);
					serialport.setSerialPortParams(DATA_RATE,
							SerialPort.DATABITS_8,
							SerialPort.STOPBITS_1 , 
							SerialPort.PARITY_NONE);
					serialport.setDTR(true);
					output = serialport.getOutputStream();
					input = serialport.getInputStream();
					serialport.addEventListener(this);
					serialport.notifyOnDataAvailable(true);

				}
				catch(Exception e)
				{
					showError("No Conection!!");
					System.exit(ERROR);
				}

			}
		}


	}

	public void sendData(String data){
		countPacketSend ++;
		lbl10.setText("send: " + String.valueOf(countPacketSend));
		byte [] m = data.getBytes();

		try {
			for (int i = 0 ; i<100; i++) {
			output.write(m);
			}
		} 
		catch (Exception e) {
			showError("Don't can data send");
			System.exit(ERROR);
		} 
	}


	private void showError (String errorMessage)
	{
		JOptionPane.showMessageDialog(window,errorMessage,"ERROR",JOptionPane.ERROR_MESSAGE);
	}

	public static class leerSerial implements Runnable
	{
		byte aux;
		private int rpmR;
		private int curr;
		private int torqR;
		private int motTemp;
		private int contTemp;
		private String CanMsgMr;
		private String CanMsgE2Mr;
		private int currL;
		private int rpmL;
		private int torql;
		private int motTempl;
		private int contTempl;
		private String CanMsgMl;
		private String CanMsgE2Ml;
		private int tpsR;
		private int tpsl;
		private int kmhR;
		private int kmhL;


		@Override
		public void run() {

			while (true){
				try{
					if (input.available()!= 'z')
						aux = (byte) input.read();
					//System.out.println(aux);

					lbl11.setText("rec: " + String.valueOf(countPacketReceived));

					switch(aux) {

					case 0x65:
						lbl4.setForeground(Color.RED);
						lbl4.setText("CONNECTION ERROR!!");
						countPacketReceived ++;
						break;

					case 0x45: 
						lbl4.setText("CONNECT");
						lbl4.setForeground(Color.GREEN);
						countPacketReceived ++;
						break;

					case 0x74:
						temperatura =  input.read();
						lblTEMP.setForeground(Color.GREEN); 
						lblTEMP.setText(String.valueOf(temperatura)+" C");
						countPacketReceived++;
						break;

					case 0x68:
						humedad = input.read();
						lblHUM.setForeground(Color.GREEN);
						lblHUM.setText(String.valueOf(humedad) + "%");			
						countPacketReceived++;
						break;

					case 0x42: 

						bTemp =  input.read();
						if(bTemp >= 55 ){

							lblBATT_TEMP.setForeground(Color.RED);
							lblBATT_TEMP.setText(String.valueOf(bTemp) + " C");
							countPacketReceived++;
						}
						else
						{ 
							lblBATT_TEMP.setForeground(Color.GREEN);
							lblBATT_TEMP.setText(String.valueOf(bTemp) + " C");
							countPacketReceived++;
						}
						break;

					case 0x76:	
						bateria = input.read();
						lblVOLT.setForeground(Color.GREEN);
						lblVOLT.setText(String.valueOf(bateria));
						countPacketReceived++;

						break;

					case 0x61:
						int currentMSB = input.read();
						int currentLSB = input.read();
						current = (int) ((((currentMSB << 8) | currentLSB)- 32000)*0.1);


						if (current >= 400) {
							lblCURR.setForeground(Color.RED);
							lblCURR.setText(String.valueOf(current));
							countPacketReceived++;
						}
						else	

						{
							lblCURR.setForeground(Color.GREEN);
							lblCURR.setText(String.valueOf(current));
							countPacketReceived++;
						}

						break;

					case 0x73: 
						soc=  input.read();

						if(soc <= 30){
							lblSOC.setForeground(Color.RED);
							lblSOC.setText(String.valueOf(soc));
							countPacketReceived++;

						}
						else
						{ 
							lblSOC.setForeground(Color.GREEN);
							lblSOC.setText(String.valueOf(soc));
							countPacketReceived++;
						}

						break;

					case 0x4D: 
						int sys1 = input.read();
						msg = null;
						switch (sys1) {
						case  0: msg = "OK";
						break;
						case  1: msg = "CELL OVER CHARGE";
						break;
						case  2: msg = "CELL OVER DISCHARGE";
						break;
						case  4: msg = "OVER TEMPERATURE";
						break;
						case  8: msg = "OVER CURRENTE DISCHARGE";
						break;
						case 16: msg = "OVER LOW SOC";
						break;
						case 32: msg = "LOW INSULATION";
						break;
						case 64: msg = "OVER LOW INSULATION";
						break;
						case 128: msg = "COMM INTERRUPTION";
						break;

						}

						lblSys1.setText(msg);
						lblSys1.setForeground(Color.GREEN);
						countPacketReceived++;

						break;

					case 0x6D:
						int sys2 = input.read();
						System.out.println(sys2);
						msg2 = null;

						switch (sys2) {

						case 0: msg2 = "OK";
						break;
						case 1: msg2 = "OVER HIGH TOTAL VOLTAGE";
						break;
						case 2: msg2 = "OVER LOW TOTAL VOLTAGE";
						break;
						case 4: msg2 = "OVER CURRENT CHARGE";
						break;
						case 8: msg2 = "LOW TEMPERATURE";
						break;
						case 16: msg2 = "OVER LARGER TEMPERATURE DIFFERENCE";
						break;
						case 32: msg2 = "OVER LARGER VOLAGE DIFFERENCE";
						break;
						case 64: msg2 = "TEMPERATURE INCREASE SPEED OVER FAST";
						break;
						case 128: msg2 = "SHORT CIRCUIT PROTECTION";
						break;
						}
						lblSys2.setText(msg2);
						lblSys2.setForeground(Color.GREEN);
						countPacketReceived++;
						break;

					case 0x70: 
						Pat =  input.read()*10;
						System.out.println(Pat);
						lblATM_PRESS.setForeground(Color.GREEN);
						lblATM_PRESS.setText(Pat + " Pa");
						countPacketReceived++;

						break;

					case 0x72: 	
						int  rpmMsb = input.read();
						int  rpmLsb = input.read();
						rpmL = (rpmMsb << 8) | rpmLsb;
						System.out.println(rpmL);
						rpmLeft.setText(String.valueOf(rpmL));
						countPacketReceived++;

						break;

					case 0x63: 
						currL = input.read();
						System.out.println(currL);
						currentLeft.setText(String.valueOf(currL));
						countPacketReceived++;

						break;

					case 0x56: 
						int VolMl = input.read();
						System.out.println(VolMl);
						voltageLeft.setText(String.valueOf(VolMl));
						countPacketReceived++;

						break;

					case 0x71: 
						torql = input.read();
						System.out.println(torql );
						torqueLeft.setText(String.valueOf(torql ));
						countPacketReceived++;

						break;

					case 0x6B: 
						kmhL = input.read();
						System.out.println(kmhL);
						speedLeft.setText(String.valueOf(kmhL));
						countPacketReceived++;

						break;

					case 0x55: 
						tpsl = input.read();
						System.out.println(tpsl);
						tpsLeft.setText(String.valueOf(tpsl));
						countPacketReceived++;

						break;

					case 0x2A: 	
						motTempl = input.read();
						System.out.println(motTempl);
						motorTempLeft.setText(String.valueOf(motTempl));
						countPacketReceived++;

						break; 

					case 0x3F: 
						contTempl = input.read();
						System.out.println(contTempl);
						contTempLeft.setText(String.valueOf(contTempl));
						countPacketReceived++;

						break;

					case 0x6F: 
						CanMsgMl = null;
						int stat1l=input.read();
						System.out.println(stat1l);
						switch (stat1l) {
						case 0: CanMsgMl = "OK";
						break;
						case 1: CanMsgMl = "HALL ERROR";
						break;
						case 2: CanMsgMl = "OVER VOLTAGE";
						break;
						case 4: CanMsgMl = "LOW VOLTAGE";
						break;
						case 8: CanMsgMl = "NO ERROR";
						break;
						case 16: CanMsgMl = "STALL ERROR";
						break;
						case 32: CanMsgMl = "INTERNAL FAULT" ;
						break;
						case 64: CanMsgMl = "OVER TEMP";
						break;
						case 128: CanMsgMl = "THROOTLE ERROR";
						break;
						}
						status1Left.setText(CanMsgMl);
						countPacketReceived++;

						break;

					case 0x4F: 
						CanMsgE2Ml = null;
						int stat2l =  input.read();

						System.out.println(stat2l);
						switch (stat2l) {

						case 0:  CanMsgE2Ml = "OK";
						break;
						case 1:  CanMsgE2Ml = "NO ERROR";
						break;
						case 2:  CanMsgE2Ml = "INTERNAL RESET";
						break;
						case 4:  CanMsgE2Ml = "HALL THROOTLE ERROR";
						break;
						case 8:  CanMsgE2Ml = "ANGLE SENSOR ERROR";
						break;
						case 16:  CanMsgE2Ml = "NO ERROR";
						break;
						case 32:  CanMsgE2Ml = "NO ERROR";
						break;
						case 64:  CanMsgE2Ml = "MOTOR OVER TEMPERATURE";
						break;
						case 128: CanMsgE2Ml = "HALL GALVANOMETER ERROR";
						break;
						}
						status2Left.setText(CanMsgE2Ml);
						countPacketReceived++;

						break;

					case 0x52:
						int rpmMsbR = input.read();
						int rpmLsbR = input.read();
						rpmR = (rpmMsbR << 8) | rpmLsbR;
						System.out.println(rpmR);
						rpmRight.setText(String.valueOf(rpmR));
						countPacketReceived++;

						break;

					case 0x43: 
						curr = input.read();
						System.out.println(curr);
						currentRight.setText(String.valueOf(curr));
						countPacketReceived++;

						break;

					case 0x21:
						int VolMr = input.read();
						System.out.println(VolMr);
						voltageRight.setText(String.valueOf(VolMr));
						countPacketReceived++;

						break;

					case 0x23: 
						torqR = input.read();
						System.out.println(torqR);
						torqueRight.setText(String.valueOf(torqR));
						countPacketReceived++;

						break;

					case 0x24: 
						kmhR = input.read();
						System.out.println(kmhR);
						speedRight.setText(String.valueOf(kmhR));
						countPacketReceived++;

						break;

					case 0x25: 
						tpsR = input.read();
						System.out.println(tpsR);
						tpsRight.setText(String.valueOf(tpsR));
						countPacketReceived++;

						break;

					case 0x26:
						motTemp = input.read();
						System.out.println(motTemp);
						motorTempRight.setText(String.valueOf(motTemp));
						countPacketReceived++;

						break;

					case 0x2F:
						contTemp = (byte)input.read();
						System.out.println(contTemp);
						contTempRight.setText(String.valueOf(contTemp));
						countPacketReceived++;

						break;

					case 0x28:
						CanMsgMr = null;
						int stat1= input.read();
						System.out.println(stat1);
						switch (stat1) {
						case 0: CanMsgMr = "OK";
						break;
						case 1: CanMsgMr = "HALL ERROR";
						break;
						case 2: CanMsgMr = "OVER VOLTAGE";
						break;
						case 4: CanMsgMr = "LOW VOLTAGE";
						break;
						case 8: CanMsgMr = "NO ERROR";
						break;
						case 16: CanMsgMr = "STALL ERROR";
						break;
						case 32: CanMsgMr = "INTERNAL FAULT" ;
						break;
						case 64: CanMsgMr = "OVER TEMP";
						break;
						case 128: CanMsgMr = "THROOTLE ERROR";
						break;
						}
						status1Right.setText(CanMsgMr);
						countPacketReceived++;

						break;

					case 0x29: 
						CanMsgE2Mr = null;
						int stat2= input.read();
						System.out.println(stat2);
						switch (stat2) {

						case 0:  CanMsgE2Mr = "OK";
						break;
						case 1:  CanMsgE2Mr = "NO ERROR";
						break;
						case 2:  CanMsgE2Mr = "INTERNAL RESET";
						break;
						case 4:  CanMsgE2Mr = "HALL THROOTLE ERROR";
						break;
						case 8:  CanMsgE2Mr = "ANGLE SENSOR ERROR";
						break;
						case 16:  CanMsgE2Mr = "NO ERROR";
						break;
						case 32:  CanMsgE2Mr = "NO ERROR";
						break;
						case 64:  CanMsgE2Mr = "MOTOR OVER TEMPERATURE";
						break;
						case 128: CanMsgE2Mr = "HALL GALVANOMETER ERROR";
						break;
						}

						status2Right.setText(CanMsgE2Mr);
						countPacketReceived++;

						break;

					case 0x6C:
						int Pitch= input.read();
						//pitch.setText(String.valueOf(Pitch));
						countPacketReceived++;

						break;

					case 0x4C:
						int Roll = input.read();
						//roll.setText(String.valueOf(Roll));
						countPacketReceived++;

						break;

					case 0x77:
						int Gx= input.read();
						int Gxx = input.read();
						//Gx_1.setText("Gx:" + String.valueOf(Gx) + "." + String.valueOf(Gxx));
						countPacketReceived++;

						break;

					case 0x78:
						int Gy= input.read();
						int Gyy = input.read();
						//Gy_1.setText("Gy:" + String.valueOf(Gy) + "." + String.valueOf(Gyy));
						countPacketReceived++;

						break;

					case 0x41:
						int Alt = input.read();
						//altitude.setText(String.valueOf(Alt*2));
						countPacketReceived++;

						break;

					case 0x4B:
					    char Kmh = (char) input.read();
						kmh.setText(String.valueOf(Kmh));
						countPacketReceived++;

						break;

					case 0x48:
						char dist = (char) input.read();
						distance.setText(String.valueOf(dist));
						countPacketReceived++;

						break;

					case 0x57:
						int aSat =  input.read();
						//avSat.setText(String.valueOf(aSat));
						countPacketReceived++;

						break;

					case 0x4A:
						int Sat =  input.read();

						if (Sat == 1) {
							//StateSat.setText("Successful connection");
						}
						else
							//StateSat.setText("Searching Satellites");
						countPacketReceived++;

						break;

					}

					if (datalog.isSelected()) {  
							
						int tpsValue = (tpsR + tpsl) / 2;
						int kmhValue = (kmhR + kmhL) / 2;
						CSV_Write(tpsValue,kmhValue);
						
					}

					if (plotBatt.isSelected()) {
						
						plt.createDataset(bateria ,"Battery","Seconds");
					
					}



					Thread.sleep((long) 0.10);

				}

				catch(Exception e)
				{

				}
			}
		}
	}


	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub

	}

	static String engineSelected = null;
	static String batterySelected = null;
	static String fileName = null;
	static FileWriter writer;
	static String date;
	private static JCheckBoxMenuItem plotBatt;
	private JCheckBoxMenuItem newPlot;
	
	private JCheckBoxMenuItem newLog;
	private JMenuItem mntmHowToUse;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static JRadioButtonMenuItem batt10kw;
	private static JRadioButtonMenuItem batt20kw;
	private JMenu mnBattery;
	private static JRadioButtonMenuItem batt22kw;
	private JButton btnD;
	private JButton btnN;
	private JButton btnR;
	private JButton btnOn;
	private static JLabel lblNewLabel_3;

	public static void createfile() throws IOException {


		if (qs2kw.isSelected())
		{engineSelected = "QS 2 KW";}
		else
			if (qs4kw.isSelected())
			{engineSelected = "QS 4 KW";}
			else
				if (qs6kw.isSelected())
				{engineSelected = "QS 6 KW";}
				else
					if (qs8kw.isSelected()) 
					{engineSelected = "QS 8 KW";}
					else
						if (pe3kw.isSelected()) 
						{engineSelected = "PE 3 KW";}
		
		if (batt10kw.isSelected())
		{batterySelected = "72V 135A (10Kw/h)";}
		else
			if (batt20kw.isSelected())
			{batterySelected = "72V 270A (20Kw/h)";}
			else
				if (batt22kw.isSelected())
				{batterySelected = "72V 300A (22Kw/h)";}
				

		String fileName = "Test " + engineSelected + "_" + batterySelected +".csv";

		try {

			File archivo = new File (fileName);

			writer = new FileWriter(archivo, true); 
			writer.write( 
					      "Date" + "," +  date + '\n' +
						  "Weather" + "," + temperatura + "," + humedad + "," + Pat + '\n' +
					      "Motor" + "," + engineSelected + '\n' +
					      "Battery" + "," + batterySelected + '\n' +					      
					      "Weight Car" + "," + weightCar.getText() + '\n' +
					      "Battery Volt" + "," + "Battery Current" + "," + "SOC%" + ","  + "Battery Temp" + "," + " Battery State1" + "," + "Battery State 2" + "," +
					      "    " + "," +  // column
					      "RM Current" + "," + "RM RPM" + "," + "RM Torque" + "," + "RM Temp" + "," + "RM Temp Cont" + "," + "RM State1" + "," + "RM State2" + "," + 
					      "    " + "," + // column
					      "LM Current" + "," + "LM RPM" + "," + "LM Torque" + "," + "LM Temp" + "," + "LM Temp Cont" + "," + "LM State1" + "," + "LM State2" + "," + 
					      "    " + "," +
					      "TPS" + "," + "Km/h" + "," + " " + "," + "Hour" + "," + "Temperature" + "," + "Humidity" + "," + "Atm Pressure" + "," +
					      "Pitch" + "," + "Roll" +
					      '\n'
					      );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR csv");
			e.printStackTrace();
		}

	}

	public static void CSV_Write(int tps, int kmh) {


		try {
			System.out.println("Escribiendo csv");
			
	  writer.append(
			        lblVOLT.getText() + "," + lblCURR.getText() + "," + lblSOC.getText() +  "," + lblBATT_TEMP.getText() + "," + lblSys1.getText() +  "," + lblSys2.getText() + "," + "    " + "," +
					currentRight.getText() + "," + rpmRight.getText() + "," + torqueRight.getText() + "," + motorTempRight.getText() + "," + contTempRight.getText() + "," + status1Right.getText() + "," + status2Right.getText()+ "," + "   " + "," +
					currentLeft.getText() + "," + rpmLeft.getText() + "," + torqueLeft.getText() + "," + motorTempLeft.getText()+ "," + contTempLeft.getText() + "," + status1Left.getText() + "," + status2Left.getText() + "," + "   " + "," +
					tps + "," + kmh + "," + "" + "," + hrs + "," + lblTEMP.getText() + "," + lblHUM.getText() + "," + lblATM_PRESS.getText() + "," +
					pitch.getText()+ "," + roll.getText() +
					'\n'
					);
			//writer.close();

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR csv");
			e.printStackTrace();
		}
	}

	public void run() {
		// TODO Auto-generated method stub

	}
}

