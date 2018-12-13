package su.jdk8.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

/**
 * @author 苏征
 * @date 2018-11-29
 */
public class MathStream {

    /**
     * 小于等于n的勾股数
     */
    private List<List<Integer>> pythagoreanTriple(int n) {
        Stream<double[]> stream = IntStream.rangeClosed(1, n).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, n).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
        List<double[]> list = stream.collect(toList());
        List<List<Integer>> result = new ArrayList<>();
        list.forEach(e -> {
            List<Integer> l = Arrays.stream(e).mapToInt(f -> (int) f).boxed().collect(toList());
            result.add(l);
        });
        return result;
    }

    @Test
    void searchPythagoreanTriple() {
        List<List<Integer>> list = pythagoreanTriple(150);
        list.forEach(System.out::println);
    }

    /**
     * 判断一个数是否是质数,仅测试小于等于待测数平方根的因子
     * 因为如果它不是质数，那么它一定可以表示成两个数（除了1和它本身）相乘，这两个数必然有一个小于等于它的平方根。
     * 只要找到小于或等于的那个就行了
     */
    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 找出n以下的质数和合数
     * @param n
     * @return
     */
    private Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(this::isPrime));
    }

    @Test
    void searchPrime() {
        Map<Boolean, List<Integer>> map = partitionPrimes(1885);
        List<Integer> list = map.get(true);
        System.out.println(list);
    }

}
