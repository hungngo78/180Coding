import java.util.Comparator;

public class CompareNode implements Comparator<Axis> {
	@Override
	public int compare(Axis e1, Axis e2) {
	
        if (e1.getPrize() > e2.getPrize()) 
        	return -1;
        else if (e1.getPrize() == e2.getPrize())      
        	return 0;
        else
        	return 1;
	}
}
