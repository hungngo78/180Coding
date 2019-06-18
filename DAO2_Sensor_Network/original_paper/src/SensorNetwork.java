import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SensorNetwork {
	
	private Map<Integer, Axis> nodes = new LinkedHashMap<Integer, Axis>();
	private Map<Integer, Axis> dataNodes = new LinkedHashMap<Integer, Axis>();
	
	Map<Integer, Boolean> discovered = new HashMap<Integer, Boolean>();
	Map<Integer, Boolean> explored = new HashMap<Integer, Boolean>();
	Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
	//Map<Integer, Integer> connectedNodes = new HashMap<Integer, Integer>();
	Stack<Integer> s = new Stack<Integer>();
	
	// used to find shorted paths
	ArrayList<Edge> visited = new ArrayList<Edge> ();
	
	ArrayList<RetainedPath> retainedPaths = new ArrayList<RetainedPath> ();
	
	// Algorithm 1
	Map<Integer, Set<Integer>> connectedComponents = new LinkedHashMap<Integer, Set<Integer>>();
	Map<Integer, List<Integer>> aggregationWalks = new LinkedHashMap<Integer, List<Integer>>();
	
	ArrayList<Edge> qEdges = new ArrayList<Edge>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// default values
		double width = 1000;
		double height = 1000;
		int transmissionRange = 150;
		
		int numberOfNodes = 0;
		int numberOfDataNodes = 0;
		
		int rCapNumber = 0;
		int mNumber ;
		int rNumber = 0;
		int qNumber = 0;		
		boolean invalid = false;
		
		System.out.println("Enter the width: " + width);
		//width = scan.nextDouble();

		System.out.println("Enter the height: " + height);
		//height = scan.nextDouble();
		
		System.out.println("Enter the Transmission range in meters: " + transmissionRange);
		//transmissionRange = scan.nextInt();
		
		do {
			System.out.println("Enter the number of nodes (Ex: 100):");
			numberOfNodes = scan.nextInt();
			
			System.out.println("Enter the number of data nodes (Ex: 55):");
			numberOfDataNodes = scan.nextInt();
			
			System.out.println("Enter R number (Ex: 20):");
			rCapNumber = scan.nextInt();
			
			System.out.println("Enter m number (Ex: 20):");
			mNumber = scan.nextInt();
			
			System.out.println("Enter r number (Ex: 15):");
			rNumber = scan.nextInt();
			
			// calculate number of aggregator need to be walked through
			qNumber = (numberOfDataNodes * (rCapNumber + mNumber) - (numberOfNodes * mNumber)) / (rCapNumber - rNumber);
			System.out.println("\nQ number:" + qNumber);
			
			if (qNumber > numberOfDataNodes) {
				System.out.println("\nPlease input again, your input were wrong, and it made an invalid q number exceeding number of data nodes");
				invalid = true;
			}
			else if (qNumber <= 0 ) {
				System.out.println("\nPlease input again, your input were wrong, and it made nagative q number");
				invalid = true;
			} 
			else 
				invalid = false;
			
		} while (invalid);
		
		scan.close();
		
		SensorNetwork sensor = new SensorNetwork();
		sensor.populateNodes(numberOfNodes, width, height, numberOfDataNodes);

		/*System.out.println("\nNode List:");
		for(int key :sensor.nodes.keySet()) {
			Axis ax = sensor.nodes.get(key);
			System.out.println("Node:" + key + ", xAxis:" + ax.getxAxis() + ", yAxis:" + ax.getyAxis());	
		}*/
		
		Map<Integer, Set<Integer>> adjacencyList1 = new LinkedHashMap<Integer, Set<Integer>> ();

		/* populate adjacency list */
		sensor.populateAdjacencyList(numberOfNodes, transmissionRange, adjacencyList1);
		
		/*System.out.println("\nAdjacency List: ");
		for(int i: adjacencyList1.keySet()) {
			System.out.print(i);
			if(!adjacencyList1.isEmpty()){
				for(int j: adjacencyList1.get(i)) { 
					System.out.print("->" + j);
				}
			}
			System.out.println();
		}*/
		
		/* populate data adjacency list */
		Map<Integer, Set<Integer>> dataAdjacencyList1 = new LinkedHashMap<Integer, Set<Integer>> ();
		sensor.populateDataAdjacencyList(numberOfNodes, transmissionRange, adjacencyList1, dataAdjacencyList1);
		
		sensor.algorithm1(dataAdjacencyList1, qNumber);
		
		sensor.drawResults(width, height, adjacencyList1, dataAdjacencyList1);
	}

	void populateNodes(int nodeCount, double width, double height, int dataNodeCount) {
		Random random = new Random();
		
		for(int i = 1; i <= nodeCount; i++) {
			Axis axis = new Axis();
			int scale = (int) Math.pow(10, 1);
			double xAxis =(0 + random.nextDouble() * (width - 0));
			double yAxis = 0 + random.nextDouble() * (height - 0);
			
			xAxis = (double)Math.floor(xAxis * scale) / scale;
			yAxis = (double)Math.floor(yAxis * scale) / scale;
			
			axis.setxAxis(xAxis);
			axis.setyAxis(yAxis);
			
			nodes.put(i, axis);	
		}
		
		while (dataNodeCount > 0) {
			int index = random.nextInt(nodeCount);
			Axis node = nodes.get(index + 1);
			if (!node.isDataNode()) {
				node.setNodeType(true);
				dataNodeCount--;
				
				dataNodes.put(index + 1, node);
			}   
		}
	}
	
	void populateAdjacencyList(int nodeCount, int tr, Map<Integer, Set<Integer>> adjList) {
		for(int i=1; i<= nodeCount; i++) {
			adjList.put(i, new HashSet<Integer>());
		}
		
		for(int node1: nodes.keySet()) {
			Axis axis1 = nodes.get(node1);
			for(int node2: nodes.keySet()) {
				Axis axis2 = nodes.get(node2);
				
				if(node1 == node2) {
					continue;
				}
				double xAxis1 = axis1.getxAxis();
				double yAxis1 = axis1.getyAxis();
					
				double xAxis2 = axis2.getxAxis();
				double yAxis2 = axis2.getyAxis();
				
				double distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));
				
				if(distance <= tr) {
					Set<Integer> tempList = adjList.get(node1);
					tempList.add(node2);
					adjList.put(node1, tempList);
						
					tempList = adjList.get(node2);
					tempList.add(node1);
					adjList.put(node2, tempList);
				}
			}
		}
	}
	
	void populateDataAdjacencyList(int nodeCount, int tr, Map<Integer, Set<Integer>> adjList, Map<Integer, Set<Integer>> dataAdjList) {
		for(int i=1; i<= nodeCount; i++) {
			dataAdjList.put(i, new HashSet<Integer>());
		}
		
		for(int node1: dataNodes.keySet()) {
			Set<Integer> notAdjList = new LinkedHashSet<Integer>();
			
			Axis axis1 = dataNodes.get(node1);
			for(int node2: dataNodes.keySet()) {
				Axis axis2 = dataNodes.get(node2);
				
				if(node1 == node2) {
					continue;
				}
				double xAxis1 = axis1.getxAxis();
				double yAxis1 = axis1.getyAxis();
					
				double xAxis2 = axis2.getxAxis();
				double yAxis2 = axis2.getyAxis();
				
				double distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));
				
				
				if(distance <= tr) {
					Set<Integer> tempList = dataAdjList.get(node1);
					tempList.add(node2);
					dataAdjList.put(node1, tempList);
						
					tempList = dataAdjList.get(node2);
					tempList.add(node1);
					dataAdjList.put(node2, tempList);
				} 
				else {
					notAdjList.add(node2);
				}
			}
			
			/* convert sensor network graph to aggregator graph by supplement data adjacency list*/ 		
			getShortestPaths(node1, adjList);
			
			for(int node2: notAdjList) {
				visited.clear();
				visited = new ArrayList<Edge> ();
				
				// check if the shortest paths between note1 and note2 in G do not contain any other data nodes
				Axis axis2 = nodes.get(node2);
				Set<Integer> shortestPath = axis2.getShortestPath();
				
				int dataNo = 0 ;
				int nodeNo = 0;
				for(int v: shortestPath) {
					nodeNo++;
					Axis axis = nodes.get(v);
					if (axis.isDataNode()) {
						dataNo++;
					}
				}
				if (nodeNo > 2) {
					if (dataNo == 2) {
						Set<Integer> tempList = dataAdjList.get(node1);
						if (tempList != null) {
							tempList.add(node2);
							dataAdjList.put(node1, tempList);
								
							tempList = dataAdjList.get(node2);
							tempList.add(node1);
							dataAdjList.put(node2, tempList);
							
							RetainedPath path = new RetainedPath(node1, node2);
							path.getShortestPath().addAll(shortestPath);
							retainedPaths.add(path);
						}
					}
				}
			}
			
			resetShortestDistanceInfo();	
		}
	}
	
	void algorithm1(Map<Integer, Set<Integer>> dataAdjList, int q) {
		int maxKey = 0;
		
		// create all edges between every 2 nodes, and store them in an ArrayList 
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for  (int n1: dataAdjList.keySet()) {
			Set<Integer> list = dataAdjList.get(n1);
			for (int n2: list) {
				if (!edges.contains(new Edge(n2, n1, 0))) {
					Edge e = new Edge(n1, n2, distance(n1, n2));
				
					edges.add(e);
				}
			}
		}
		
		// sort all the edges in E in non-descending order of their weights
		Collections.sort(edges, new CompareEdge());
		
		// fetch each item of ArrayList<Edge> 
		int k = 0;
		while (k < q) {
			Iterator<Edge> ite = edges.iterator();
			while (ite.hasNext()) {
				Edge edge = ite.next();
				int n1 = edge.getNode1();
				int n2 = edge.getNode2();
				
				// check if this edge has been fetched already
				if (qEdges.contains(edge))
					continue;
				
				// check if 2 nodes this edge belong to fetched connected components
				int res= checkExisting(edge, connectedComponents);
				
				// both 2 nodes of this edge already belong to fetched connected components
				if (res == 3) {
					// try with another edge
					continue;
				}					
				
				// both 2 nodes of this edge already belong to fetched connected components
				//   but they belong to different connected components
				else if (res == 2) {
					qEdges.add(edge);
					k++;
					break;
				}	
				
				// Only 1 node of this edge belong to fetched connected components
				else if (res == 1) {
					qEdges.add(edge);
					k++;
					break;
				}
				
				// Both 2 nodes of this edge dont belong to any fetched connected components
				else {
					// create new connectedComponent 
					Set<Integer> connectedComponent = new LinkedHashSet<Integer>();
					
					// add edge into connectedComponent
					connectedComponent.add(n1);
					connectedComponent.add(n2);
					maxKey++;
					connectedComponents.put(maxKey, connectedComponent);
					
					qEdges.add(edge);
					k++;
					break;
				}
			}
			
			//k++;
		}
		
		/* apply LP-Walk for each connected component to know starting node and aggregation walk */
		System.out.println("\nResult after applying algorithm: ");
		
		Map<Integer, Set<Integer>> aggregatorAdjList = new LinkedHashMap<Integer, Set<Integer>> ();
		ArrayList<Integer> leafNodes = null;
		for (int key: connectedComponents.keySet()) {
			Set<Integer> connectedComponent = connectedComponents.get(key);
			
			// leaf list of current connectedComponent
			leafNodes = new ArrayList<>(); 
			
			for (int node: connectedComponent) {
				aggregatorAdjList.put(node, new HashSet<Integer>());
				
				// check if it is leaf node
				int count = 0;
				Iterator<Edge> ite = qEdges.iterator();
				while (ite.hasNext()) {
					Edge edge = ite.next();
					if (node == edge.getNode1()) {
						count++;
						aggregatorAdjList.get(node).add(edge.getNode2());
					}
					else if (node == edge.getNode2()) {
						count++;
						aggregatorAdjList.get(node).add(edge.getNode1());
					}
				}
				
				if (count == 1) {		// it's leaf node
					// put it into leaf list of current connectedComponent
					leafNodes.add(node);
				}
			}
			
			double longestDistance = 0;
			int longestPathStartPoint  = 0;
			//int longestPathEndPoint = 0;
			
			// fetch all leaf nodes		
			for (int n1: leafNodes) {
				// find all shortest paths from n1 to all the other nodes in current connected component
				getShortestPaths(n1, aggregatorAdjList);
				
				// find the longest path, keep it in longestDistance variable
				for (int n2: leafNodes) {
					if (n2 != n1) {
						Axis axis2 = nodes.get(n2);
						if (axis2 == null) 
							continue;
						
						double distance = axis2.getShortestDistance();
						if (distance > longestDistance) {
							longestDistance = distance;
							longestPathStartPoint = n1;
							//longestPathEndPoint = n2;
						}
					}
				}
				
				resetShortestDistanceInfo();
			}				
			
			if (longestPathStartPoint != 0) {
				// from the longestPathStartPoint in longest shortest path, apply DFS to find agregation walk
				List<Integer> dFSWalk = new ArrayList<Integer>();
				execDFS(longestPathStartPoint, dFSWalk, aggregatorAdjList, connectedComponent.size());
				
				System.out.print("  Starting Point = " +longestPathStartPoint + " ----- ");
				
				// go back to original sensor network graph
				List<Integer> aggregationWalk = new ArrayList<Integer>();
				Iterator<Integer> itedFSWalk = dFSWalk.iterator();
				int n1 = 0, n2 = 0;
				while (itedFSWalk.hasNext()) {
					int n = itedFSWalk.next();
					if (n1 == n2) {
						n2 = n;
						aggregationWalk.add(n2);
					} else {
						n1 = n2;
						n2 = n;
						
						// bring n1, n2 back to original sensor network graph
						Set<Integer> p = getRetainedPath(n1, n2);
						if (p == null) {   // shortest path between n1 and n2 has other data nodes
							aggregationWalk.add(n2);
						}
						else {   // shortest path between n1 and n2 does not have any other data nodes
							for (int i: p) {
								if (i != n1) {
									aggregationWalk.add(i);		
								}
							}			
						}
					}
				}
				
				System.out.print("    Aggregation Walk:  ");
				for (int i: aggregationWalk) {
					System.out.print(i + "--");
				}
				System.out.println();
				
				this.aggregationWalks.put(key, aggregationWalk);
			}
		}
	}
	
	int execDFS(int n1, List<Integer> path , Map<Integer, Set<Integer>> dataAdjList , int visitNo ) {
		Axis axis1 = nodes.get(n1);
		if (axis1!= null && !axis1.isVisited()) {
			axis1.setVisited(true);
			visitNo--;
		}
		
		Set<Integer> n1AdjList = dataAdjList.get(n1);
		if (n1AdjList != null) {
			for (int n2: n1AdjList) {
				Axis axis2 = nodes.get(n2);
				if (axis2!= null && !axis2.isVisited()) {
					path.add(n1);
					axis2.setVisited(true);
					visitNo--;
					
					visitNo = execDFS(n2, path, dataAdjList, visitNo);
				} 
			}
		}
		
		if (visitNo >= 0) {
			path.add(n1);
			if (visitNo == 0)
				visitNo--;
		}
		
		return visitNo;
	}
	
	void execDjikstraAlgo(int node1, Map<Integer, Set<Integer>> adjList) {
		Axis axis1 = nodes.get(node1);
		if (axis1 == null)
			return;
		
		Set<Integer> shortestPathtoNode1 = axis1.getShortestPath();
					
		Set<Integer> list = adjList.get(node1);
		if (list != null) {
			for(int v: list) {			
				Axis axisV = nodes.get(v);
				
				if (axisV == null)
					continue;
				double currentDistance = axisV.getShortestDistance();
				if (currentDistance == 0)
					continue;
					
				double newCalculatedDistance = axis1.getShortestDistance() + distance(node1, v);
				if (newCalculatedDistance < currentDistance) {
					axisV.setShortestDistance(newCalculatedDistance);
					
					Set<Integer> newShortestPath = axisV.getShortestPath();
					newShortestPath.clear();
					newShortestPath.addAll(shortestPathtoNode1);
					newShortestPath.add(v);
					axisV.setShortestPath(newShortestPath);
					
					// remove all edges visited list that start with v
					if (currentDistance != Double.MAX_VALUE) {
						Iterator<Edge> it= visited.iterator();
						while(it.hasNext()){
							Edge edge = (Edge)it.next();
							if (edge.getNode1() == v) {
								it.remove();
							}
						}
					}
				}
				
				Edge edge = new Edge(node1, v, 0);
				if (!visited.contains(edge)) {
					visited.add(edge);
					
					execDjikstraAlgo(v, adjList);
				}
			}
		}
	}
	
	void drawResults(double width, double height, Map<Integer, Set<Integer>> adjList, Map<Integer, Set<Integer>> dataAdjList) {
		//Draw sensor network graph
		SensorNetworkGraph graph = new SensorNetworkGraph();
		graph.setGraphWidth(width);
		graph.setGraphHeight(height);
		graph.setNodes(nodes);
		graph.setAdjList(adjList);
		graph.setPreferredSize(new Dimension(960, 800));
		Thread graphThread = new Thread(graph);
		graphThread.start(); 
		
		
		//Draw aggregation graph
		AggregationGraph aggregationGraph = new AggregationGraph();
		aggregationGraph.setGraphWidth(width);
		aggregationGraph.setGraphHeight(height);
		aggregationGraph.setNodes(nodes);
		aggregationGraph.setAdjList(dataAdjList);
		aggregationGraph.setPreferredSize(new Dimension(960, 800));
		Thread AgraphThread = new Thread(aggregationGraph);
		AgraphThread.start(); 
		
		//Draw Minimum Spanning Tree graph
		QEdgeForest qEdgesGraph = new QEdgeForest();
		qEdgesGraph.setGraphWidth(width);
		qEdgesGraph.setGraphHeight(height);
		qEdgesGraph.setNodes(this.nodes);
		qEdgesGraph.setAdjList(dataAdjList);
		qEdgesGraph.setConnectedComponents(this.connectedComponents);
		qEdgesGraph.setQEdges(this.qEdges);
		qEdgesGraph.setPreferredSize(new Dimension(960, 800));
		Thread BgraphThread = new Thread(qEdgesGraph);
		BgraphThread.start();
		
		//Draw aggregation walk
		AggregationWalkGraph aggregationWalkGraph = new AggregationWalkGraph();
		aggregationWalkGraph.setGraphWidth(width);
		aggregationWalkGraph.setGraphHeight(height);
		aggregationWalkGraph.setNodes(this.nodes);
		//aggregationWalkGraph.setAdjList(dataAdjList);
		aggregationWalkGraph.setConnectedComponents(this.aggregationWalks);
		//aggregationWalkGraph.setQEdges(this.qEdges);
		aggregationWalkGraph.setPreferredSize(new Dimension(960, 800));
		Thread aggregationWalkThread = new Thread(aggregationWalkGraph);
		aggregationWalkThread.start();
	}
	
	Set<Integer> getRetainedPath(int n1, int n2) {
		Set<Integer> res = null;
		for (RetainedPath path: retainedPaths) {
			if (path == null)
				continue;
			
			if ((path.getNode1() == n1) && (path.getNode2() == n2)) {
				res = path.getShortestPath();
			}
		}
		return res;
	}
	
	int checkExisting(Edge edge, Map<Integer, Set<Integer>> connectedComponents) {
		int result = 0; 	// both 2 node do not belong to any connectedComponent
		
		int indexN1 = 0;
		int indexN2 = 0;

		for (int key: connectedComponents.keySet()) {
			Set<Integer> connectedComponent = connectedComponents.get(key);

			for (int n: connectedComponent) {
				if (n == edge.getNode1()) 
					indexN1 = key;
				if (n == edge.getNode2()) 
					indexN2 = key;
			}
			
			// both 2 node belong to 1 connectedComponent
			if (indexN1 != 0 && indexN2 != 0 && indexN1 == indexN2) {
				result = 3;
				break;
			}
			
			// 2 node belong to 2 different connectedComponents
			else if (indexN1 != 0 && indexN2 != 0 && indexN1 != indexN2) {
				// combine 2 connectedComponents
				Set<Integer> connectedComponent1 = connectedComponents.get(indexN1);	
				Set<Integer> connectedComponent2 = connectedComponents.get(indexN2);	
				connectedComponent1.addAll(connectedComponent2);
				connectedComponents.remove(indexN2);
				
				result = 2;
				break;
			}
		}
		
		// 1 node is belong to 1 connectedComponent, 1 the other node is not
		if ((indexN1 == 0 && indexN2 != 0) || (indexN1 != 0 && indexN2 == 0)) {
			// add node into connectedComponent
			if (indexN2 != 0)
				connectedComponents.get(indexN2).add(edge.getNode1());
			
			if (indexN1 != 0)
				connectedComponents.get(indexN1).add(edge.getNode2());
			
			result = 1;	
		}

		return result;
	}
	
	void getShortestPaths(int node1, Map<Integer, Set<Integer>> adjList) {
		Axis axis1 = nodes.get(node1);
		if (axis1 != null) {
			axis1.setShortestDistance(0);
			axis1.getShortestPath().add(node1);
			
			execDjikstraAlgo(node1, adjList);
		}
	}
		
	void resetShortestDistanceInfo() {
		for(int node: nodes.keySet()) {
			Axis axis = nodes.get(node);

			if (axis != null) {
				axis.setShortestDistance(Double.MAX_VALUE);
				axis.getShortestPath().clear();
			}
		}
	}

	double distance(int node1, int node2) {
		double distance = 0;
		Axis axis1 = nodes.get(node1);
		Axis axis2 = nodes.get(node2);
		
		if (axis1 != null && axis2 != null) {
			double xAxis1 = axis1.getxAxis();
			double yAxis1 = axis1.getyAxis();
				
			double xAxis2 = axis2.getxAxis();
			double yAxis2 = axis2.getyAxis();
			
			distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));
		}
		
		return distance;
	}
}
