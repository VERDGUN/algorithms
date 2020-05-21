package com.github.verdgun.graph;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;

public class KosarajuSCC {
    /**
     * 已访问过的顶点
     */
    private boolean[] marked;
    /**
     * 强连通分量标识符;
     */
    private int[] id;

    /**
     * 强连通分量的数量;
     */
    private int count;


    public KosarajuSCC(Digraph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        DepthFirstOrder order = new DepthFirstOrder(graph.reverse());
        for (Integer s : order.reversePost()) {
            if (!marked[s]) {
                dfs(graph,s);
                count++;
            }
        }
    }

    private void dfs(Digraph graph, Integer s) {
        marked[s]=true;
        id[s]=count;

        for (Integer w : graph.adj(s)) {
            if (!marked[w]){
                dfs(graph,w);
            }
        }
    }

}
