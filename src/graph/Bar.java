package graph;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


public class Bar {

	double value;
	Color color;
	
	public Bar(double value, Color color) {
		this.value = value;
		this.color = color;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void paint(Graphics2D g, Point bottomLeft, double maximum, int width, double scale) {
		Color oldColor = g.getColor();
		g.setColor(this.color);
		int height = (int) (value*scale);
		//System.out.println("Filling bar : " + bottomLeft + " " + width + " " + height );
		g.fillRect(bottomLeft.x, bottomLeft.y, width, height);
		g.setColor(oldColor);
	}
	
	public Color getColor() {
		return this.color;
	}
}
