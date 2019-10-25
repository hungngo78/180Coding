package csc501.hungngo.project1.heap;

import csc501.hungngo.project1.data.Interval;

public class MaxHeap extends Heap {

	private int sortType;
			
	public MaxHeap(Interval[] arr, int _sortType) {
		size = arr.length;
		sortType = _sortType;
		
		Heap = arr;
	}
	
    // build the max heap using maxHeapify() function 
    public void maxHeap() 
    { 
        for (int pos = (size / 2) - 1; pos >= 0; pos--) { 
        	maxHeapify(pos); 
        } 
    } 
    
    // heapify the node at pos 
    public void maxHeapify(int pos) 
    { 
    	if (!isLeaf(pos)) { 
    		int largest = pos;
    		
    		if (2*pos + 1 < size) {
    			if (((Interval) Heap[pos]).getTimeToSort(sortType) < ((Interval) Heap[2*pos + 1]).getTimeToSort(sortType)) {
    				largest = 2*pos + 1;
    			}
    		}
    		
    		if (2*pos + 2 < size) {
    			if (((Interval) Heap[largest]).getTimeToSort(sortType) < ((Interval) Heap[2*pos + 2]).getTimeToSort(sortType)) {
    				largest = 2*pos + 2;
    			}
    		}
    		
    		if (largest != pos) {
    			swap(pos, largest); 
    			maxHeapify(largest); 
    		}
    	}
    } 
    
    // get max node from the heap 
    public Interval findMax() 
    { 
    	return (Interval) Heap[0];
    }
}
