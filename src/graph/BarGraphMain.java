package graph;
import javax.swing.JFrame;


public class BarGraphMain {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Bar Graph");
		
		BarGraphData data = new BarGraphData(30, 5);
		BarGraph barGraph = new BarGraph(data);
		barGraph.update();
		barGraph.setVisible(true);
		
		mainFrame.add(barGraph);
		mainFrame.setSize(470, 800);
		mainFrame.setVisible(true);
	}

}
