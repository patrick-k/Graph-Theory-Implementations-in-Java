package com.unf.graph.mst;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * MinimumSpanningTreeTests.java
 * Patrick Kane (N01422638)
 * CEN6070 - Assignment 2 2/12/2021
 *
 * Test runner for metamorphic tests on:
 *
 * Kruskal's Minimum Spanning Tree
 * Prim's Minimum Spanning Tree
 *
 * We will exercise two metamorphic relations over these algorithms:
 *
 * MR1 - A graph with set V of vertices will have |V| - 1 edges with positive weights
 *  - mst(G) produces |E| = |V| - 1
 *
 * MR2 - The weight of mst(G) is less than the sum of weights of edges E in G
 *  - sum(mst(G).edges) <= sum(G.edges)
 */
@RunWith(JUnit4.class)
public class MinimumSpanningTreeTests {
    /**
     * Adjacency list, i = num edges, entry A[i] = [i,j,x] where i,j is an edge, x is the weight of that edge
     */
    private static int[][] kruskalWeights = new int[8][3];
    private static int kruskalSumWeights = 0;

    /**
     * Adjacency matrix, non-zero values correspond to an edge with weight
     */
    private static int[][] primWeights = new int[5][5];
    private static int primSumWeights = 0;

    @BeforeClass
    public static void classSetup() {
        kruskalWeights[0] = new int[]{0, 1, 1};
        kruskalWeights[1] = new int[]{0, 2, 4};
        kruskalWeights[2] = new int[]{0, 4, 2};
        kruskalWeights[3] = new int[]{2, 4, 3};
        kruskalWeights[4] = new int[]{1, 4, 3};
        kruskalWeights[5] = new int[]{3, 1, 3};
        kruskalWeights[6] = new int[]{3, 2, 1};
        kruskalWeights[7] = new int[]{3, 4, 2};

        for (int[] kruskalWeight : kruskalWeights) {
            kruskalSumWeights += kruskalWeight[2];
        }

        primWeights[0] = new int[]{0, 1, 4, 0, 2};
        primWeights[1] = new int[]{1, 0, 0, 3, 3};
        primWeights[2] = new int[]{4, 0, 0, 1, 3};
        primWeights[3] = new int[]{0, 3, 1, 0, 2};
        primWeights[4] = new int[]{2, 3, 3, 2, 0};

        for (int[] primWeight : primWeights) {
            for (int i = 0; i < primWeight.length; i++) {
                primSumWeights += primWeight[i];
            }
        }
        primSumWeights /= 2;
    }

    @Test
    public void prim_OneFewerEdgesThanVertices_MR1() {
        int numVertices = 5;
        PrimMST mst = new PrimMST(numVertices);

        mst.Prim(primWeights, 0);
        int numEdges = mst.getNumberOfEdges();

        assertEquals(numEdges, numVertices - 1);
    }

    @Test
    public void kruskal_OneFewerEdgesThanVertices_MR1() {
        int numVertices = 5;
        KruskalMST mst = new KruskalMST(numVertices);

        mst.Kruskal(kruskalWeights);
        int numEdges = mst.getNumberOfEdges();

        assertEquals(numEdges, numVertices - 1);
    }

    @Test
    public void prim_MstWeightsLessThanGraphWeights_MR2() {
        PrimMST mst = new PrimMST(5);
        int cost = mst.Prim(primWeights, 0);

        assertTrue(cost <= primSumWeights);
    }

    @Test
    public void kruskal_MstWeightsLessThanGraphWeights_MR2() {
        KruskalMST mst = new KruskalMST(5);
        int cost = mst.Kruskal(kruskalWeights);

        assertTrue(cost <= kruskalSumWeights);
    }
}
