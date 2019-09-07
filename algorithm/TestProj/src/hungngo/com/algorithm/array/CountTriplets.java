package hungngo.com.algorithm.array;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountTriplets {
	
	/* geometric progression
	 * You are given an array and you need to find number of triplets of indices (j,j,k)
	 *   such that the elements at those indices are in geometric progression for a given common ratio r and i < j < k.

	For example, arr= [1,4,16,64] . If r=4 , we have [1,4,16] and [4,16.64] at indices (0,1,2) and (1,2,3). 
	  --> Output = 2
	*/

	public CountTriplets() {
		// TODO Auto-generated constructor stub
	}
	
	public int count1(int arr[], int ratio) {
		Map<Integer, Integer> jMap = new LinkedHashMap<>();
		Map<Integer, Integer> kMap = new LinkedHashMap<>();
		
		for (int i=0; i< arr.length; i++) {
			// check if arr[i] is j of any triplet
			int count = jMap.getOrDefault(arr[i], -1);
			if (count != -1)
				jMap.put(arr[i], ++count);
			
			// check if arr[i] is k of any triplet
			count = kMap.getOrDefault(arr[i], -1);
			if (count != -1)
				kMap.put(arr[i], ++count);
			
			
			/* arr[i] can be i of some triplets  */
			// expecting arr[i]*ratio as j
			count = jMap.getOrDefault(arr[i]*ratio, -1);
			if (count != -1)
				jMap.put(arr[i]*ratio, ++count);		// case: 9 9 27 81 ->  (9,27,81) , (9,27,81)
			else
				jMap.put(arr[i]*ratio, 0);
			
			// expecting arr[i]*ratio*ratio as k
			if(!kMap.containsKey(arr[i]*ratio*ratio))
				kMap.put(arr[i]*ratio*ratio, 0);
		}
		
		int sum = 0;
		for (Map.Entry<Integer, Integer> entry: jMap.entrySet()) {
			sum += entry.getValue() * kMap.getOrDefault(entry.getKey() * ratio, 0);
		}
			
		
		return sum;
	}
	
	public int count(int arr[], int ratio) {
		Map<Integer, Integer> jMap = new LinkedHashMap<>();
		Map<Integer, Integer> kMap = new LinkedHashMap<>();
		
		for (int i=0; i< arr.length; i++) {
			if (kMap.containsKey(arr[i])) {
				kMap.put(arr[i], kMap.get(arr[i]) + jMap.getOrDefault(arr[i]/ratio, 0));
				jMap.put(arr[i], kMap.get(arr[i]));
			} else {
				if (jMap.containsKey(arr[i]))
					jMap.put(arr[i], jMap.get(arr[i]) + 1);
			}
	
			jMap.put(arr[i]*ratio, 0);
			kMap.put(arr[i]*ratio*ratio, 0);
		}
		
		int sum = 0;
		for (Map.Entry<Integer, Integer> entry: kMap.entrySet()) {
			sum += entry.getValue();
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int arr[] = {1, 3, 9, 9, 27, 81};
		int arr[] = {1, 4, 16, 64};
		//int arr[] = {1, 2, 2, 4};
		
		int commonRatio = 4;
		
		CountTriplets countTriplets = new CountTriplets();
		System.out.print("Number of triplets = " + countTriplets.count(arr, commonRatio));
	}

}
