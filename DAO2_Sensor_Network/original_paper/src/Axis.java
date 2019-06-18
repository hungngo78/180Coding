import java.util.LinkedHashSet;
import java.util.Set;

public class Axis {

	private double xAxis;
	private double yAxis;
	private boolean dataNodeType;
	
	// these properties help finding the shortest path between 2 nodes
	private double shortestDistance;
	private Set<Integer> shortestPath;
	
	// for DFS
	private boolean visited;
	
	// for the time after applying LPWalk 
	//private Set<Integer> retainPath; 
	
	public Axis () {
		dataNodeType = false;
		
		shortestDistance = Double.MAX_VALUE;
		shortestPath = new LinkedHashSet<Integer>();
		
		visited = false;
		
		//retainPath = null;
	}
	
	public void setVisited(boolean _visited) {
		this.visited = _visited;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	/*public void setRetainPath(Set<Integer> path) {
		this.retainPath = path;
	}
	
	public Set<Integer> getRetainPath() {
		return this.retainPath;
	}*/
	
	public void setShortestPath(Set<Integer> newShortestPath) {
		this.shortestPath = newShortestPath;
	}
	
	public Set<Integer> getShortestPath() {
		return this.shortestPath;
	}
	
	public void setShortestDistance(double dis) {
		this.shortestDistance = dis;
	}
	
	public double getShortestDistance() {
		return this.shortestDistance;
	}
	
	public double getxAxis() {
		return xAxis;
	}
	
	public void setxAxis(double xAxis) {
		this.xAxis = xAxis;
	}
	
	public double getyAxis() {
		return yAxis;
	}
	
	public void setyAxis(double yAxis) {
		this.yAxis = yAxis;
	}
	
	public boolean isDataNode() {
		return dataNodeType;
	}
	
	public void setNodeType(boolean _nodeType) {
		this.dataNodeType = _nodeType;
	}
	
}
