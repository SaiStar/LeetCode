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
        int[] candidates = new int[]{10,1,2,7,6,1,5};
//        int[] candidates = new int[]{2,5,2,1,2};
        System.out.println(combinationSum2(candidates,8));
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
            //大剪枝
            if(target-candidates[i] < 0){
                return;
            }
            /**
             * ！！！！！！！！！！重复避免---------看最后解释
             */
            //小剪枝，去除同层级的相同元素
            if (i > index && candidates[i] == candidates[i-1]){
                continue;
            }

            path.addLast(candidates[i]);
            back(candidates,target-candidates[i],i+1,ans,path);
            path.removeLast();
        }
    }
    /**
     * 这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
     *                   1
     *                  / \
     *                 2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
     *                /     \
     *               5       5
     *                 例2
     *                   1
     *                  /
     *                 2      这种情况确是允许的
     *                /
     *               2
     * 为何会有这种神奇的效果呢？
     * 首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
     * 可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。
     * 因为当第二个2出现的时候，他就和前一个2相同了。
     *
     * 那么如何保留例2呢？
     * 那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
     * 例2的两个2是处在不同层级上的。
     * 在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
     * 必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
     * 第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
     */
}
