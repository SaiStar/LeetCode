package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author wuxue
 * @create 2020-09-07
 */
@SpringBootTest
public class TopKFrequentElements {
    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     */

    @Test
    public void test(){
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        int k = 2;
        int[] ans = topKFrequent(nums,k);
        for (int i = 0;i < k;i++){
            System.out.println(ans[i]);
        }
    }



    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];

        //借助哈希表来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int n:nums){
            if (map.containsKey(n)){
                map.put(n,map.get(n)+1);
            }
            else {
                map.put(n,1);
            }
        }

        //打印哈希表
        for (Integer keys : map.keySet())
        {
            System.out.println(keys + ":"+ map.get(keys));
        }

        //维护元素数目为K的最小堆
        //创建最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });
        //将哈希表的前K个元素加入堆
        //当堆满后
        //将当前元素与堆顶元素进行比较，如果当前元素大于堆顶元素，则删除堆顶元素，将当前元素加入堆
        for (Integer key:map.keySet()){
            if (queue.size() < k){
                queue.add(key);
            }else if (map.get(key)>map.get(queue.peek())){
                queue.remove();
                queue.add(key);
            }
        }

        //最小堆中的K个元素即为前K个高频元素
        int i = 0;
        while (!queue.isEmpty() && i<k){
            ans[i] = queue.remove();
            i++;
        }
        return ans;
    }
 }