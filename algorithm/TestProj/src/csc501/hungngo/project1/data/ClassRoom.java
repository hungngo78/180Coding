package csc501.hungngo.project1.data;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassRoom implements HeapNode {
	private int id;
	private ArrayList<Interval> intervalList;
	private int latestOpenTime;
	
	public ClassRoom() {
		// TODO Auto-generated constructor stub
		intervalList = new ArrayList<>();
	}

	public void setId(int _id) {
		id = _id;
	}
	public int getId() {
		return id;
	}
	public void setLatestOpenTime(int time) {
		latestOpenTime = time;
	}
	public int getLatestOpenTime() {
		return latestOpenTime;
	}
	public void addInterval(Interval _interval) {
		this.intervalList.add(_interval);
	}
	public ArrayList<Interval> getIntervalList() {
		return this.intervalList;
	}
	
	public String toString() {
		StringBuffer intervalStr = new StringBuffer();
		Iterator<Interval> iter = intervalList.iterator();
		while (iter.hasNext()) {
			Interval in = (Interval)iter.next();
			intervalStr.append("[" + in.getId() + ", " + "[" + in.getStartTime() + ", " + in.getEndTime() + "]" + "]");
			
			if (iter.hasNext())
				intervalStr.append(", ");
		}
			
		//return intervalStr.toString();
		return "Class [id="+ id +", intervals=[" + intervalStr.toString() +"]]";	
		
	}
}
