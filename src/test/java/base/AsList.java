package base;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuxue
 * @create 2020-08-26
 */
@SpringBootTest
public class AsList {

    @Test
    public void dequeAsStack(){
        Deque<Integer> deque = new LinkedList<>();
        deque.push(2);
        deque.push(1);
        deque.push(3);
        System.out.println(deque);
        System.out.println(deque.peek());
        System.out.println(deque.pop());
        System.out.println(deque);
    }
    @Test
    public void arrayAsList(){
        String[] str = new String[]{"u","i"};
        List list = Arrays.asList(str);
        System.out.println(list.get(0));

        //运行异常
//        list.add("qwe");

        //String数组修改后，List也会修改
        str[0] = "123";
        System.out.println(list);
    }

    //传递的数组必须是对象数组，而不是基本类型。
    //Arrays.asList()是泛型方法，传入的对象必须是对象数组。
    @Test
    public void baseType(){
//        int[] myArray = { 1, 2, 3 };
//        List myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
//        //当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身！此时List 的唯一元素就是这个数组
////        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        int [] array=(int[]) myList.get(0);
//        System.out.println(array[1]);//1


        Integer[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//3
        System.out.println(myList.get(0));//1
        System.out.println(myList.get(1));//2

    }

    /**
     * 将数组转换为ArrayList
     */
    @Test
    public void changeList(){
        List myList = Arrays.asList(1, 2, 3);
        System.out.println(myList.getClass());//class java.util.Arrays$ArrayList

        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println(list.getClass());//class java.util.ArrayList




        Integer [] myArray = { 1, 2, 3 };
        System.out.println(arrayToList(myArray).getClass());//class java.util.ArrayList

//        使用 Java8 的Stream(推荐)
        List streamList = Arrays.stream(myArray).collect(Collectors.toList());
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List streamBoxedList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

    }

    //JDK1.5+
    static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s : array) {
            l.add(s);
        }
        return (l);
    }


    /**
     * Collection.toArray()
     * 该方法是一个泛型方法：<T> T[] toArray(T[] a); 如果toArray方法中没有传递任何参数的话返回的是Object类型数组。
     * 由于JVM优化，new String[0]作为Collection.toArray()方法的参数现在使用更好，new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型。
     */
    @Test
    public void reverseCollection(){
        String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s=list.toArray(new String[0]);//没有指定类型的话会报错

        System.out.println(s[0]);
    }

    /**
     * 不要在foreach循环里进行元素的remove/add操作
     * 如果要进行remove操作，可以调用迭代器的 remove方法而不是集合类的 remove 方法。因为如果列表在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身remove/add方法，迭代器都将抛出一个ConcurrentModificationException,这就是单线程状态下产生的 fail-fast 机制。
     * fail-fast 机制 ：多个线程对 fail-fast 集合进行修改的时，可能会抛出ConcurrentModificationException，单线程下也会出现这种情况，上面已经提到过。
     * java.util包下面的所有的集合类都是fail-fast的，而java.util.concurrent包下面的所有的类都是fail-safe的。
     *
      */
     @Test
     public void removeList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

//        for (String item:list){
//            if("2".equals(item)){
//                list.remove(item);//报错：java.util.ConcurrentModificationException
//            }
//        }

         //不要在List 元素的 foreach循环里进行元素的remove/add操作。remove元素使用Iterator方式，如果并发操作，需要对Iterator对象加锁
         Iterator<String> iterator = list.iterator();
         while (iterator.hasNext()){
             String item = iterator.next();
             if("1".equals(item)){
                 //remove()将会删除上次调用next()时返回的元素，也就是说先调用next()方法，再调用remove方法才会删除元素。
                iterator.remove();
            }
         }
         System.out.println(list);
     }
}
