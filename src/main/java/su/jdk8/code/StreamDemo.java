package su.jdk8.code;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.*;
import su.jdk8.enums.Gender;
import su.jdk8.enums.Grade;
import su.jdk8.model.Student;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public class StreamDemo {
    private List<Student> students;
    @BeforeEach
    void init() {
        students = Arrays.asList(
                new Student(1, "小明", Gender.MALE, Grade.ONE, 84),
                new Student(2, "小红", Gender.FEMALE, Grade.TWO, 85),
                new Student(3, "小张", Gender.MALE, Grade.ONE, 35),
                new Student(4, "小王", Gender.MALE, Grade.THREE, 46),
                new Student(5, "小丽", Gender.FEMALE, Grade.TWO, 55),
                new Student(6, "小青", Gender.FEMALE, Grade.FOUR, 74),
                new Student(7, "小白", Gender.FEMALE, Grade.TWO, 94),
                new Student(8, "小刘", Gender.MALE, Grade.FOUR, 66),
                new Student(9, "小新", Gender.MALE, Grade.THREE, 59),
                new Student(10, "小慧", Gender.FEMALE, Grade.ONE, 95));
    }

    /**
     * select * from student s group by s.grade;
     */
    @Test
    void testStream() {
        Map<Grade, List<Student>> map = students.stream()
                .collect(groupingBy(Student::getGrade));
        MapUtils.verbosePrint(System.out, "班级分组列表", map);
    }

    /**
     * select * from student s group by s.grade, s.gender
     */
    @Test
    void testStreamGroup() {
        Map<Grade, Map<Gender, List<Student>>> map = students.stream()
                .collect(groupingBy(
                        Student::getGrade, groupingBy(
                                Student::getGender)));
        MapUtils.verbosePrint(System.out, "班级性别分组列表", map);
    }

    /**
     * select s.grade, avg(s.score) from student s group by s.grade having avg(s.score) >= 60
     */
    @Test
    void testStreamGroupFilter() {
        Map<Grade, Double> map = students.stream()
                .collect(groupingBy(Student::getGrade, averagingInt(Student::getScore)));
        MapUtils.verbosePrint(System.out, "班级平均分列表", map);

        Map<Grade, Double> result1 = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 60)
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue));
        MapUtils.verbosePrint(System.out, "班级平均分及格的列表", result1);
        Map<Grade, Double> result2 = Maps.filterValues(map, e -> e < 60);
        MapUtils.verbosePrint(System.out, "班级平均分不及格列表", result2);

    }

    @Test
    void partitioning() {
        Map<Boolean, List<Student>> partitioningMap = students.stream()
                .collect(Collectors.partitioningBy(e -> e.getScore() >= 60));
        MapUtils.verbosePrint(System.out, "班级分数及格和不及格列表", partitioningMap);
    }

    /**
     * select count(score), sum(score), min(score), average(score), max(score) from student group by score;
     * 将同学的分数聚合统计
     */
    @Test
    void statisticScore() {
        IntSummaryStatistics statisticScore = students.stream().collect(summarizingInt(Student::getScore));
        System.out.println(statisticScore);
    }

    /**
     * 连接所有同学的名字
     */
    @Test
    void jointName() {
        String jointName = students.stream()
                .map(Student::getName)
                .collect(joining(",", "{", "}"));
        System.out.println(jointName);
    }

}
