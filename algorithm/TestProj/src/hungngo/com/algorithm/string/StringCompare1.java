package hungngo.com.algorithm.string;

public class StringCompare1 {

	public StringCompare1() {
		// TODO Auto-generated constructor stub
	}

	// s1 > s2 --> 1
	// s1 = s2 --> 0
	// s1 < s2 --> -1
	public int compare(String s1, String s2) {
		int res = 0;
		
		
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO
		String s1 = "ab40c";
		String s2 = "ab5c";
		
		StringCompare1 stringCompare1 = new StringCompare1();
		if (stringCompare1.compare(s1, s2) == 1)
			System.out.print("s1 > s2"); 
		else if (stringCompare1.compare(s1, s2) == 0)
			System.out.print("s1 = s2"); 
		else 
			System.out.print("s1 < s2"); 
			
	}

}
