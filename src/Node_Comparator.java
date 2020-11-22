package ex1.src;

import java.util.Comparator;

public class Node_Comparator implements Comparator<node_info>{
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
