package com.unf.graph.search;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * GraphSearchTests.java
 * Patrick Kane (N01422638)
 * CEN6070 - Assignment 2 2/12/2021
 *
 * Test runner for metamorphic tests on:
 *
 * Breadth-First Search
 * Depth-First Search
 *
 * We will exercise three metamorphic relations over these algorithms:
 *
 * MR1 - Exchanging start and end inputs will produce the same path (and cost where applicable)
 *  - graphSearch(G, x, y) = graphSearch(G, y, x)
 *
 * MR2 - Exchanging start and end nodes in the graph structure will produce the same path (and cost where applicable)
 *  - graphSearch(G, x, y) = graphSearch(G', x, y), where G' is the graph G with nodes x and y swapped
 *    in the underlying adjacency matrix
 *
 * MR3 - Moving the starting vertex 'forward' along a path in the input graph will produce
 *   - graphSearch(G, x, y) = graphSearch(G, z, y) - z_w, where z_w is the weight of node z, z is the first
 *     node returned in the path generated from graphSearch(G, x, y)
 */
@RunWith(JUnit4.class)
public class GraphSearchTests {
    private BreadthFirstSearch bfs;
    private DepthFirstSearch dfs;

    @Before
    public void setup() {
        bfs = new BreadthFirstSearch(5);
        bfs.addEdge(0, 1);
        bfs.addEdge(1, 2);
        bfs.addEdge(2, 0);
        bfs.addEdge(3, 3);
        bfs.addEdge(4, 2);

        dfs = new DepthFirstSearch(5);
        dfs.addEdge(0, 1);
        dfs.addEdge(1, 2);
        dfs.addEdge(3, 3);
        dfs.addEdge(4, 2);
    }

    @Test
    public void bfs_exchangeStartAndEnd_MR1() {
        int startNode = 1;
        int endNode = 4;

        String path = StringUtils.trim(bfs.printPath(startNode, endNode));
        String swappedPath = StringUtils.trim(bfs.printPath(endNode, startNode));

        assertEquals(path, StringUtils.reverse(swappedPath));
    }

    @Test
    public void dfs_exchangeStartAndEnd_MR1() {
        int startNode = 1;
        int endNode = 4;

        String path = StringUtils.trim(dfs.printPath(startNode, endNode));
        String swappedPath = StringUtils.trim(dfs.printPath(endNode, startNode));

        assertEquals(path, StringUtils.reverse(swappedPath));
    }

    @Test
    public void bfs_exchangeStartAndEndNodes_MR2() {
        BreadthFirstSearch bfs2 = new BreadthFirstSearch(5);
        bfs2.addEdge(0, 1);
        bfs2.addEdge(4, 2);
        bfs2.addEdge(2, 0);
        bfs2.addEdge(3, 3);
        bfs2.addEdge(1, 2);

        int startNode = 1;
        int endNode = 4;

        String path = StringUtils.trim(bfs.printPath(startNode, endNode));
        String swappedPath = StringUtils.trim(bfs2.printPath(endNode, startNode));

        assertEquals(path, StringUtils.reverse(swappedPath));
    }

    @Test
    public void dfs_exchangeStartAndEndNodes_MR2() {
        DepthFirstSearch dfs2 = new DepthFirstSearch(5);
        dfs2.addEdge(0, 1);
        dfs2.addEdge(4, 2);
        dfs2.addEdge(3, 3);
        dfs2.addEdge(1, 2);

        int startNode = 1;
        int endNode = 4;

        String path = StringUtils.trim(dfs.printPath(startNode, endNode));
        String swappedPath = StringUtils.trim(dfs2.printPath(endNode, startNode));

        assertEquals(path, StringUtils.reverse(swappedPath));
    }

    @Test
    public void bfs_moveStartNode_MR3() {
        int startNode = 1;
        int newStartNode = startNode++;
        int endNode = 4;

        String path = StringUtils.trim(bfs.printPath(startNode, endNode));
        String newPath = StringUtils.trim(bfs.printPath(newStartNode, endNode));

        // subtract 2 for trailing space in new path length
        assertEquals(path.length(), newPath.length() - 2);
    }

    @Test
    public void dfs_moveStartNode_MR3() {
        int startNode = 1;
        int newStartNode = startNode++;
        int endNode = 4;

        String path = StringUtils.trim(dfs.printPath(startNode, endNode));
        String newPath = StringUtils.trim(dfs.printPath(newStartNode, endNode));

        // subtract 2 for trailing space in new path length
        assertEquals(path.length(), newPath.length() - 2);
    }
}
