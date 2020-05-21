package com.github.verdgun.graph;

import java.util.Arrays;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQueue;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph edgeWeightedDigraph, int source) {
        int size = edgeWeightedDigraph.V();
        distTo = new double[size];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0d;
        edgeTo = new DirectedEdge[size];
        queue = new Queue<Integer>();
        queue.enqueue(source);
        onQueue[source] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(edgeWeightedDigraph, v);
        }

    }

    private void relax(EdgeWeightedDigraph edgeWeightedDigraph, int v) {
        for (DirectedEdge directedEdge : edgeWeightedDigraph.adj(v)) {
            int w = directedEdge.to();
            if (distTo[w] > distTo[v] + directedEdge.weight()) {
                distTo[w] = distTo[v] + directedEdge.weight();
                edgeTo[w] = directedEdge;
                if (!onQueue[w]) {
                    onQueue[w] = true;
                }
            }
            //如果遍历没有结束,但路径中的顶点树达到了edgeWeightedDigraph.getV()个,说明存在负权重环
            if (++cost % edgeWeightedDigraph.V() == 0)
                findNegativeCycle();
            if (hasNegativeCycle()) {
                break;
            }
        }
    }

    private void findNegativeCycle() {
        int size = distTo.length;
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(size);
        for (int i = 0; i < size; i++) {
            if (edgeTo[i] != null) {
                edgeWeightedDigraph.addEdge(edgeTo[i]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(edgeWeightedDigraph);
        cycle = finder.cycle();

    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
    }


}
