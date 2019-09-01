package hungngo.com.algorithm.string;

public class SubString1 {

	public SubString1() {
		// TODO Auto-generated constructor stub
	}
	
	// reduce time complexity to O(n)
	private boolean checkConcatenated2(String s, String t, String subString) {
		boolean res = false;
		
		if (t.length() % subString.length() == 0) {
			int len1 = t.length() / subString.length();
			String str1 = new String(new char[len1]).replace("\0", subString);
			if (str1.equals(t)) 
				res = true;
		}
		
		if (res) {
			if (s.length() % subString.length() == 0) {
				int len2 = s.length() / subString.length();
				String str2 = new String(new char[len2]).replace("\0", subString);
				if (str2.equals(s)) 
					res = true;
			}
		}
		
		return res;
	}
	
	// time complexity to O(n^2) -> should be improved
	private boolean checkConcatenated1(String s, String t, String subString) {
		boolean res = false;
		
		//checkedStr = subString;
		String checkedStr = "";
		while (checkedStr.length() <= t.length()) {
			if (checkedStr.equals(t)) {
				res = true;
				break;
			} else {
				//checkedStr = checkedStr.concat(checkedStr);  ->  a+a = aa,  aa+aa=aaaa  -> wrong vi miss "aaa"
				checkedStr = checkedStr.concat(subString);
			}
		}
		
		if (res) {
			//checkedStr = subString;
			checkedStr = "";
			while (checkedStr.length() <= s.length()) {
				if (checkedStr.equals(s)) {
					res = true;
					break;
				} else {
					//checkedStr = checkedStr.concat(checkedStr);  ->  a+a = aa,  aa+aa=aaaa  -> wrong vi miss "aaa"
					checkedStr = checkedStr.concat(subString);
				}
			}
		}
		
		return res;
	}
	
	public int getMinSubStringLenght(String s, String t) {
		int len = -1;
		
		StringBuffer tmpStr = new StringBuffer();
		for (int i=0; i<t.length(); i++) {
			tmpStr.append(t.charAt(i));
			
			if (checkConcatenated2(s, t, tmpStr.toString())) {
				len = tmpStr.length();
				break;
			} else {
				
			}
		}
		
		return len;
	}

	public static void main(String[] args) {
		/*
		 ababab
 		 abab	
		   -> 2
		
		 abcd
		 abcd
		   -> 4
		   
		 "abcabc"
		 "abc"
		   -> 3
		*/
		
		// TODO
		String s = "abcabc";
		String t = "abc";
		
		SubString1 sub = new SubString1();
		System.out.print("lenght of min substring= " +sub.getMinSubStringLenght(s, t)); 
	}

}
