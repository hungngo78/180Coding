package hungngo.com.algorithm.sort;

public class HeapSort {

	public HeapSort() {
		// TODO Auto-generated constructor stub
	}
	
	public void heapify(int arr[], int heapSize, int index) {
		
		
		if (arr.length <= (2*index + 2))
			return;
		
		// arr[i]: ->  2i +1;  2i + 2
		
		// check to know if left element is out of heap
		if (2*index + 1 >= heapSize) 
			return;
		
		if (arr[index] < arr[2*index + 1]) {
			int tmp = arr[index];
			arr[index] = arr[2*index + 1];
			arr[2*index + 1] = tmp; 
			
			// heapify for sub tree
			heapify(arr, heapSize, 2*index + 1);
		}
		
		// check to know if left element is out of heap
		if (2*index + 2 >= heapSize) 
			return;
		
		if (arr[index] < arr[2*index + 2]) {
			int tmp = arr[index];
			arr[index] = arr[2*index + 2];
			arr[2*index + 2] = tmp;
			
			// heapify for sub tree
			heapify(arr, heapSize, 2*index + 2);
		}
	}
	
	public void sort(int arr[]) {
		// define heap size
		int heapSize = arr.length;
		
		// build heap ( heapify all items  of array )
		for (int i= arr.length/2 -1 ; i>=0 ; i--) {
			heapify(arr, heapSize, i);
		}
		
		// one by one extract element from heap
		// vi du :  8/2 -1 = 3  ->  a[3] -> left: 3*2 + 1, right : 3*2 + 2
		//          9/2 -1 = 3
		//  -->  chia mang ra lam 2, tru 1 vì index bat dau tu 0
		for (int i=arr.length-1; i>0; i--) {			
			
			// swap the first element of heap with the last element ( according to heap size ) 
			int tmp = arr[0];
			arr[0] = arr[i];
			arr[i] = tmp;
			
			// heapify again first element
			heapify(arr, --heapSize, 0);
		}
		
	}
	
	public static void main(String args[]) {
		int arr[] = {12, 2, 11, 9, 13, 5, 6, 7, 8, 4};
		 
		System.out.println("Before: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		
		HeapSort heapSort = new HeapSort();
		heapSort.sort(arr);
		
		System.out.println();
		System.out.println("After: ");
		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}
