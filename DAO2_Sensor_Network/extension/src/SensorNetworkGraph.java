import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SensorNetworkGraph extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Axis> nodes;
	private double graphWidth;
    private double graphHeight;
    private int scaling = 25;
    private int ovalSize = 10;
    private int gridCount = 10;
    private boolean connected;
    private Map<Integer, Set<Integer>> adjList;

    public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public Map<Integer, Set<Integer>> getAdjList() {
		return adjList;
	}

	public void setAdjList(Map<Integer, Set<Integer>> adjList) {
		this.adjList = adjList;
	}

	public void setNodes(Map<Integer, Axis> nodes) {
        this.nodes = nodes;
        invalidate();
        this.repaint();
    }

    public Map<Integer, Axis> getNodes() {
        return nodes;
    }
    
    public double getGraphWidth() {
		return graphWidth;
	}

	public void setGraphWidth(double graphWidth) {
		this.graphWidth = graphWidth;
	}

	public double getGraphHeight() {
		return graphHeight;
	}

	public void setGraphHeight(double graphHeight) {
		this.graphHeight = graphHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       double xScale =  ( (getWidth() - 3 * scaling) / (graphWidth));
       double yScale =   (( getHeight() - 3 * scaling) / (graphHeight));
       
       List<Point> graphPoints = new ArrayList<Point>();			
       for (Integer key: nodes.keySet()) {
           double x1 = ( nodes.get(key).getxAxis() * (xScale) + (2*scaling));
           double y1 =  ((graphHeight - nodes.get(key).getyAxis())  * yScale + scaling );
           Point point = new Point();
           point.setLocation(x1, y1);
           graphPoints.add(point);
       }
              
       g2.setColor(Color.white);
       g2.fillRect(2*scaling, scaling, getWidth() - (3 * scaling), getHeight() - 3 * scaling);
       g2.setColor(Color.black);

       for (int i = 0; i < gridCount + 1; i++) {
           int x0 = 2*scaling;
           int x1 = ovalSize + (2*scaling);
           int y0 = getHeight() - ((i * (getHeight() - (3*scaling))) / gridCount + (2*scaling));
           int y1 = y0;
           if (nodes.size() > 0) {
               g2.setColor(Color.black);
               g2.drawLine((2*scaling) + 1 + ovalSize, y0, getWidth() - scaling, y1);
               String yLabel = ((int) ((getGraphHeight() * ((i * 1.0) / gridCount)) * 100)) / 100.0 + "";
               FontMetrics metrics = g2.getFontMetrics();
               int labelWidth = metrics.stringWidth(yLabel);
               g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
           }
           g2.drawLine(x0, y0, x1, y1);
       }

       for (int i = 0; i < gridCount + 1; i++) {
    	   int x0 = i * (getWidth() - (scaling * 3)) / gridCount+ (2*scaling);
           int x1 = x0;
           int y0 = getHeight() - (2*scaling);
           int y1 = y0 - ovalSize;

           ///if ((i % ((int) ((nodes.size() / 20.0)) + 1)) == 0) {
           if ( (i == (gridCount/2)) || (i == gridCount) ) {
               if (nodes.size() > 0) {
                   g2.setColor(Color.black);
                   g2.drawLine(x0, getHeight() - (2*scaling) - 1 - ovalSize, x1, scaling);
                   String xLabel = ((int) ((getGraphWidth() * ((i * 1.0) / gridCount)) * 100)) / 100.0 + "";//i + "";
                   FontMetrics metrics = g2.getFontMetrics();
                   int labelWidth = metrics.stringWidth(xLabel);
                   g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                   
               }
               g2.drawLine(x0, y0, x1, y1);
           }
       }

       Stroke stroke = g2.getStroke();
       g2.setColor(Color.gray);
       g2.setStroke(new BasicStroke(2f));
       for (int node: adjList.keySet()) {			
    	   if((adjList.get(node) != null) && (!adjList.get(node).isEmpty())) {
	    	   for (int adj: adjList.get(node)) {
		    	   if(adjList.get(node).contains(adj)) {
			           int x1 = graphPoints.get(node-1).x;
			           int y1 = graphPoints.get(node-1).y;
			           int x2 = graphPoints.get(adj-1).x;
			           int y2 = graphPoints.get(adj-1).y;
			           g2.drawLine(x1, y1, x2, y2);
		    	   }
	    	   }
    	   }
       }

       //Draw the oval
       g2.setStroke(stroke);
       g2.setColor(Color.red);
       double ovalW = ovalSize;
       double ovalH = ovalSize;
       for (Integer key: nodes.keySet()) {
    	   Axis node = nodes.get(key);
    	   double x1 = ( node.getxAxis() * (xScale) + (2*scaling));
           double y1 =  ((graphHeight - node.getyAxis())  * yScale + scaling );
           
    	   double x = x1 - ovalSize / 2;
    	   double y = y1 - ovalSize / 2;
    	   Ellipse2D.Double shape = new Ellipse2D.Double(x, y, ovalW, ovalH);
    	   if (!node.isDataNode())
    		   g2.draw(shape);
    	   else
    		   g2.fill(shape);
       }

       //Label the nodes
       g2.setColor(Color.red);
       for (int i = 0; i < graphPoints.size(); i++) {
           int x = graphPoints.get(i).x - ovalSize / 2;
           int y = graphPoints.get(i).y - ovalSize / 2;
           g2.drawString(""+(i+1), x, y);
       }
   }

	public void run() {
		String graphName= "Sensor Network Graph";
		JFrame frame = new JFrame(graphName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
   }
}