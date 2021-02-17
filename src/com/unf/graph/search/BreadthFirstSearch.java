
package com.unf.graph.search;

import com.unf.graph.Graph;
import com.unf.graph.Vertex;

import java.util.LinkedList;

public class BreadthFirstSearch extends Graph {
	public BreadthFirstSearch(int v) {
		super(v);
	}

	void BFS(int start){
		for(Vertex v: vertex){
			v.color=0;
			v.distance=-1;//if after BFS, distance=-1 --> unreachable
			v.parent=null;
		}
		LinkedList<Vertex> queue = new LinkedList<>();
		vertex[start].color=1;
		vertex[start].distance=0;
		queue.add(vertex[start]);
		while(!queue.isEmpty()){
			Vertex u = queue.removeFirst();
			for(Vertex v : u.adj){
				if(v.color==0){
					v.color=1;
					v.distance=u.distance+1;
					v.parent=u;
					queue.add(v);
				}
			}
			u.color=2;
		}
	}
	
	String printPath(int start, int end){
		System.out.print("Path from "+start+" to "+end+": ");
		BFS(start);

		String path = print(start, end);
		System.out.println();
		return path;
	}
	
	String print(int start, int end){
		StringBuilder sb = new StringBuilder();
		if(end==start) {
			System.out.print(start+" ");
			sb.append(start+" ");
		}
		else if(vertex[end].parent==null) {
			System.out.println("No path from "+start+" to "+end+" exists.");
			sb.append("No path from "+start+" to "+end+" exists.");
		}
		else {
			sb.append(print(start,vertex[end].parent.data));
			sb.append(end+" ");
			System.out.print(end+" ");
		}

		return sb.toString();
	}
	
//	public static void main(String[] args){
//		BreadthFirstSearch g = new BreadthFirstSearch(5);
//		g.addEdge(0, 1);
//		g.addEdge(1, 2);
//		g.addEdge(2, 0);
//		g.addEdge(3, 3);
//		g.addEdge(4, 2);
//
//		g.printPath(1, 4);
//	}
}
