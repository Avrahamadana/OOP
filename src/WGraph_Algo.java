package ex1.src;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

import javax.swing.text.StyleContext.SmallAttributeSet;

import ex1.src.WGraph_DS.Edge;
import ex1.src.WGraph_DS.Node_Info;
public class WGraph_Algo implements weighted_graph_algorithms {

	weighted_graph graph;
	HashMap<node_info, node_info> prevNodes = new HashMap<>(); 

	/**
	 * This function initializes a graph in the graph that the function received.
	 *  @param g.
	 *  Complications O(1).
	 */
	@Override
	public void init(weighted_graph g) {
		
		this.graph =g;
	}

	/** 
	 * This default constructor initializes the hash map of the graph.
	 *  Complications O(1).
	 */
	public WGraph_Algo() 
	{
		graph = new WGraph_DS();
	}

	/**
	 * This function returns a graph.
	 *  Complications O(1).
	 */
	@Override
	public weighted_graph getGraph() {

		return graph;
	}

	/**
	 * This function makes a deep copy of a graph.
	 * Complications O(n*k) , n- nodes , k-Neighbors.
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph copy = new WGraph_DS();

		for (node_info node: graph.getV()) {

			copy.addNode(node.getKey());
		}
		for (node_info node_src : graph.getV()) {
			Collection<node_info> Ni = graph.getV(node_src.getKey());
			for(node_info node_dest : Ni) {
				double w = graph.getEdge(node_src.getKey(), node_dest.getKey());
				copy.connect(node_src.getKey(), node_dest.getKey(), w);
			}

		}
		return copy;
	}

	/**
	 * Returns whether the graph is linked or not, by BFS tour.
	 * Complications O(v+c) v - Number of nodes , c-Number of arches
	 */
	@Override
	public boolean isConnected() {
		Collection<node_info> g = graph.getV();
		for (node_info node_data : g) {
			if(node_data.getInfo().equals("T")) 
			{
				node_data.setInfo("F");
			}
		}

		Iterator<node_info> i = g.iterator();
		Queue<node_info> q = new LinkedList<>();
		if(g.size() != 0) 
		{
			node_info t = i.next(); 
			t.setInfo("T");
			q.add(t);
		}

		while(!q.isEmpty()) 
		{

			node_info tmp = q.poll();
			Collection<node_info> Ni = graph.getV(tmp.getKey());
			for (node_info node_data : Ni) {
				if(node_data.getInfo()!= "T") {
					q.add(node_data);
					node_data.setInfo("T");
					graph.getNode(node_data.getKey()).setInfo("T");
				}
			}

		}


		if(g.size()==0) 
		{
			return true;
		}

		for (node_info node_data : graph.getV()) {
			if(node_data.getInfo() == "F") 
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of vertices in the short track by two keys of beginning and end, the solo search is done by Dijkstra.
	 * @param src.
	 * @param dest.
	 * Complications O(n) or o(nigh)
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		return Dijkstra(src,dest);
	}

	/**
	 * Returns the short route by two keys of beginning and end, the search for the solo is done by Dijkstra.
	 * Complications O(n*e) n-node , e-nigh
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		LinkedList<node_info> list = new LinkedList<node_info>();
		double distance = this.shortestPathDist(src, dest);
		if (distance == Double.MAX_VALUE)
			return null;
		node_info nDest = this.graph.getNode(dest);
		if (nDest == null)
			return null;
		while (nDest.getKey() != src) {
			list.add(nDest);
			nDest = this.prevNodes.get(nDest);
		}
		list.add(this.graph.getNode(src));

		return reverseList(list);
	}
	/**
	 * This function makes reverse on the list.
	 * @param list
	 * @return
	 * Complications O(r) r-route.
	 */
	private static LinkedList<node_info> reverseList(LinkedList<node_info> list) {
		LinkedList<node_info> rList = new LinkedList<node_info>();
		while (!list.isEmpty()) {
			rList.add(list.getLast());
			list.removeLast();
		}
		return rList;
	}

	/**
	 * This function calculates the short route by weight by source and destination.
	 * @param src
	 * @param dest
	 * @return
	 * Complications O(|E|log(|V|) + |V|log(|V|)
	 */
	public  double Dijkstra(int src , int dest) 
	{
		HashMap<node_info, Double> totalCosts = new HashMap<>();

		Node_Comparator comp = new Node_Comparator();
		PriorityQueue<node_info> minPQ =new PriorityQueue<node_info>(comp);
		double [] visted = new double[graph.nodeSize()];
       
		if(src == dest) 
		{
			return 0;
		}

		totalCosts.put(graph.getNode(src),0.0);
		minPQ.add(graph.getNode(src));
		graph.getNode(src).setInfo("T");
		double Infinity = Double.MAX_VALUE;

		for (node_info node : graph.getV()) {
			if(node.getKey() != src) {
				totalCosts.put(node, Infinity);
			}

			Collection<node_info> Ni = graph.getV(node.getKey());
			for (node_info node_info : Ni) {
				if(node_info.getInfo().equals("T")) 
				{
					node_info.setInfo("F");
					visted[node_info.getKey()] = Infinity;
				}
			}
		}

		node_info newSmallest = null;
		double altPath =-1;
		while(!minPQ.isEmpty()) 
		{

			newSmallest = minPQ.poll();

			Collection<node_info> Ni = graph.getV(newSmallest.getKey());
			for (node_info node_info : Ni) {
				if(node_info.getKey() == dest) 
				{
					altPath = totalCosts.get(newSmallest) + graph.getEdge(newSmallest.getKey(), node_info.getKey());
					if(altPath>totalCosts.get(node_info)) 
					{
						altPath = totalCosts.get(node_info);
					}
					else {
						altPath = totalCosts.get(newSmallest) + graph.getEdge(newSmallest.getKey(), node_info.getKey());
						node_info.setTag(altPath);
						prevNodes.put(node_info,newSmallest);
						totalCosts.put(node_info, altPath);
						visted[node_info.getKey()] = altPath;
						node_info.setInfo("T");
					}
				}

				if(altPath<totalCosts.get(node_info) && node_info.getInfo().equals("F")) 
				{
					node_info.setTag(altPath);
					totalCosts.put(node_info, altPath);
					prevNodes.put(node_info,newSmallest);
					minPQ.add(node_info);
					visted[node_info.getKey()] = node_info.getTag();
					node_info.setInfo("T");
				}
				if(visted[node_info.getKey()] > altPath ) 
				{
					visted[node_info.getKey()] = altPath;
					node_info.setTag(altPath);
					totalCosts.put(node_info, altPath);
					prevNodes.put(node_info,newSmallest);
				}

			}
		}

		return altPath;
	}
    
	
	/**
	 * This function gets a text file to which I save the information on the graph like: vertex, neighbors, weight
	 * @param file.
	 * Complications O(v)*O(Ni) v-vertex Ni- Neighbors
	 */
	@Override
	public boolean save(String file) {
		try 
		{
			PrintWriter pw = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();

			for(node_info node : graph.getV()) 
			{ 
				sb.append(node.getKey());
				int n = graph.getV(node.getKey()).size();
				for(node_info node1: graph.getV(node.getKey())) 
				{
					sb.append(",");
					sb.append(node1.getKey());
					n--;
				}
				sb.append("\n");
				pw.write(sb.toString());
				sb.setLength(0);
			}
			pw.close();
			System.out.println("done");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return false;
	}
/**
 * This function receives text and invents the information into the graph.
 * @param file.
 * Complications O(v)*O(Ni) v-vertex Ni- Neighbors.
 */
	@Override
	public boolean load(String file) {
		String line ="";
		try 
		{
			BufferedReader br =new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null)
			{
				String [] info = line.split(",");
				for(int i=0; i<info.length; i++) 
				{
					int n = Integer.parseInt(info[i]);
					this.graph.addNode(n);
					for(int j= i+1; i<info.length; i++) 
					{
						int n1 = Integer.parseInt(info[j]);
						this.graph.addNode(n1);
						this.graph.connect(n, n1, 0);
					}
				}
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	public String toString() 
	{
		return ""+ this.graph;
	}
}
