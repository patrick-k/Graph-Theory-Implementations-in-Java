package com.unf.graph.search;

import com.unf.graph.Graph;
import com.unf.graph.Vertex;

import java.util.List;

public class DepthFirstSearch extends Graph {
	int time;
	
	public DepthFirstSearch(int v) {
		super(v);
		time=0;
	}

	protected void reset() {
		for(Vertex v: vertex){
			v.color=0;
			v.distance=-1;
			v.parent=null;
		}
	}
	
	void DFS(int start){
		reset();
		time=0;
		DFSVisit(start);
	}
	
	void DFSVisit(int u){
		vertex[u].color = 1;
		List<Vertex> neighbors = vertex[u].adj;
		if (neighbors == null) {
			return;
		}

		for (Vertex v : neighbors) {
			if (v.color == 0) {
				System.out.println("Parent of " + v.data + " is " + vertex[u].data);
				v.parent = vertex[u];
				DFSVisit(v.data);
			}
		}
	}
	
	void printTimes(){
		for(Vertex v: vertex){
			System.out.printf("Vertex: %2d start:%2d finish:%2d\n",v.data,
					vertex[v.data].discoveryTime, vertex[v.data].finishTime);
		}
	}

	String printPath(int start, int end){
		System.out.print("Path from "+start+" to "+end+": ");
		DFS(start);

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
//		DepthFirstSearch g = new DepthFirstSearch(4);
//		g.addEdge(0, 1);
//		g.addEdge(1, 2);
//		g.addEdge(3, 3);
//		g.addEdge(4, 2);
//		g.DFS(1);
//		g.printTimes();
//	}
}
