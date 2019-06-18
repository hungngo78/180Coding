import java.util.LinkedHashSet;
import java.util.Set;

public class RetainedPath {
	private int node1;
	private int node2;
	
	private Set<Integer> shortestPath;
	
	
	public RetainedPath() {
		shortestPath = null;
	}
	
	public RetainedPath(int n1, int n2) {
		this.node1 = n1;
		this.node2 = n2;
		
		shortestPath = new LinkedHashSet<Integer>();
	}

	public void setShortestPath(Set<Integer> newShortestPath) {
		this.shortestPath = newShortestPath;
	}
	
	public Set<Integer> getShortestPath() {
		return this.shortestPath;
	}
	
	public void setNode1(int n1) {
		this.node1 = n1;
	}
	
	public int getNode1() {
		return this.node1;
	}
	
	public void setNode2(int n2) {
		this.node2 = n2;
	}
	
	public int getNode2() {
		return this.node2;
	}
}
