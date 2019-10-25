package csc501.hungngo.project1.utility;

import java.util.ArrayList;

import csc501.hungngo.project1.data.HeapNode;
import csc501.hungngo.project1.data.Interval;

public class IntervalUtil {
	public static int START_TIME = 0;
	public static int END_TIME = 1;
	
	public IntervalUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void printClasses(Interval arr[]) {
		for (int i=0; i<arr.length; i++) {
			System.out.println(arr[i].toString());
			//System.out.println("   ");
		}
	}
	
	public static void printClassRooms(ArrayList<HeapNode> nodes) {
		for (HeapNode cr : nodes) {
			System.out.println(cr.toString());
		}
	}

	
}
