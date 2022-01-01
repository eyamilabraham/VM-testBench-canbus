import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class plot extends ApplicationFrame {
	
	public plot (String applicationTitle, String chartTitle) {
		super (applicationTitle);
		
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"X",
				"Y",
				createDataset(0,"",""),
				PlotOrientation.VERTICAL,
				true,true,false
				);
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize(new java.awt.Dimension(250,200));
		setContentPane (chartPanel);
			
		}
	
	public static DefaultCategoryDataset createDataset(int value, String text, String time) {
		 
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
		 
		 	  dataset.addValue(value, text , time );
		  
		 
		 return dataset;
		 
	 }
	
	

}
