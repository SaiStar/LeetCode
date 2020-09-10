package medium;

import io.swagger.models.auth.In;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author wuxue
 * @create 2020-09-10
 */
@SpringBootTest
public class L40_CombinationSumII {
    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * 示例 2:
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     */

    @Test
    public void test(){
//        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int[] candidates = new int[]{2,5,2,1,2};
        System.out.println(combinationSum2(candidates,5));
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(candidates.length<0 || target<0){
            return ans;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        back(candidates,target,0,ans,path);
        return ans;
    }

    public void back(int[] candidates,int target,int index,List<List<Integer>> ans,Deque<Integer> path){
        if(target==0){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i=index; i<candidates.length; i++){
            if (i > index && candidates[i] == candidates[i-1]){
                continue;
            }
            if(target-candidates[i] < 0){
                return;
            }
            path.addLast(candidates[i]);
            back(candidates,target-candidates[i],i+1,ans,path);
            path.removeLast();
        }
    }
}
