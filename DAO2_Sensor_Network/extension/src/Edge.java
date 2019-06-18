
public class Edge {
	private int node1;
	private int node2;
	private double n1prize;
	private double n2prize;
	private double prize;
	
	public Edge() {
		// TODO Auto-generated constructor stub
	}
	
	public Edge(int n1, int n2, double n1prize, double n2prize, double p) {
		this.node1 = n1;
		this.node2 = n2;
		
		this.n1prize = n1prize;
		this.n2prize = n2prize;
		
		prize = p;
	}
	
	public void setPrize(double d) {
		this.prize = d;
	}
	
	public double getPrize() {
		return this.prize;
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
	
	public void setNode1Prize(double n1) {
		this.n1prize = n1;
	}
	
	public double getNode1Prize() {
		return this.n1prize;
	}
	
	public void setNode2Prize(double n2) {
		this.n2prize = n2;
	}
	
	public double getNode2Prize() {
		return this.n2prize;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flg = false;
		if(obj instanceof Edge) {
			if( ((Edge)obj).node1 == this.node1 && ((Edge)obj).node2 == this.node2 ) {
				flg = true;
			}
		}
		return flg;
	}
}
