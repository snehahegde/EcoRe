package graph;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class BarGraph extends JPanel {

	DataSource dataSource;
	ArrayList<BarGroup> dataBars;
	ArrayList<JLabel> legendLabel;
	ArrayList<JLabel> yAxisLabels;
	ArrayList<JLabel> xAxisLabels;
	
	public BarGraph(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		dataBars = new ArrayList<>();
		legendLabel = new ArrayList<>();
		yAxisLabels = new ArrayList<>();
		xAxisLabels = new ArrayList<>();
		for(int i=0; i<50; i++) {
			JLabel l = new JLabel();
			yAxisLabels.add(l);
			this.add(l);
		}
		
	}
	
	private void createBarGroup() {
		int components = dataSource.getDataTypes();
		int dataCount = dataSource.getDataPoints();

		dataBars = new ArrayList<>();

		for(int i=0; i<dataCount; i++) {
			BarGroup bg = new BarGroup(components);
			dataBars.add(bg);
			//this.add(bg.getLabel());
			//JLabel l = new JLabel();
			//legendLabel.add(l);
			//this.add(l);
		}
	}
	
	public void update() {
		createBarGroup();
		ArrayList<ArrayList<Double>> values = dataSource.getValues();
		ArrayList<String> sources = dataSource.getDataSourceLabels();
		for(int i=0; i<dataBars.size(); i++) {
			System.out.println("Databar: " + i);
			ArrayList<Double> barGroupValues = values.get(i);
			String source = "";
			if( sources != null ) {
				source = sources.get(i);
			}
			dataBars.get(i).update(barGroupValues, source);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		//update();

		System.out.println("********   painting graph    **********");
		
		// Get the max
		double maxValue = 0;
		for(BarGroup bg: dataBars) {
			double val = bg.getValue();
			if(val > maxValue) {
				maxValue = val;
			}
		}

		double scale = 100 / maxValue;
		System.out.println("Scale: " + scale);
		
		Graphics2D graphics = (Graphics2D)g;
		int positionY = getHeight() - 200;
		
		int graphStartX = 50;
		int space = 75;
		int positionX = graphStartX;
		int left = positionX + 25;
		for(BarGroup bg: dataBars) {
			bg.paint(graphics, positionY, left, scale);
			left += space;
		}
		
		/*
		ArrayList<String> dataTypeLabels = dataSource.getDataTypeLabels();
		if(dataBars.size() > 0) {
			BarGroup bg = dataBars.get(0);
			Bar[] bars = bg.getBars();
			//System.out.println("Bars: " + bars.length);
			for(int i=0; i<bars.length; i++) {
				Color oldColor = graphics.getColor();
				graphics.setColor(bars[i].getColor());
				int labelYposition = positionY+30*i+20;
				graphics.fillRect(10, labelYposition+5, 10, 10);
				
				JLabel l = legendLabel.get(i);
				l.setText(dataTypeLabels.get(i));
				l.setLocation(30, labelYposition);
				
				graphics.setColor(oldColor);
			}
		}
		*/
		graphics.drawLine(positionX, 100, positionX, positionY);
		graphics.drawLine(positionX, positionY, getWidth(), positionY);
		int notchPosition = positionY;
		for(int i=0; i<10; i++) {
			graphics.drawLine(positionX-10, notchPosition, positionX, notchPosition);
			if(yAxisLabels != null && yAxisLabels.size() > i) {
				JLabel l = yAxisLabels.get(i);
				l.setLocation(10, notchPosition-10);
				l.setSize(100, 20);
				l.setVisible(true);
				l.setText(String.valueOf(50*i/scale));
			}

			notchPosition -= 50;
		}
		
		
	}

}
