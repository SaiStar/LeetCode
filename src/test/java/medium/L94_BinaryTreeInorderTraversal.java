package medium;

import com.sun.javafx.logging.PulseLogger;
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

//        System.out.println(inorderTraversal(root));
//        System.out.println(remarkInorder(root));
//        System.out.println(stackPreorder(root));
        System.out.println(stackPostorder(root));
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


    //递归
    @Test
    public void DiGui(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> ans = new ArrayList<>();
        back(root,ans);
        System.out.println(ans);
    }
    public void back(TreeNode t,List<Integer> ans){
        if(null == t){
            return;
        }
        back(t.left,ans);
        ans.add(t.val);
        back(t.right,ans);
    }

    //非递归
    @Test
    public void FeiDiGui(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> ans = new ArrayList<>();
        System.out.println(zhan(root));
    }
    public List<Integer> zhan(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> path = new LinkedList<>();
        TreeNode pNode = root;
        while (null != pNode || !path.isEmpty()){
            while (null != pNode){
                path.push(pNode);
                pNode = pNode.left;
            }
            pNode = path.pop();
            ans.add(new Integer(pNode.val));
            pNode = pNode.right;
        }
        return ans;
    }

    public List<Integer> remarkInorder(TreeNode root){

        List<Integer> ans = new ArrayList<>();
        Deque<Object> path = new LinkedList<>();
        if (root == null)
            return ans;
        path.push(root);
        while (!path.isEmpty()){
            Object pop = path.pop();
            if(pop instanceof Integer){
                ans.add((Integer) pop);
            }else {
                TreeNode cur = (TreeNode)pop;
                if (cur.right != null){
                    path.push(cur);
                }
                path.push(new Integer(cur.val));
                if (cur.left != null){
                    path.push(cur.left);
                }
            }
        }
        return ans;
    }

    public List<Integer> stackPreorder(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> path = new ArrayDeque<>();
        TreeNode pNode = root;
        while (!path.isEmpty() || pNode != null){
            while (pNode != null){
                path.push(pNode);
                ans.add(pNode.val);
                pNode = pNode.left;
            }
            pNode = path.pop();

            pNode = pNode.right;
        }
        return ans;
    }

    public List<Integer> stackPostorder(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> path = new ArrayDeque<>();
        TreeNode pNode = root;
        TreeNode visited = null;
        while (!path.isEmpty() || pNode != null){
            while (pNode != null){
                path.push(pNode);
                pNode = pNode.left;
            }
            pNode = path.peek();
            //如果右节点为Null 或者已经被访问过，则出栈；否则继续向下访问
            if (pNode.right == null || pNode.right == visited){
                pNode = path.pop();
                ans.add(pNode.val);
                visited = pNode;
                pNode = null;
            }else {
                pNode = pNode.right;
            }
        }
        return ans;
    }
}
