package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author wuxue
 * @create 2020-09-09
 */
@SpringBootTest
public class CombinationSum {
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 提示：
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     */

    @Test
    public void test(){
        int[] candidates = new int[]{2,3,5};
        System.out.println(combinationSum(candidates,8));
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(candidates.length > 30 || target >500 || candidates.length < 0 || target < 0){
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        back(0,candidates,target,ans,path);
        return ans;
    }

    public void back(int index,int[] candidates,int target,List<List<Integer>> ans,Deque<Integer> path){
        if(0 == target){
            ans.add(new ArrayList<>(path));
//            System.out.println("ans add："+path);
            return;
        }
        for (int i = index;i<candidates.length;i++){
            if(target - candidates[i] < 0){
                break;
            }

            path.addLast(candidates[i]);
//                System.out.println("path add:"+item);
            back(i,candidates,target-candidates[i],ans,path);
            path.removeLast();
//                System.out.println("path remove:"+item);
        }
    }
}
