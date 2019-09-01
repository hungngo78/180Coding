package hungngo.com.algorithm.string;

public class SubString1 {

	public SubString1() {
		// TODO Auto-generated constructor stub
	}
	
	public int getMinSubStringLenght(String s, String t) {
		int len = -1;
		
		StringBuffer tmpStr = new StringBuffer();
		for (int i=0; i<t.length(); i++) {
			tmpStr.append(t.charAt(i));
			
			if (checkConcatenated(s, t, tmpStr.toString())) {
				len = tmpStr.length();
				break;
			} else {
				
			}
		}
		
		return len;
	}
	
	private boolean checkConcatenated(String s, String t, String subString) {
		boolean res = false;
		
		String checkedStr = "";
		while (checkedStr.length() <= t.length()) {
			if (checkedStr.equals(t)) {
				res = true;
				break;
			} else {
				checkedStr = checkedStr.concat(subString);
			}
		}
		
		if (res) {
			checkedStr = "";
			while (checkedStr.length() <= s.length()) {
				if (checkedStr.equals(s)) {
					res = true;
					break;
				} else {
					checkedStr = checkedStr.concat(subString);
				}
			}
		}
		
		return res;
	}

	public static void main(String[] args) {
		/*
		 ababab
 		 abab	
		   -> 2
		
		 abcd
		 abcd
		   -> 4
		*/
		
		// TODO
		String s = "aaaa";
		String t = "aaa";
		
		SubString1 sub = new SubString1();
		System.out.print("lenght of min substring= " +sub.getMinSubStringLenght(s, t)); 
	}

}
