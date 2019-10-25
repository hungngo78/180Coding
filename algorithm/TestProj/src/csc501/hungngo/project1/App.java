package csc501.hungngo.project1;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import csc501.hungngo.project1.data.ClassRoom;
import csc501.hungngo.project1.data.HeapNode;
import csc501.hungngo.project1.data.Interval;
import csc501.hungngo.project1.interval.IntervalPartition;
import csc501.hungngo.project1.interval.IntervalSchedule;
import csc501.hungngo.project1.utility.IntervalUtil;

public class App {

	public App() {
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter the number of intervals: ");
		int numberInterval = scan.nextInt();
		System.out.print("Please enter start time of intervals: ");
		int startTimes = scan.nextInt();
		System.out.print("Please enter end time of intervals: ");
		int endTimes = scan.nextInt();
		
		Interval arr[] = new Interval[numberInterval];
		int intervalNo = 0;
		for (int i=0; i<numberInterval; i++) {
			int startTime = random.nextInt(endTimes - startTimes) + startTimes;
			
			Interval in = new Interval();
			in.setId(intervalNo);
			in.setStartTime(startTime);
			
			int endTime = 0;
			do {
				endTime = random.nextInt(endTimes - startTimes) + startTimes + 1;
			} while (endTime <= startTime);  
			in.setEndTime(endTime);
			
			arr[intervalNo] = in;
			intervalNo += 1;
		}
		
		System.out.println();
		System.out.println("================== Initial Interval ========================================");
		IntervalUtil.printClasses(arr);
		
		System.out.println();
		System.out.println("================== Interval Scheduling ========================================");
		IntervalSchedule intervalSchedule = new IntervalSchedule(arr, numberInterval);
		ArrayList<HeapNode> classRooms = intervalSchedule.execute();
		IntervalUtil.printClassRooms(classRooms);
		
		System.out.println();
		System.out.println("================== Interval Partitioning ========================================");
		IntervalPartition intervalPartition = new IntervalPartition(arr);
		classRooms = intervalPartition.execute();
		IntervalUtil.printClassRooms(classRooms);	
	}
}
