package _01基础._13图.graph;

/**
 * 图中的边
 * Created by huangjunyi on 2022/8/30.
 */
public class Edge {

    //边的权重
    public int value;

    //边的起始点
    public Node from;

    //边的终点
    public Node to;

    public Edge(int value, Node from, Node to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }
}
