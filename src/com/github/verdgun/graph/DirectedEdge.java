package com.github.verdgun.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 加权有向边
 */
@Data
@AllArgsConstructor
public class DirectedEdge {
    private int from;
    private int to;
    private double weight;
}
