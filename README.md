
**Ex1 : Project**

In this task, I develop an infrastructure of data structures, and algorithms, and a display system.

The project is to develop a data structure of a weighted and unintentional graph. By implementing the data structure and implementing several algorithms in the graph including duplication, checking bindings, calculating a short trajectory and finding the shortest trajectory.

In order to implement the data structure, 2 main classes were constructed, WGraph_Algo, WGraph_DS, and an innner class in the graph called node_info:

1.**WGraph_DS :** This class represents a undirectional and weighted graph. The interface supports many nodes. graph_Ds is represented by Two elements; HashMap of Nodes and HashMap of Neighbors node.
This class has 2 inner classes: Edge and Node_Info.

The functions that make up the department are:

-`getNode()` : This function accepts key and returns node_Info.

-`hasEdge()` : The function accepts two int (key) variables and returns true or false If there is an Edge between the two.

-`addNode()` :  The function gets a node and adds it to the hashMap which is the graph and .

-`connect()` : The function accepts 2 int (key) variables and a Connects  eage between them.

-`getEdge()` : The function accepts 2 int and returns the weight of the side between the two vertices

-`getV()` : The function receives nothing but returns a collection of vertices on the graph.

-`getV()` : The function receives node_id and returns all node_id neighbors.

-`removeNode( )` : The function receives a key, and the function deletes both the vertex and the vertex's neighbors and the vertex from its neighbors.

-`removeEdge()` : The function receives two nodes and deletes the connecting side between them and the neighbors between them.

-`ndoeSize()` : The function returns the number of vertices.

-`edgeSize()` : The function returns the number of edge.

-`getMac()` : The function returns the number of operations performed on the graph.

**The inner class of WGraph_Ds** :
a .**Node_info** : The class is an internal class of WGraph_DS that implements node_info.
This class represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.
You created a new node, returning the key of the node.

the function is:

-`getKey()` : Returns the key of the node.

-`removeNode()` : Removes the edge with the key .

-`get/setInfo` : Add/return a marker on the node so we can see if we did an action on the vertex.

-`get/setTag `: Add/return a marker on the node so we can see if we did an action on the vertex

b.**Edge** : This class is an inner class of WGraph_DS.
In this class I construct the edge between 2 vertices by source, target, weight.
-`Edge()` : This function builds an edge by obtaining source, target, and weight.

-`Edge()` : This function gets an edge and copies the edge.

-`getSrc` : This function returns the source.

-`getDest` : This function returns the dest;

-`getW` : This function retruns the Weight.

3.**Graph_Algo:** This class is doing algorithmic operations to implement a graph , in this class I used 1 hashmap for the vertices.

-`Init()` :This function receives a graph and initializes the graph.

-`getGraph` : This function receives nothing, and returns the graph.

-`copy()` : This function receives nothing, this function makes a deep copy, it copies all the vertices and also all the neighbors of the vertices.

-`isConnected()` : This function checks if the graph is a link, meaning if it is possible to walk on all the vertices. In this function, I used the Info to mark each vertex I visited and so at the end I went through again and checked that everyone with the same marking on the Info, the tour was done by bfs.

-`shortestPathDist()` : This function receives a key of the beginning vertex and the end vertex and returns the trajectory with the lowest weight number of edges.
I used this function in Dijkstra.

In this function I used Dijkstra to go on the graph, I started with the original vertex and then I went to the neighbors according to the Dijkstra principle and marked each vertex I was in by info and also put the weight of the side in the tag so we will not go back to the same vertex unless there is a way with Light weight.
In the Dijkstra function I used HashMap and Queue.
The hashMap was used to save the vertex and you are the lowest weight to reach.
And a queue to keep the neighbors I reach, and as a queue I always prioritized nodes so that the node with the lowest weight would come out first.
And so every time I got to the top I updated the weight to get to it and the weight on its tag.

-`shortesPath()` : 
This function gets a start and end node key and returns the path with the smallest weight, in this function I used the shortestPathDist () function that will return the smallest weight in the required path.

Then I created a list to which I added from hashMap parent the codecs by the key which is basically the data it is the parent and so on until it reaches null.

-`save()` :  This function gets a text file to which I save the information on the graph like: vertex, neighbors, weight.

-`load()` : This function receives text and invents the information into the graph.

**Node_Comparator** :  This class is a class that assigns a comprator that checks the equality of objects we need.
In this class I created one function which checks which of the nodes has a lower weight and thus cause the node with the lower weight to be first in line.
And so every time we walk on the graph we will always be on the low weight.

Links related: https://www.coursera.org/lecture/advanced-data-structures/core-dijkstras-algorithm-2ctyF

