package hungngo.com.algorithm.string;

public class ReverseString {
	
	// su dung toCharArray() 
	
	public ReverseString() {
		// TODO Auto-generated constructor stub
	}
	
	public String reverse(String s) {
		char[] arr = s.toCharArray();
		for (int i=0; i<arr.length/2; i++) {
			char tmp = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = tmp;
		}
		
		return new String(arr);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "gnirts a esrever ot woH";
		ReverseString reverseString = new ReverseString();
		System.out.print("Reversed string = " + reverseString.reverse(s1));
	}

}
