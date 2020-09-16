package easy;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxue
 * @create 2020-09-16
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
@SpringBootTest
public class L226_InvertBinaryTree {
    /**
     * 翻转一棵二叉树。
     */
    @Test
    public void test(){
        List<Integer> tree = new ArrayList<>();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        printTree(root,tree);
        System.out.println(tree);
        tree.clear();
        root = invertTree(root);
        printTree(root,tree);
        System.out.println(tree);
    }
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public void printTree(TreeNode root,List<Integer> tree){
        if (root == null){
            return;
        }
        printTree(root.left,tree);
        tree.add(root.val);
        printTree(root.right,tree);
    }
}
