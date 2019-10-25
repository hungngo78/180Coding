package csc501.hungngo.project1.interval;

import java.util.ArrayList;

import csc501.hungngo.project1.data.HeapNode;
import csc501.hungngo.project1.data.Interval;
import csc501.hungngo.project1.heap.MaxHeap;
import csc501.hungngo.project1.utility.IntervalUtil;

public abstract class IntervalJob {
	public abstract ArrayList<HeapNode> execute();
	
	public void sortIntervalByAscendingOrd(Interval[] arr, int criterion) {
		if (criterion != IntervalUtil.START_TIME && 
				criterion != IntervalUtil.END_TIME)
			return;
		
		MaxHeap heap = new MaxHeap(arr, criterion);
		
		// define heap size
		int heapSize = arr.length;
		
		// build max heap
		heap.maxHeap();
		
		// put aside the current max value which is the root element of max heap
		for (int i=arr.length-1; i>0; i--) {			
			// swap the first element of heap with the last element ( according to heap size ) 
			int tmpId = arr[0].getId();
			int tmpStart = arr[0].getStartTime();
			int tmpEnd = arr[0].getEndTime();
			
			arr[0].setId(arr[i].getId());
			arr[0].setStartTime(arr[i].getStartTime());
			arr[0].setEndTime(arr[i].getEndTime());
			
			arr[i].setId(tmpId);
			arr[i].setStartTime(tmpStart);
			arr[i].setEndTime(tmpEnd);
			
			// update heap size
			heap.setHeapSize(--heapSize);
			
			// heapify again the first element
			heap.maxHeapify(0);
		}
	}
}
