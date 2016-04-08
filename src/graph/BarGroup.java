package graph;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;


public class BarGroup {

	String source;
	Bar[] bars;
	double value;
	JLabel sourceLabel;
	
	public BarGroup(int numComponents) {
		createBars(numComponents);
		value = 0;
		sourceLabel = new JLabel();
	}
	
	private void createBars(int components) {
		bars = new Bar[components];
		for(int i=0; i<bars.length; i++) {
			float hue = (float) (i*0.17);
			float saturation = (float) 0.7;
			float brightness = (float) 0.8;
			Color c = Color.getHSBColor(hue, saturation, brightness);
			bars[i] = new Bar(0, c);
		}
	}
	
	public void update(ArrayList<Double> values, String source) {
		createBars(values.size());
		this.value = 0;
		for(int i=0; i<bars.length; i++) {
			double value = values.get(i);
			bars[i].setValue(value);
			System.out.println("Calue of bar " + i + " is " + value);
			this.value += value;
		}
		this.source = source;
		sourceLabel.setText(source);
		System.out.println("Total value: " + this.value);
		
	}
	
	public JLabel getLabel() {
		return this.sourceLabel;
	}
	
	public void paint(Graphics2D g, int bottom, int left, double scale) {
		System.out.println("@@@@@@@ paint @@@@@@@");
		Point bottomLeft = new Point(left, bottom);
		int width = 50;
		for(Bar bar: bars) {
			int barHeight = (int) (bar.getValue()*scale);
			bottomLeft.translate(0, -1*barHeight);
			double scaledValue = this.value*scale;
			bar.paint(g, bottomLeft, scaledValue, width, scale);
		}
		
		sourceLabel.setLocation(left, bottom+5);
		sourceLabel.setSize(50, 10);
		sourceLabel.setVisible(true);
	}
	
	public Bar[] getBars() {
		return bars;
	}
	
	public double getValue() {
		return this.value;
	}
}
