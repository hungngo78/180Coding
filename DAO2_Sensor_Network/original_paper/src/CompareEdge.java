import java.util.Comparator;

public class CompareEdge implements Comparator<Edge> {

	public CompareEdge() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Edge e1, Edge e2) {
	
        if (e1.getDistance() < e2.getDistance()) 
        	return -1;
        else if (e1.getDistance() == e2.getDistance())      
        	return 0;
        else
        	return 1;
	}

}
