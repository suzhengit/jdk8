package su.jdk8.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author 苏征
 * @date 2018-11-29
 */
public class MathStream {

    /**
     * 小于等于n的勾股数
     */
    @Test
    void pythagoreanTriple(int n) {
        Stream<double[]> stream = IntStream.rangeClosed(1, n).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, n)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
        List<double[]> list = stream.collect(Collectors.toList());
        for (double[] doubles : list) {
            System.out.println(Arrays.toString(doubles));
        }
    }

    @Test
    void searchPythagoreanTriple() {
        pythagoreanTriple(150);
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
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
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
