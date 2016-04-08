package graph;
import java.util.ArrayList;
import java.util.Random;


public class BarGraphData implements DataSource {

	int types;
	int dataCount;
	ArrayList<ArrayList<Double>> data;
	
	public BarGraphData(int dataCount, int types) {
		this.types = types;
		this.dataCount = dataCount;
		data = new ArrayList<ArrayList<Double>>();
		for(int i=0; i<dataCount; i++) {
			ArrayList<Double> d = new ArrayList<Double>();
			for(int j=0; j<getDataTypes(); j++) {
				double value = new Random().nextDouble();
				value *= 100.0;
				d.add(value);
			}
			data.add(d);
		}
	}
	
	@Override
	public int getDataTypes() {
		return types;
	}

	@Override
	public ArrayList<ArrayList<Double>> getValues() {
		return data;
	}

	@Override
	public int getDataPoints() {
		return dataCount;
	}

	@Override
	public ArrayList<String> getDataTypeLabels() {
		ArrayList<String> labels = new ArrayList<String>();
		for(int i=0; i<types; i++) {
			labels.add("Type " + (i+1));
		}
		return labels;
	}
	
	public ArrayList<String> getDataSourceLabels() {
		ArrayList<String> labels = new ArrayList<>();
		for(int i=0; i<dataCount; i++) {
			labels.add(String.valueOf(i+1));
			//labels.add("Source " + (i+1));
		}
		return labels;
	}
	

}
