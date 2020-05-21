package com.github.verdgun.graph;

import edu.princeton.cs.algs4.Bag;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        this.V = v;
        this.E = 0;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }

    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.getFrom()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> res = new Bag<>();
        for (Bag<DirectedEdge> directedEdges : adj) {
            for (DirectedEdge directedEdge : directedEdges) {
                res.add(directedEdge);
            }
        }

        return res;
    }
}
