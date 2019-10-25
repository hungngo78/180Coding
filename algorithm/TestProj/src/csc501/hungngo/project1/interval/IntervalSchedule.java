package csc501.hungngo.project1.interval;

import java.util.ArrayList;

import csc501.hungngo.project1.data.ClassRoom;
import csc501.hungngo.project1.data.HeapNode;
import csc501.hungngo.project1.data.Interval;
import csc501.hungngo.project1.utility.IntervalUtil;

public class IntervalSchedule extends IntervalJob {
	private Interval[] arr;
	private int length;
	
	public IntervalSchedule(Interval[] _arr, int _length) {
		arr = _arr;
		length = _length;
	}
	
	public ArrayList<HeapNode> execute() {
		ArrayList<HeapNode> classRooms = new ArrayList<HeapNode>();
		
		ClassRoom classRoom = schedule();
		classRooms.add(classRoom);
		
		return classRooms;
	}
	
	private ClassRoom schedule() {
		System.out.println("================== Sorted Interval (by end time) ====================");
		sortIntervalByAscendingOrd(arr, IntervalUtil.END_TIME);
		IntervalUtil.printClasses(arr);
		
		ClassRoom classRoom = new ClassRoom();
		classRoom.setLatestOpenTime(0);
		
		for (int i=0; i<length; i++) {
			// check compatible with class room
			if (classRoom.getLatestOpenTime() <= arr[i].getStartTime()) {
				classRoom.addInterval(arr[i]);
				classRoom.setLatestOpenTime(arr[i].getEndTime());
			}
		}
		
		return classRoom;
	}

}
