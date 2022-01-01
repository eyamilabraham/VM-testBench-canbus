import java.awt.Color;
import java.util.*;

import javax.swing.JLabel;

public class DigitalClock extends JLabel implements Runnable {
	
	public String hrs;

	private String hora, minutos, segundos, dia, mes, año, ampm;
	private Calendar calendario = new GregorianCalendar();
;
	Thread thread1;



	public DigitalClock(int x, int y, int p, int p1) {
		setBounds(x,y,p,p1);		
		thread1= new Thread (this);
		thread1.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		Thread ct = Thread.currentThread();

		while (ct == thread1) {


			try {
				actualiza();

				setText(hora + ":" + minutos + ":" + segundos);
				hrs = getText();
				//System.out.println(hrs);
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {

			}


		}
	}



	private void actualiza() {

		Date fechaHoraActual = new Date();
		calendario.setTime(fechaHoraActual);

		hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
		minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
		dia = calendario.get(Calendar.DATE) > 9 ? "" + calendario.get(Calendar.DATE) : "0" + calendario.get(Calendar.DATE);
		mes = calendario.get(Calendar.MONTH) > 9 ? "" + calendario.get(Calendar.MONTH) : "0" + calendario.get(Calendar.MONTH);
		año = calendario.get(Calendar.YEAR) > 9 ? "" + calendario.get(Calendar.YEAR) : "0" + calendario.get(Calendar.YEAR);
		
	}




}
