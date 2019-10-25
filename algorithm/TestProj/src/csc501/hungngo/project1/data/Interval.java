package csc501.hungngo.project1.data;

public class Interval implements HeapNode {
	private int id;
	private int startTime;
	private int endTime;
	
	
	
	public Interval() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(int _id) {
		id = _id;
	}
	public int getId() {
		return id;
	}
	public void setStartTime(int s) {
		startTime = s;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setEndTime(int e) {
		endTime = e;
	}
	public int getEndTime() {
		return endTime;
	}
	
	public int getTimeToSort(int type) {
		if (type == 0) 
			return this.startTime;
		else
			return this.endTime;
	}

	public String toString() {
		return "[" + id + ", " + "[" + startTime + ", " + endTime + "]" + "]";
	}
}
