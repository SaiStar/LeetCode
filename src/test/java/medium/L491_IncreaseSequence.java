package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxue
 * @create 2020-08-25
 */
@SpringBootTest
public class L491_IncreaseSequence {

    //给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
    /*
    输入: [4, 6, 7, 7]
    输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
    说明:

    给定数组的长度不会超过15。
    数组中的整数范围是 [-100,100]。
    给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     */
    private  List<List<Integer>> results = new ArrayList<>();
    //临时数组保存当前选出的子序列
    private  List<Integer> temp = new ArrayList<>();

    @Test
    public void test(){
        System.out.println(findSubsequences());
    }

    public List<List<Integer>> findSubsequences() {
        int[] nums = new int[]{4, 6, 7, 7};
        DFS(0,Integer.MIN_VALUE,nums);
        return results;
    }

    /**
     *在执行DFS时，考虑cur当前位置选或者不选。
     * 如果选择当前元素，那么就把当前元素加入temp中，然后递归到下一个位置，在递归结束后，把temp的最后一个元素删除进行回溯
     * 如果不选当前元素，直接递归下一个位置
     * @param cur //当前位置的下标
     * @param preValue
     * @param nums
     */
    public void DFS(int cur, int preValue, int[] nums){
        if(cur >= nums.length){
            if(temp.size()>=2){
                results.add(new ArrayList<>(temp));
            }
            return;
        }
        //将当前元素加入，并向后遍历
        if (nums[cur] >= preValue){
            //选择当前元素
            temp.add(nums[cur]);
            //继续向下遍历
            DFS(cur+1,nums[cur],nums);
            //撤销当前选中数字，选中其它数字
            temp.remove(temp.size()-1);
        }
        //不遍历重复元素
        if (nums[cur] != preValue){
            DFS(cur+1,preValue,nums);
        }
    }

    @Test
    public void listInit(){
        List<List<Integer>> listList = new ArrayList<>();
        for(int i = 0;i < 3;i++){
            List<Integer> list = new ArrayList<>();
            list.add(i+1);
            list.add(i+2);
            listList.add(list);
        }
        for (List<Integer> li:listList){
            System.out.println(li.hashCode());
            System.out.println(li);
        }


    }
}
