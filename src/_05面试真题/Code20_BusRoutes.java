package _05面试真题;

import java.util.ArrayList;
import java.util.HashMap;

// 来自三七互娱
// Leetcode原题 : https://leetcode.com/problems/bus-routes/
public class Code20_BusRoutes {

	// 0 : [1,3,7,0]
	// 1 : [7,9,6,2]
	// ....
	// 返回：返回换乘几次+1 -> 返回一共坐了多少条线的公交。
	public static int numBusesToDestination(int[][] routes, int source, int target) {
		if (source == target) {
			return 0;
		}
		int n = routes.length;
		// key : 车站
		// value : list -> 该车站拥有哪些线路！
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				if (!map.containsKey(routes[i][j])) {
					map.put(routes[i][j], new ArrayList<>());
				}
				map.get(routes[i][j]).add(i);
			}
		}
		// 按公交线路进行宽度优先遍历
		ArrayList<Integer> queue = new ArrayList<>();
		boolean[] set = new boolean[n];
		// 起点站所在的所有线路先入队列
		for (int route : map.get(source)) {
			queue.add(route);
			set[route] = true;
		}
		int len = 1;
		while (!queue.isEmpty()) {
			// 宽度优先遍历一次取一层的技巧
			ArrayList<Integer> nextLevel = new ArrayList<>();
			// 搜索新线路，如果没有如果队列的，入队列
			for (int route : queue) {
				int[] bus = routes[route];
				for (int station : bus) {
					// 发现目的地站点，返回坐过的公交线路数
					if (station == target) {
						return len;
					}
					// 每个公交站属于那几个线路，已存在map中
					// 通过公交站，引出新线路
					for (int nextRoute : map.get(station)) {
						if (!set[nextRoute]) {
							nextLevel.add(nextRoute);
							set[nextRoute] = true;
						}
					}
				}
			}
			queue = nextLevel;
			len++;
		}
		return -1;
	}

}
