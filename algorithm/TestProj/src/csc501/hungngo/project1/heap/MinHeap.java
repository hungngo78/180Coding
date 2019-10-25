package csc501.hungngo.project1.heap;

import csc501.hungngo.project1.data.ClassRoom;
import csc501.hungngo.project1.data.HeapNode;

public class MinHeap extends Heap {

	//private ClassRoom[] Heap;	
	private int maxSize;
	
	public MinHeap(int _maxsize) {
		this.maxSize = _maxsize; 
        this.size = 0; 
        
        Heap = new ClassRoom[this.maxSize + 1];         
	}
	
    // build the min heap using minHeapify() function 
    public void minHeap() 
    { 
        for (int pos = (size / 2) - 1; pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 
    
    // heapify the node at pos 
    public void minHeapify(int pos) 
    {     	
    	if (!isLeaf(pos)) { 
    		int largest = pos;
    		if (2*pos + 1 < size) {
    			if (((ClassRoom) Heap[pos]).getLatestOpenTime() > ((ClassRoom) Heap[2*pos + 1]).getLatestOpenTime()) {
    				largest = 2*pos + 1;
    			}
    		}
    		
    		if (2*pos + 2 < size) {
    			if (((ClassRoom) Heap[largest]).getLatestOpenTime() > ((ClassRoom) Heap[2*pos + 2]).getLatestOpenTime()) {
    				largest = 2*pos + 2;
    			}
    		}
    		
    		if (largest != pos) {
    			swap(pos, largest); 
    			minHeapify(largest); 
    		}    			       
    	}
    } 
    
	// insert a node into the heap 
    public void insert(ClassRoom element) 
    { 
        if (size >= maxSize) { 
            return; 
        } 
        
        size += 1;
        
        int current = size-1; 
        Heap[current] = element; 
  
        while(current>0 && (((ClassRoom) Heap[current]).getLatestOpenTime() < ((ClassRoom) Heap[parent(current)]).getLatestOpenTime())) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
    
    // Extract min node from the heap 
    public HeapNode findMin() 
    { 
    	return Heap[0];
    }
    
    // update end time of a class room
    public void updateEndTime(int pos, int _endTime) 
    { 
    	if (pos >=0 && pos < size) {
    		if (_endTime > ((ClassRoom) Heap[pos]).getLatestOpenTime()) {
		    	((ClassRoom) Heap[pos]).setLatestOpenTime(_endTime);
		    	minHeapify(pos);
    		}
    	}
    }
}
