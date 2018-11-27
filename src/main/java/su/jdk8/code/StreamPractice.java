package su.jdk8.code;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import su.jdk8.enums.Grade;
import su.jdk8.model.Student;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public class StreamPractice {

    /**
     * 将map中key存在于list的name取出来放在另一个集合中
     */
    @Test
    void testLambda() {
        List<String> list = Arrays.asList("1", "2", "3");
        List<String> result = Lists.newArrayList();
        Map<String, Student> map = new HashMap<String, Student>() {{
            put("1", new Student() {{
                setId(1);
                setName("关羽");
            }});
            put("2", new Student() {{
                setId(2);
                setName("张飞");
            }});
        }};
        //一个函数作为另一个函数的结果
        Function<String, BiConsumer<String, Student>> function = e -> (k, v) -> {
            if (e.equals(k)) {
                result.add(v.getName());
            }
        };
        list.stream()
            .map(function)
            .forEach(map::forEach);
        result.forEach(System.out::println);
    }

    /**
     * map的差集
     */
    @Test
    void testLambdaSubtraction() {
        Map<String, Student> mapA = new HashMap<String, Student>() {{
            put("1", new Student() {{
                setId(1);
                setName("关羽");
                setGrade(Grade.ONE);
            }});
            put("2", new Student() {{
                setId(2);
                setName("张飞");
            }});
            put("3", new Student() {{
                setId(3);
                setName("赵云");
            }});
            put("4", new Student() {{
                setId(4);
                setName("马超");
                setGrade(Grade.FOUR);
            }});
            put("5", new Student() {{
                setId(5);
                setName("黄忠");
            }});
        }};
        Map<String, Student> mapB = new HashMap<String, Student>() {{
            put("1", new Student() {{
                setId(1);
                setName("关羽");
            }});
            put("2", new Student() {{
                setId(2);
                setName("诸葛亮");
                setGrade(Grade.ONE);
            }});
            put("3", new Student() {{
                setId(3);
                setName("魏延");
            }});
            put("4", new Student() {{
                setId(4);
                setName("马超");
                setGrade(Grade.FOUR);
            }});
            put("5", new Student() {{
                setId(5);
                setName("黄忠");
                setGrade(Grade.THREE);
            }});
        }};
        Map<String, Student> result = mapA.entrySet().stream().filter(a -> {
            boolean flag = false;
            for (Map.Entry<String, Student> entry : mapB.entrySet()) {
                String key = entry.getKey();
                Student value = entry.getValue();
                if (value.getName().equals(a.getValue().getName()) && key.equals(a.getKey())) {
                    flag = true;
                }
            }
            return flag;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        MapUtils.verbosePrint(System.out, "Map差集列表", result);
    }
}
