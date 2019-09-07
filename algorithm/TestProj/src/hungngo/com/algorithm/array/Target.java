package hungngo.com.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/*
 * {13, 11, 8, 5, 9, 12, 5, 6, 2};
 * target = 21 
 * -> (9, 12) , (13, 8)           //  9 + 12 = 21;     13 + 8 = 21
 *  
 *  */

public class Target {

	public Target() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer, Integer> findChildrenPairs(int[] arr, int target) {
		Map<Integer, Integer> res = new HashMap<>();
		
		Map<Integer, Integer> tmpMap = new HashMap<>();
		for (int i= 0; i<arr.length; i++) {
			if (tmpMap.containsValue(arr[i])) {
				// should check   distinct pair (3,9)  (9,3)
				if (!res.containsKey(arr[i]) && !res.containsKey(target - arr[i]))		
					res.put(target - arr[i], arr[i]);  
				
				tmpMap.remove(target - arr[i]);
			} else {
				tmpMap.put(arr[i], target - arr[i]);
			}
		}
		
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {13, 9, 11, 12, 8, 5, 9, 12, 5, 12, 9, 6, 2};
		int targetNo = 21;
		
		Target t = new Target();
		Map<Integer, Integer> map = t.findChildrenPairs(arr, targetNo);
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println("Entry Key= " + entry.getKey() + "; Entry Value= "+ entry.getValue());
		}
		
		// Time Complexity = O(n)
	}

}
