package _01基础._13图.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图结构
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph {

    //图中所有的点
    public Map<Integer, Node> nodes;

    //图中所有的边
    public Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
