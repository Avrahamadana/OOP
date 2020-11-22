package ex1.src;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import ex1.src.WGraph_DS.Edge;



public class WGraph_DS implements weighted_graph {
	HashMap<Integer, node_info> graph = new HashMap<>();
	HashMap<Integer, HashMap<Integer, Edge>> Ni = new HashMap<>();
	static int count = 0;


	public class Node_Comparator1 implements Comparator<node_info>
	{
		@Override
		public int compare(node_info node1,node_info node2) {
			double ans = node1.getTag() - node2.getTag();
			if(ans>0) 
			{
				return 1;
			}
			else if(ans == 0) 
			{
				return 0;
			}
			else 
			{
				return -1;
			}
	}
	}
	/**
	 * Edge Is the internal class of WGRAPH_DS , This class is used for edge preservation by: source, target, and weight.
	 * @author avrah
	 *
	 */
	public class Edge {
		private int src; // the source of Edge.
		private int dest; // the target of Edge.
		private double w; // the weight of Edge.

		/**
		 * It is the Default constructor , this Default constructor  builds the Edge by obtaining a s and d and w.
		 * @param s.
		 * @param d.
		 * @param w.
		 * Complications O(1).
		 */
		public Edge(int s, int d, double w) {
			this.src = s;
			this.dest = d;
			this.w = w;
		}

		/**
		 * This is the Copy constructor, which gets Edge and builds a Edge with the same values ג€‹ג€‹as the node we got.
		 * @param n
		 * Complications O(1).
		 */
		public Edge(Edge n) {
			this.src = n.src;
			this.dest = n.dest;
			this.w = n.w;
		}

		/**
		 * This function return the source of Edge.
		 * @return 
		 *  Complications O(1).
		 */
		public int getSrc() {
			return src;
		}

		/**
		 * This function returns the target of Edge.
		 * @return
		 *  Complications O(1).
		 */
		public int getDest() {
			return dest;
		}

		/**
		 * This function return the weight.
		 * @return
		 *  Complications O(1).
		 */
		public double getW() {
			return w;
		}

		/**
		 * This function print the Edge.
		 */
		@Override
		public String toString() {
			return "[s: " + src + ", d: " + dest + ", w: " + w + "]";
		}
	}

	private int MC; //The number of actions on a graph.
	private int edgeSize; //The number of vertices in the graph.
	private int nodeSize; //The number of nodes.

	/**
	 * This default constructor initializes the hash map of both the graph and the neighbors.
	 *  Complications O(1).
	 */
	public WGraph_DS() {
		graph = new HashMap<>();
		Ni = new HashMap<>();
	}

	/**
	 * This function returns a node by the key that the function received.
	 * @param key
	 * Complications O(1).
	 */
	@Override
	public node_info getNode(int key) {
		node_info node = graph.get(key);
		if (node == null) {
			return null;
		}
		return node;
	}

	/**
	 * This function checks if node1 and node2 are neighbors
	 * @param node1
	 * @param node2
	 * Complications O(1).
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {

		if (Ni.get(node1) == null || Ni.get(node1).get(node2) == null) {
			return false;
		}
		return true;
	}

	/**
	 * This function returns the Edge weight between node1 and node2.
	 * @param node1.
	 * @param node2.
	 * Complications O(1).
	 */
	@Override
	public double getEdge(int node1, int node2) {
		if(node1==node2) 
		{
			return 0;
		}
		if (!hasEdge(node1, node2)) {
			return -1;
		}
		return Ni.get(node1).get(node2).getW();
	}

	/**This function adds a node graph by getting a key
	 * @param key.
	 * Complications O(1).
	 */
	@Override
	public void addNode(int key) {
		if(!graph.containsKey(key)) 
		{
			node_info temp = new Node_Info(key);
			graph.put(key, temp);
			Ni.put(key, new HashMap<>());
			nodeSize++;
			MC++;
		}
	}

	/**
	 * This function connects two vertices by node1 and node2 and the weight of their Edge.
	 * @param node1.
	 * @param node2.
	 * @param w.
	 * Complications O(1).
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		Edge e = new Edge(node1, node2, w);
		Edge e1 = new Edge(node2,node1,w);
		if(hasEdge(node1, node2) == true && node1!=node2) 
		{
			if(Ni.get(node1).get(node2).getW() > e.getW()) 
			{
				Ni.get(node1).put(node2, e);
				Ni.get(node2).put(node1, e1);
				MC++;
			}
		}
		
		if (hasEdge(node1, node2) == false && node1!=node2) {
			Ni.get(node1).put(node2, e);
			Ni.get(node2).put(node1, e1);
			this.edgeSize++;
		}
		
		this.MC++;
	}


	/**
	 * This function returns the collection of nodes in the graph.
	 * Complications O(1).
	 */
	@Override
	public Collection<node_info> getV() {
		return graph.values();
	}
	/**
	 * This function returns the collection of the vertices of the vertex are the key we received.
	 * @param node_id.
	 * Complications O(k) , k -Signifies the number of neighbors of the same vertex.
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		List<node_info> list = new LinkedList<node_info>(); 
		if (Ni.get(node_id) != null) {
			Collection<Edge> ed = Ni.get(node_id).values();
			for (Edge edge : ed) {
				list.add(graph.get(edge.getDest()));
			}
		}
		return list;
	}

	/**
	 * This function deletes the vertex from the graph and also the vertex from its neighbors.
	 * @param key.
	 * Complications O(k) , k -Signifies the number of neighbors of the same vertex.
	 */
	@Override
	public node_info removeNode(int key) {
		node_info node = getNode(key);

		if (node == null) {
			return null;
		}

		Collection<node_info> nodeNi = getV(key);
		for (node_info node_info : nodeNi) {
			removeEdge(key,node_info.getKey());
		}
		if (Ni.get(key) != null) {
			Ni.remove(key);
		}

		graph.remove(key);

		MC++;
		nodeSize--;

		return node;
	}

