package ex1.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import ex1.src.WGraph_DS.Node_Info;

class WGraph_DSTest {
	static weighted_graph graph;


	@Test
	void testGetNode() 
	{
		int n = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;
		graph = new WGraph_DS();
		graph.addNode(n);
		graph.addNode(m);
		graph.addNode(t);
		assertEquals(graph.getNode(n).getKey(),n);
		assertEquals(m, graph.getNode(m).getKey());
		assertEquals(t, graph.getNode(t).getKey());
	}

	@Test
	void testGetEdge() 
	{
		int n = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.connect(0, 1, n);
		graph.connect(1, 2, m);
		graph.connect(2, 3, t);

		assertEquals(graph.getEdge(0, 1), n);
		assertEquals(graph.getEdge(1, 2), m);
		assertEquals(graph.getEdge(2, 3), t);
	}
	@Test
	void testAddNode() 
	{
		int n = 0;
		int n1 = 1;
		int n2 =2;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);

		assertEquals(graph.getNode(n).getKey(), n);
		assertEquals(graph.getNode(n1).getKey(), n1);
		assertEquals(graph.getNode(n2).getKey(), n2);
	}

	@Test
	void testConnect() 
	{
		int p = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;

		int n =0;
		int n1 =1;
		int n2 =2;
		int n3 =3;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.connect(n, n1, p);
		graph.connect(n1, n2, m);
		graph.connect(n2, n3, t);

		assertEquals(graph.getEdge(n, n1),p );
		assertEquals(graph.getEdge(n1, n2),m );
		assertEquals(graph.getEdge(n2, n3),t );
	}

	@Test
	void testGetV() 
	{
		Collection<node_info> nodes = graph.getV();
		int nodeSize = graph.nodeSize();
		assertEquals(nodeSize, nodes.size());
	}

	@Test
	void testGetVNi() 
	{
		int p = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;

		int n =0;
		int n1 =1;
		int n2 =2;
		int n3 =3;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);

		graph.connect(n, n1, p);
		graph.connect(n1, n2, m);
		graph.connect(n2, n3, t);


		int NiSize = 0;


		Collection<node_info> Ni = graph.getV(0);
		for (node_info node1: Ni) {
			if(node1 != null) 
			{
				NiSize++;
			}
		}
		assertEquals(graph.getV(0).size(), NiSize);
	}

	@Test
	void testRemoveNode() 
	{
		int p = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;

		int n =0;
		int n1 =1;
		int n2 =2;
		int n3 =3;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		int before = graph.nodeSize();

		graph.connect(n, n1, p);
		graph.connect(n1, n2, m);
		graph.connect(n2, n3, t);

		graph.removeNode(n);
		assertFalse(graph.getV().contains(graph.getNode(n)));
		assertEquals(graph.nodeSize(), before-1);
	}

	@Test
	void testRemoveEdge() 
	{
		int p = (int)(Math.random()*10)+1;
		int m =(int)(Math.random()*10)+1;
		int t=(int)(Math.random()*10)+1;

		int n =0;
		int n1 =1;
		int n2 =2;
		int n3 =3;

		graph = new WGraph_DS();
		graph.addNode(0);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);


		graph.connect(n, n1, p);
		graph.connect(n1, n2, m);
		graph.connect(n2, n3, t);

		int edges_befor = graph.edgeSize();

		graph.removeEdge(n, n1);
		int edges_after = graph.edgeSize();

		assertEquals(edges_befor-1, edges_after);
	}

	@Test
	void GraphMil() 
	{
		int million = 1000000;
		int count =0;
		graph = new WGraph_DS();
		for(int i=0; i<=million; i++) 
		{
			graph.addNode(i);
		}

		for(int i=0; i<=million; i++) 
		{
			for(int j=0; j<=10; j++) 
			{
				graph.connect(i, j, count++);
			}
		}
		int beforeSize = graph.nodeSize();
		double EdgeW = graph.getEdge(0, 1);
		graph.removeNode(0);
		int afterSize = graph.nodeSize();
		assertEquals(beforeSize-1, afterSize);
		assertNotEquals(EdgeW, graph.getEdge(0, 1));
	}
}