package hungngo.com.algorithm.sort;

import java.util.ArrayList;

public class QuickSort {

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

	public int[] sort(int arr[]) {
		if (arr.length <= 1)
			return arr;
		
		int result[] = new int[arr.length];
		int pivot = arr[arr.length - 1];
		
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		
		for (int i=0; i<arr.length-1; i++) {
			if (arr[i] <= pivot)
				left.add(arr[i]);
			else
				right.add(arr[i]);
		}
		
		int arrLeft[] = sort(left.stream().mapToInt(Integer::intValue).toArray());
		int arrRight[] = sort(right.stream().mapToInt(Integer::intValue).toArray());
		
		int i= 0;
		while (i < arr.length) {
			for (int j=0; j<arrLeft.length; j++) {
				result[i] = arrLeft[j];
				i++;
			}
			result[i] = pivot;
			i++;
			for (int k=0; k<arrRight.length; k++) {
				result[i] = arrRight[k];
				i++;
			}
		}
		
		return result;
	}
	
	
	public static void main(String args[]) {
		int arr[] =  {12, 2, 11, 9, 13, 5, 6, 7, 8};
		
		System.out.println("Before: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		
		QuickSort quicksort = new QuickSort();
		arr = quicksort.sort(arr);
		
		System.out.println();
		System.out.println("After: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}
