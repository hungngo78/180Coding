package csc501.hungngo.project1.interval;

import java.util.ArrayList;

import csc501.hungngo.project1.data.ClassRoom;
import csc501.hungngo.project1.data.HeapNode;
import csc501.hungngo.project1.data.Interval;
import csc501.hungngo.project1.heap.MinHeap;
import csc501.hungngo.project1.utility.IntervalUtil;

public class IntervalPartition extends IntervalJob {
	
	private Interval[] arr;
	
	public IntervalPartition(Interval[] _arr) {
		arr = _arr;
	}

	public ArrayList<HeapNode> execute() {
		System.out.println("================== Sorted Interval (by start time) ====================");
		sortIntervalByAscendingOrd(arr, IntervalUtil.START_TIME);
		IntervalUtil.printClasses(arr);
		
		int classRoomIdx = 0;
		
		ArrayList<HeapNode> classRooms = new ArrayList<HeapNode>();
		ClassRoom newClassRoom = new ClassRoom();
		newClassRoom.addInterval(arr[0]);
		newClassRoom.setId(classRoomIdx);
		newClassRoom.setLatestOpenTime(arr[0].getEndTime());
		classRooms.add(newClassRoom);
		
		MinHeap heap = new MinHeap(arr.length);
		heap.insert(newClassRoom);
		for (int i=1; i<arr.length; i++) {
			// find min 
			ClassRoom min = (ClassRoom) heap.findMin();
			if ( arr[i].getStartTime() >= min.getLatestOpenTime()) {
				// schedule request i on resource j
				min.addInterval(arr[i]);
				
				// update latest open time of this class room		
				heap.updateEndTime(0, arr[i].getEndTime());
			} else {
				// open new class room
				classRoomIdx += 1;
				newClassRoom = new ClassRoom();
				newClassRoom.setId(classRoomIdx);
				classRooms.add(newClassRoom);
				
				// schedule request i on resource d
				newClassRoom.addInterval(arr[i]);
				
				// update latest open time of this class room
				newClassRoom.setLatestOpenTime(arr[i].getEndTime());
				
				heap.insert(newClassRoom);
			}
			
		}
		
		return classRooms;
	}
	
	
}
