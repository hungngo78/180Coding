package csc501.hungngo.project1.heap;

import csc501.hungngo.project1.data.HeapNode;

public abstract class Heap {

	protected HeapNode[] Heap;	
	protected int size;
	
	public Heap() {
		// TODO Auto-generated constructor stub
	}

	public void setHeapSize(int _size) {
		this.size = _size;
	}
	public int getHeapSize() {
		return this.size;
	}
	
	protected boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
	
	// return the position of parent for the current node
    protected int parent(int pos) 
    { 
        return pos / 2; 
    }
	
	// Function to swap two nodes of the heap 
    protected void swap(int fpos, int spos) 
    { 
        HeapNode tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
}
