package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author wuxue
 * @create 2020-09-11
 */
@SpringBootTest
public class L216_CombinationSumIII {
    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */

    @Test
    public void test(){
        System.out.println(combinationSum3(3,9));
    }

    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if(k > 10 || n > 5050){
            return ans;
        }
        back(1,k,n);
        return ans;
    }

    public void back(int index,int k,int n){

        if(n == 0 && path.size() == k){
            ans.add(new ArrayList<>(path));
//            System.out.println("ans:"+ans);
            return;
        }

        for (int i = index;i <= 9;i++){
            if(n-i < 0){
                return;
            }
            path.addLast(i);
//            System.out.println("path add:"+path);
//            System.out.println("n:"+(n-i));
            back(i+1,k,n-i);
            path.removeLast();
//            System.out.println("path remove:"+path);
        }
    }
}
