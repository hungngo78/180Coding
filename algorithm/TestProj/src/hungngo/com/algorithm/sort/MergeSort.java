package hungngo.com.algorithm.sort;

import java.util.ArrayList;

public class MergeSort {
	
	public void merge(int arr[], int start, int end, int middle) {
		ArrayList<Integer> array = new ArrayList<>();
		
		int size1 = 0;
		int size2 = 0;
		size1 = middle - start + 1;
		size2 = end - middle;		
		
		int arr1[] = new int[size1];
		int arr2[] = new int[size2];
		for (int i=0; i<size1; i++) {
			arr1[i] = arr[start + i];
		}
		for (int i=0; i<size2; i++) {
			arr2[i] = arr[middle + 1 + i];
		}
		
		int index1 = 0;
		int index2 = 0;
		while (index1 < size1 || index2 < size2) {
			if (index1 < size1 && index2 < size2) {
				if (arr1[index1] > arr2[index2]) {
					array.add(arr2[index2]);
					
					index2++;
				} else if (arr1[index1] < arr2[index2]) {
					array.add(arr1[index1]);
					
					index1++;
				} else {
					array.add(arr1[index1]);
					array.add(arr2[index2]);
					
					index1++;
					index2++;
				}
			} else if  (index1 < size1 && index2 >= size2) {
				// bo sung phan con lai cua arr1 vao array
				for (int i= index1; i<size1; i++) {
					array.add(arr1[i]);
				}
				break;
			} else if  (index1 >= size1 && index2 < size2) {
				// bo sung phan con lai cua arr2 vao array
				for (int i= index2; i<size2; i++) {
					array.add(arr2[i]);
				}
				break;
			}
		}
		
		//array.forEach(System.out::println);
		
		// cap nhat lai arr doan start - end
		int i = start;
		for (int item : array) {
			arr[i] = item;
			i++;
		} 
	}
	
	public void sort(int arr[], int start, int end) {
		if (start == end)
			return;
		
		// find the middle point
		int middle = (end - start)/2;
		
		if (middle > 0) {	 // 3 item
			sort(arr, start, start + middle);
			sort(arr, start + middle + 1, end);
			
			merge(arr, start, end, start + middle);
		} else {     // 2 items
			if (arr[start] > arr[end])  {
				int tmp = arr[start];
				arr[start] = arr[end];
				arr[end] = tmp;
			}
		}		
	}
	
	public static void main(String args[]) {
		int arr[] = {12, 2, 11, 9, 13, 5, 6, 7, 8};
		System.out.println("Before: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		
		MergeSort mergeSort = new MergeSort();
		mergeSort.sort(arr, 0, arr.length-1);
		
		System.out.println();
		System.out.println("After: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
	}

}
