package _01基础._13图.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图中的点
 * Created by huangjunyi on 2022/8/30.
 */
public class Node {

    //点的集合
    public int value;

    //入度
    public int in;

    //出度
    public int out;

    //邻居节点集合
    public List<Node> nexts;

    //到邻居节点的边的集合
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
