package hungngo.com.algorithm.string;

public class StringCompare1 {

	public StringCompare1() {
		// TODO Auto-generated constructor stub
	}

	private int compare2Letter(char c1, char c2) {
		if (c1 > c2) 
			return 1;
		else if (c1 < c2)
			return -1;
		else
			return 0;
	}
	
	// s1 > s2 --> 1
	// s1 = s2 --> 0
	// s1 < s2 --> -1
	public int compare(String s1, String s2) {
		int res = 0;
		
		int index1 = 0;
		int index2 = 0;
		while (index1 < s1.length() && index2 < s2.length()) {
			char c1 = s1.charAt(index1);
			char c2 = s2.charAt(index2);
			
			if (Character.isLetter(c1) && Character.isLetter(c2)) {
				res = compare2Letter(c1, c2);
				
				index1++;
				index2++;
			} else if (Character.isLetter(c1) && Character.isDigit(c2)) {
				res = 1;
				
				index1++;
				
				// increase index of s2
				index2++;
				while ( index2<s2.length() && Character.isDigit(s2.charAt(index2)) ){
					index2++;
				}
			} else if (Character.isDigit(c1) && Character.isLetter(c2)) {
				res = -1;
				
				index2++;
				
				// increase index of s1
				index1++;
				while ( index1<s1.length() && Character.isDigit(s1.charAt(index1)) ){
					index1++;
				}
			} else if (Character.isDigit(c1) && Character.isDigit(c2)) {
				// extract digit from s1
				StringBuffer digitStr1 = new StringBuffer();
				digitStr1.append(c1);
				
				index1++;
				while ( index1<s1.length() && Character.isDigit(s1.charAt(index1)) ){
					digitStr1.append(s1.charAt(index1));
					index1++;
				}
				int digit1 = Integer.parseInt(digitStr1.toString());
				
				// extract digit from s2
				StringBuffer digitStr2 = new StringBuffer();
				digitStr2.append(c2);
				
				index2++;
				while ( index2<s2.length() && Character.isDigit(s2.charAt(index2)) ){
					digitStr2.append(s2.charAt(index2));
					index2++;
				}
				int digit2 = Integer.parseInt(digitStr2.toString());
				
				if (digit1 > digit2) 
					res = 1;
				else if  (digit1 < digit2)
					res = -1;
			}
			
			if (res != 0)
				break;			
		}
		
		if (res == 0) {
			if (index1 < s1.length()) 		// s1 longer than s2
				res = 1;
			else if (index2 < s2.length())   // s2 longer than s1
				res = -1;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		/*
		 * s1 = "ab40c";
		 * s2 = "ab5cdef";
		 * --> s1 > s2
		 * 
		 * s1 = "ab40";
		 * s2 = "ab";
		 * --> s1 > s2
		 * 
		 * s1 = "abc";
		 * s2 = "ab4";
		 * --> s1 > s2
		 * 
		 * s1 = "ab4";
		 * s2 = "ab4";
		 * --> s1 = s2
		 * 
		 * s1 = "b1c";
		 * s2 = "b1";
		 * --> s1 > s2 
		 * 
		 * s1 = "bc";
		 * s2 = "b1";
		 * --> s1 > s2  
		 */
		
		
		// TODO
		String s1 = "bc";
		String s2 = "b1";
		
		StringCompare1 stringCompare1 = new StringCompare1();
		if (stringCompare1.compare(s1, s2) == 1)
			System.out.print("s1 > s2"); 
		else if (stringCompare1.compare(s1, s2) == 0)
			System.out.print("s1 = s2"); 
		else 
			System.out.print("s1 < s2"); 
			
	}

}
