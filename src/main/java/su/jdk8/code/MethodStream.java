package su.jdk8.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 苏征
 * @date 2018-11-29
 */
public class MethodStream {
    /**
     * 多个集合汇合成一个流
     */
    @Test
    void listConverge() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 2, 3, 5);
        List<Integer> flatList = Stream.of(list1, list2)
             //   .flatMap(list -> list.stream())
                .flatMap(List::stream)  //实例方法引用
                .collect(Collectors.toList());
        System.out.println(flatList);
    }

    /**
     * 多个数组汇合成一个数组流
     */
    @Test
    void arrayConverge() {
        String[] array1 = {"A","B","C","D"};
        String[] array2 = {"D","E","C","A"};
        String[] flatArray = Stream.of(array1, array2)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(flatArray));
    }

    @Test
    void reduceStream() {
        Integer sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }


    @Test
    void test4() {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
        List<double[]> list = stream.collect(Collectors.toList());
        for (double[] doubles : list) {
            System.out.println(Arrays.toString(doubles));
        }
    }

}
