package hungngo.com.algorithm.sort;

public class SelectionSort {

	public SelectionSort() {
		// TODO Auto-generated constructor stub
	}
	
	public void sort(int arr[], int pivotIndex) {
		if (pivotIndex >= arr.length - 1)
			return;
		
		int minIndex = pivotIndex;
		int min = arr[minIndex];
		
		for (int i=pivotIndex; i<arr.length; i++) {
			if (arr[i] < min) {
				minIndex = i;
				min = arr[i];	
			}
		}
		
		int tmp = arr[pivotIndex]; 
		arr[pivotIndex] =  min;
		arr[minIndex] = tmp;
		
		sort(arr, ++pivotIndex);
	}

	public static void main (String args[]) {
		int arr[] = {12, 2, 11, 9, 13, 5, 6, 7, 8};
		
		System.out.println("Before: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.sort(arr, 0);
		
		System.out.println();
		System.out.println("After: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}

