package medium;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuxue
 * @create 2020-08-26
 */
@SpringBootTest
public class LetterCombinationsOfaPhoneNumber {
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    /**
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    @Test
    public void test(){
        String digits = "234";
        System.out.println(letterCombinations(digits).toString());
    }

    List<String> ans = new ArrayList<>();
//    String temp = "";
    StringBuffer temp = new StringBuffer();
    Map<Character,String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        //空判断
        if(digits.length() > 0){
            back(0,digits);
        }
        return ans;
    }

    public void back(int cur,String digits){
        if(cur >= digits.length()){
            ans.add(temp.toString());
        }else {
            char target = digits.charAt(cur);
            String letter = map.get(target);
            for (int i = 0;i<letter.length();i++){
                temp.append(letter.charAt(i));
                back(cur+1,digits);
                temp.deleteCharAt(cur);
            }
        }
    }
    //频繁组装String，浪费时间
//    public void back(int cur,String digits){
//        if(cur >= digits.length()){
//            ans.add(temp);
//            return;
//        }
//        else {
//            char target = digits.charAt(cur);
//            char[] letter = map.get(target).toCharArray();
//
//            for (char cc:letter){
//                temp += cc;
//                back(cur+1,digits);
//                temp = temp.substring(0,temp.length()-1);
//            }
//        }
//    }

}
