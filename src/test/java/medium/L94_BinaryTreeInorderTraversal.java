package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author wuxue
 * @create 2020-09-14
 */
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
@SpringBootTest
public class L94_BinaryTreeInorderTraversal {
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * 示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(root));
    }
    //标记法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Deque<Object> deque = new LinkedList<>();
        if (null == root){
            return ans;
        }
        //队首入列
        deque.push(root);
        while (!deque.isEmpty()){
            Object pop = deque.pop();
            if (pop instanceof Integer){
                ans.add((Integer)pop);
            }else {
//                栈是一种 先进后出的结构，出栈顺序为左，中，右
//                那么入栈顺序必须调整为倒序，也就是右，中，左
                TreeNode treeNode = (TreeNode)pop;
                if (null != treeNode.right){
                    deque.push(treeNode.right);
                }
                deque.push(new Integer(treeNode.val));
                if (null != treeNode.left){
                    deque.push(treeNode.left);
                }
            }
        }
        return ans;
    }
}