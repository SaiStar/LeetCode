package Hard;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author wuxue
 * @create 2020-09-03
 */
@SpringBootTest
public class NQueens {
    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     *
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     *  输入：4
     * 输出：[
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     */
    @Test
    public void test(){

    }

    /**
     * 基于集合的回溯
     *
     * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合columns、diagonals1、diagonals2分别记录每一列以及两个方向的每条斜线上是否有皇后。
     * 列的表示法很直观，一共有 N 列，每一列的下标范围从 0 到 N-1，使用列的下标即可明确表示每一列。
     *
     * 如何表示两个方向的斜线呢？对于每个方向的斜线，需要找到斜线上的每个位置的行下标与列下标之间的关系。
     * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如 (0,0) 和 (3,3) 在同一条方向一的斜线上。因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
     *
     * 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，例如 (3,0) 和 (1,2) 在同一条方向二的斜线上。因此使用行下标与列下标之和即可明确表示每一条方向二的斜线。
     *
     * 每次放置皇后时，对于每个位置判断其是否在三个集合中，如果三个集合都不包含当前位置，则当前位置是可以放置皇后的位置。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        //每一列上是否有皇后
        Set<Integer> columns = new HashSet<Integer>();
        //方向一是否有皇后
        Set<Integer> diagonals1 = new HashSet<Integer>();
        //方向二是否有皇后
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            //到最后一行，将解加入数组
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                //方向一行差
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                //方向二行和
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }

                //做选择
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                //进入下一个决策树
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                //取消选择
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
