package com.github.verdgun.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 * 无向图
 */
public class BasicGraph {
    private int v;
    private int e;
    private ArrayList<Node> bucket;

    public BasicGraph(int v, int e, Edge[] bucket) {
        this.v = v;
        this.e = e;
        this.bucket = new ArrayList<>(v);
        for (int i = 0; i < bucket.length; i++) {
            Edge edge = bucket[i];
            Node node = this.bucket.get(edge.v);
            Node build = new Node(edge.w, node);
            this.bucket.set(edge.v, build);

            node = this.bucket.get(edge.w);
            build = new Node(edge.v, node);
            this.bucket.set(edge.w, build);
        }
    }

    public BasicGraph(int v) {
        this.v = v;
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    public void add(int v, int w) {
        if (bucket == null) {
            bucket = new ArrayList<>();
        }
        e++;

        Node node = this.bucket.get(v);
        Node build = new Node(w, node);
        this.bucket.set(v, build);

        node = this.bucket.get(w);
        build = new Node(v, node);
        this.bucket.set(w, build);
    }

    /**
     * 获取顶点v的所有相邻顶点
     *
     * @param v 顶点v
     * @return 顶点v的所有相邻顶点
     */
    public Node adj(int v) {
        return bucket.get(v);
    }

    /**
     * @param graph 图
     * @param v     顶点
     * @return 顶点v的深度
     */
    public static long degree(BasicGraph graph, int v) {
        Node node = graph.bucket.get(v);
        int sum = 0;
        while (node != null) {
            sum += 1;
            node = node.next;
        }
        return sum;
    }

    /**
     * @param graph 图
     * @return 所有顶点的最大度数
     */
    public static long maxDegree(BasicGraph graph) {
        long max = 0;
        for (int i = 0; i < graph.v; i++) {
            long temp = degree(graph, i);
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }

    /**
     * @param graph 图
     * @return 计算所有顶点的平均度数
     */
    public static long avgDegree(BasicGraph graph) {
        return graph.e * 2 / graph.v;
    }

    public static long numberOfSelfLoops(BasicGraph graph) {
        int sum = 0;
        for (int i = 0; i < graph.bucket.size(); i++) {
            Node node = graph.bucket.get(i);
            while (node != null) {
                if (node.w == i)
                    sum++;
                node = node.next;
            }
        }

        return sum;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(v + " vertices, " + e + " edges\n");
        for (int i = 0; i < v; i++) {
            stringBuilder.append(i + " : ");
            Node node = bucket.get(i);
            while (node != null) {
                stringBuilder.append(node.w + " ");
                node = node.next;
            }

            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    @Data
    @AllArgsConstructor
    public class Edge {
        private int v;
        private int w;
    }

    @AllArgsConstructor
    private class Node {
        int w;
        Node next;
    }
}
