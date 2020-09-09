package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author wuxue
 * @create 2020-09-08
 */
@SpringBootTest
public class Combinations {
    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * 示例:
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */

    @Test
    public void test(){
        System.out.println(combine(4,2));
    }

    List<List<Integer>> ans = new ArrayList<>();
    //Deque:双端队列
    Deque<Integer> temp = new ArrayDeque<>();
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 && n < k){
            return ans;
        }
        back(1,n,k);
        return ans;
    }

    /**
     * 回溯
     * @param cur
     * @param n
     * @param k
     */
    public void back(int cur,int n, int k){
        if(temp.size() == k){
            ans.add(new ArrayList<>(temp));
            return;
        }


        /**
         * 剪枝
         * 缩小循环范围，提高执行效率，减少了不必要的运算
         */
//        例如：n = 6 ，k = 4。
//
//        path.size() == 1 的时候，接下来要选择 3 个数，搜索起点最大是 4，最后一个被选的组合是 [4, 5, 6]；
//        path.size() == 2 的时候，接下来要选择 2 个数，搜索起点最大是 5，最后一个被选的组合是 [5, 6]；
//        path.size() == 3 的时候，接下来要选择 1 个数，搜索起点最大是 6，最后一个被选的组合是 [6]；

        // 搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        // 接下来要选择的元素个数 = k - path.size()
        // 搜索起点的上界 = n - (k - path.size()) + 1
        for (int i = cur;i <= n - (k - temp.size()) + 1;i++){
            temp.addLast(i);
//            System.out.println("递归之前："+temp.toString());
            back(i+1,n,k);
            temp.removeLast();
//            System.out.println("递归之后："+temp.toString());
        }

//        for (int i = cur;i <= n;i++){
//            temp.addLast(i);
////            System.out.println("递归之前："+temp.toString());
//            back(i+1,n,k);
//            temp.removeLast();
////            System.out.println("递归之后："+temp.toString());
//        }
    }

}