	/**
	 * This function deletes a Edge between node1 and node2.
	 * @param node1.
	 * @param node2.
	 * Complications O(1).
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if (!hasEdge(node1, node2)) {
			return;
		}
		Ni.get(node1).remove(node2);
		Ni.get(node2).remove(node1);
		this.edgeSize--;
		MC++;
	}

	/*
	 * This function return the number of nodes in the graph.
	 * Complications O(1).
	 */
	@Override
	public int nodeSize() {

		return this.nodeSize;
	}

	/**
	 * This function return the number of Edge in ther graph.
	 * Complications O(1).
	 */
	@Override
	public int edgeSize() {
		return this.edgeSize;
	}

	/**
	 * This function returns the number of operations performed on the graph.
	 * Complications O(1).
	 */
	@Override
	public int getMC() {
		return this.MC;
	}

	public String toString() {
		return "" + this.graph;
	}

	/**
	 * This function checks are the graphs are equal.
	 * @param obj.
	 * Complications O(1).
	 */
	@Override
	public boolean equals(Object obj) {/// ׳�׳©׳ ׳•׳× !
		if( obj instanceof weighted_graph){
			weighted_graph g = (weighted_graph)obj;
			if( g.getV().size() != this.graph.size()){return false;}
			else if ( g.edgeSize() != this.edgeSize()){return false;}
			else return Objects.equals(g.toString(),this.toString());
		}
		return true;
	}

	/*
	 * NODE_Info Is the internal class of WGRAPH_DS which interfaces with node_info and Comparator.
	 * The internal class Node_info is a class that builds a node which consists of: a tag and information about nodes.
	 */
	public  class Node_Info implements node_info , Comparator<node_info> {
		private int key; // the key of node
		private double tag; //the tag of node
		private String info = "F"; //the data of node
        public int count = 0;
		public  final Node_Comparator _Comp = new Node_Comparator(); //Compare by weight.

		public Node_Info() 
		{
			this.key = count++;
		}
		/**
		 * It is the Default constructor who builds the nomad by obtaining a key and Initializes the tag at 0.
		 * @param key
		 * Complications O(1)
		 */
		public Node_Info(int key)
		{
			this.key = key;
			this.tag = 0;
		}

		/**
		 * This is the Copy Condtractur, which gets node_info and builds a node with the same values ג€‹ג€‹as the node we got.
		 * @param n.
		 * Complications O(1).
		 */
		public Node_Info(Node_Info n) 
		{
			this.key = n.getKey();
			this.tag = n.getTag();
			this.info = n.getInfo();
		}

		/**
		 * This function returns the key of node.
		 * Complications O(1).
		 */
		@Override
		public int getKey() {
			return this.key;
		}

		/**
		 * This function returns the info of node.
		 * Complications O(1)
		 */
		@Override
		public String getInfo() {
			return this.info;
		}

		/**
		 * In this function the information of the node is updated.
		 * Complications O(1).
		 * @param s.
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;
		}

		/**
		 * This function returns the Tag of node.
		 * Complications O(1).
		 */
		@Override
		public double getTag() {
			return this.tag;
		}

		/**
		 * This function receives a double number and fluently updates the tag.
		 * @param t.
		 * Complications O(1).
		 */
		@Override
		public void setTag(double t) {
			this.tag = t;
		}

		/**
		 * this function is to print node
		 */
		public String toString() {
			return "" + this.key;
		}

		/**
		 * In this function I get 2 nodes and check which of the nodes has you lighter weight.
		 *@param node1.
		 *@param node2.
		 *Complications O(1).
		 */
		@Override
		public int compare(node_info node1, node_info node2) {
			double ans = node1.getTag() - node2.getTag();
			if(ans>0) 
			{
				return 1;
			}
			else if(ans == 0) 
			{
				return 0;
			}
			else 
			{
				return -1;
			}
		}

		/**
		 * this function check if two nodes have same key/
		 * @param obj.
		 * Complications O(1).
		 */
		@Override
		public boolean equals(Object obj) {//check if the Object is equals
			if(!(obj instanceof node_info)) 
			{
				return false;
			}
			node_info other = (node_info)obj;
			return this.getKey() == other.getKey();
		}
	}  
	
	
}
