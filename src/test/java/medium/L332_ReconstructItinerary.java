package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author wuxue
 * @create 2020-08-27
 */
@SpringBootTest
public class L332_ReconstructItinerary {
    /**
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
     *
     * 说明:
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * 示例 1:
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * 示例 2:
     * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     */

//    Hierholzer 算法
//    欧拉通路
    //什么时候能用到优先队列呢？ 就是需要排序的时候。
    Map<String,PriorityQueue<String>> map = new HashMap<>();
    List<String> ans = new LinkedList<>();

    @Test
    public void test(){
        List<List<String>> tickets = new ArrayList<>();
        List<String> ticket1 = Arrays.asList("MUC", "LHR");
        tickets.add(ticket1);
        List<String> ticket2 = Arrays.asList("JFK", "MUC");
        tickets.add(ticket2);
        List<String> ticket3 = Arrays.asList("SFO", "SJC");
        tickets.add(ticket3);
        List<String> ticket4 = Arrays.asList("LHR", "SFO");
        tickets.add(ticket4);
//        System.out.println(tickets);
        List<String> ans = findItinerary(tickets);
        System.out.println(ans);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket:tickets){
            String start = ticket.get(0);
            String end = ticket.get(1);
            if(!map.containsKey(start)){
                map.put(start,new PriorityQueue<>());
            }
            map.get(start).offer(end);
        }
//        1、从起点出发，进行深度优先搜索
        DFS("JFK");
        Collections.reverse(ans);
        return ans;
    }

    public void DFS(String cur){
        while (map.containsKey(cur) && map.get(cur).size() > 0){
//            2、每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
            String temp = map.get(cur).poll();
            DFS(temp);
        }
//        3、如果没有可移动的路径，则将所在节点加入到栈中，并返回。
        ans.add(cur);
    }
}
