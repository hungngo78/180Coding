package hungngo.com.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountOccurence {
	/* 
	 * input: 10 20 30 10
		output: 
		10 2
		20 1
		30 1
	 */
	public CountOccurence() {
		// TODO Auto-generated constructor stub
	}
	
	public void count(int[] arr) {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		Arrays.parallelSort(arr);
		for (int i=0; i<arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); 
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("Entry " + entry.getKey() + ": " + entry.getValue());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {10, 20, 30, 10};
		
		CountOccurence countOccurence = new CountOccurence();
		countOccurence.count(arr);
	}
}
