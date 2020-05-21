package com.github.verdgun.string;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;

public class NFA {
    private char[] re;
    private Digraph g;
    private int reLength;

    public NFA(String re) {
        this.re = re.toCharArray();
        reLength = re.length();
        g = new Digraph(reLength + 1);

        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < reLength; i++) {
            int lp = i;
            if (this.re[i] == '(' || this.re[i] == '|') {
                ops.push(i);
            }
            //如果此时re[i]为右括号
            else if (this.re[i] == ')') {
                //此时or可能是'('也可能是'|'
                int or = ops.pop();
                if (this.re[or] == '|') {
                    //此时lp指向左括号
                    lp = ops.pop();
                    g.addEdge(lp, or + 1);
                    g.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            //此时lp可能指向左括号或者非')','|'的字符
            if (i < reLength - 1 && this.re[i + 1] == '*') {
                g.addEdge(lp, i + 1);
                g.addEdge(i + 1, lp);
            }

            if (this.re[i] == '(' || this.re[i] == ')' || this.re[i] == '*') {
                g.addEdge(i, i + 1);
            }
        }
        if (!ops.isEmpty())
            throw new IllegalArgumentException("Invalid regular expression");
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        //获取从起始点可达的位置
        DirectedDFS dfs = new DirectedDFS(g, 0);
        //初始化pc,获取从起点可达的所有状态(位置)
        for (int i = 0; i < g.V(); i++) {
            if (dfs.marked(i)) {
                pc.add(i);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            //计算txt[i+1]可能到达的所有nfa状态
            Bag<Integer> match = new Bag<>();
            for (Integer v : pc) {
                if (v == reLength) continue;
                if (txt.charAt(i) == re[v] || '.' == re[v]) {
                    match.add(v+1);
                }
            }

            pc = new Bag<>();
            dfs = new DirectedDFS(g, match);
            for (int v = 0; v < g.V(); v++) {
                if (dfs.marked(v))
                    pc.add(v);
            }
        }

        for (Integer integer : pc) {
            if (integer == reLength) return true;
        }

        return false;
    }
}
