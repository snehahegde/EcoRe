package graph;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author shashank
 *
 */
public interface DataSource {

	public ArrayList<String> getDataTypeLabels();
	public int getDataPoints();
	public int getDataTypes();
	public ArrayList<ArrayList<Double>> getValues();
	public ArrayList<String> getDataSourceLabels();
		
}
