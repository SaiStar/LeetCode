package Hard;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wuxue
 * @create 2020-09-15
 */
@SpringBootTest
public class L17_SudokuSolver {
    /**
     * 编写一个程序，通过已填充的空格来解决数独问题。
     *
     * 一个数独的解法需遵循如下规则：
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     *
     * Note:
     * 给定的数独序列只包含数字 1-9 和字符 '.' 。
     * 你可以假设给定的数独只有唯一解。
     * 给定数独永远是 9x9 形式的。
     */

    @Test
    public void test(){
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
    }
    public void solveSudoku(char[][] board) {
        //行 数字使用情况
        boolean[][] rowUsed = new boolean[9][10];
        //列 数字使用情况
        boolean[][] colUsed = new boolean[9][10];
        //方格 数字使用情况
        boolean[][][] boxUsed = new boolean[3][3][10];
        //初始化 统计每行、每列、每方格的数字使用情况
        for (int i = 0;i < board.length;i++){
            for (int j = 0;j < board[i].length;j++){
                int cap = board[i][j] - '0';
                if(1 <=cap && cap <=9){
                    //第i行数字cap已使用，标记为true
                    rowUsed[i][cap] = true;
                    colUsed[j][cap] = true;
                    boxUsed[i/3][j/3][cap] = true;
                }
            }
        }
        recusiveSolveSudoku(board,rowUsed,colUsed,boxUsed,0);
        for (char[] cc:board){
            System.out.println(cc);
        }
    }

    private boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][][]boxUsed,
                                        int pos){
        //边界校验，如果已填充结束，返回true
        if (pos == 81){
            return true;
        }
        int row = pos / 9;
        int col = pos % 9;

        //该位置不为空，即已存在，则填充下一位
        if (board[row][col] != '.'){
            return recusiveSolveSudoku(board,rowUsed,colUsed,boxUsed,pos+1);
        }

        //尝试填充1-9数字
        for (int i = 1;i <= 9;i++){
            //如果数字i，该行、列、方格已存在，继续循环下一个
            if (rowUsed[row][i] || colUsed[col][i] || boxUsed[row/3][col/3][i]){
                continue;
            }
            //否则，尝试填充数字i
            rowUsed[row][i] = colUsed[col][i] = boxUsed[row/3][col/3][i] = true;
            board[row][col] = (char) (i + '0');
            if (recusiveSolveSudoku(board,rowUsed,colUsed,boxUsed,pos+1)){
                return true;
            }
            //填充失败则回溯到前一个状态
            rowUsed[row][i] = colUsed[col][i] = boxUsed[row/3][col/3][i] = false;
        }
        //数字1-9均填充失败，回溯到初始状态，即为空
        board[row][col] = '.';
        return false;
    }
}
