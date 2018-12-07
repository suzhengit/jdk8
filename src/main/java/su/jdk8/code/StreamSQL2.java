package su.jdk8.code;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import su.jdk8.enums.Course;
import su.jdk8.enums.Gender;
import su.jdk8.enums.Grade;
import su.jdk8.model.Student;
import su.jdk8.model.Teacher;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用Stream模拟多表关联
 * @author 苏征
 * @date 2018-11-30
 */
public class StreamSQL2 {
    private List<Student> students;
    private List<Teacher> teachers;



    @BeforeEach
    void init() {
        students = Arrays.asList(
                new Student(1, "小明", Gender.MALE, Grade.ONE, 84),
                new Student(2, "小红", Gender.FEMALE, Grade.TWO, 85),
                new Student(4, "小王", Gender.MALE, Grade.THREE, 46),
                new Student(5, "小丽", Gender.FEMALE, Grade.TWO, 55),
                new Student(6, "小青", Gender.FEMALE, Grade.FOUR, 74),
                new Student(8, "小刘", Gender.MALE, Grade.FOUR, 66),
                new Student(9, "小新", Gender.MALE, Grade.THREE, 59),
                new Student(10, "小慧", Gender.FEMALE, Grade.FIVE, 95));
        teachers = Arrays.asList(
                new Teacher(1, "关羽", Grade.ONE, Course.CHINESE),
                new Teacher(2, "张飞", Grade.ONE, Course.HISTORY),
                new Teacher(3, "赵云", Grade.TWO, Course.MATH),
                new Teacher(4, "马超", Grade.THREE, Course.BIOLOGY),
                new Teacher(5, "黄忠", Grade.FOUR, Course.CHINESE),
                new Teacher(6, "魏延", Grade.TWO, Course.CHINESE),
                new Teacher(7, "诸葛亮", Grade.THREE, Course.CHINESE),
                new Teacher(8, "庞统", Grade.TWO, Course.PHYSICS),
                new Teacher(9, "法正", Grade.TWO, Course.POLITICS),
                new Teacher(10, "姜维", Grade.FOUR, Course.ENGLISH));

    }

    /**
     * SELECT * FROM student a LEFT JOIN teacher b on a.grade = b.grade WHERE a.score >= 60
     */
    @Test
    void leftJoinStream() {
        List<Student> studentList = students.stream().peek(student -> {
            List<Teacher> teacherList = teachers.stream()
                    .filter(teacher -> student.getGrade() == teacher.getGrade())
                    .collect(Collectors.toList());
            student.setTeachers(teacherList);
        }).filter(StreamSQL2::filterPass).collect(Collectors.toList());
        studentList.forEach(System.out::println);
    }

    /**
     * SELECT * FROM student a, teacher b WHERE a.grade = b.grade AND a.score >= 60
     */
    @Test
    void innerJoinStream() {
        List<Student> studentList = students.stream().filter(student -> {
            boolean flag = false;
            List<Teacher> teacherList = Lists.newArrayList();
            for (Teacher teacher : teachers) {
                if (student.getGrade() == teacher.getGrade()) {
                    teacherList.add(teacher);
                    flag = true;
                }
            }
            student.setTeachers(teacherList);
            return flag;
        }).filter(StreamSQL2::filterPass).collect(Collectors.toList());
        studentList.forEach(System.out::println);
    }

    /**
     * select gender, sum(grade) from student a left join teacher b on a.grade = b.grade where a.score >= 60 group by a.grade having a.grade >= 90;
     */
    @Test
    void joinGroupStream() {
        Map<Grade, Double> map = students.stream().peek(student -> {
            List<Teacher> teacherList = Lists.newArrayList();
            teachers.forEach(teacher -> {
                if (student.getGrade() == teacher.getGrade()) {
                    teacherList.add(teacher);
                }
                student.setTeachers(teacherList);
            });
        }).filter(StreamSQL2::filterPass).collect(Collectors.groupingBy(Student::getGrade, Collectors.averagingDouble(Student::getScore)));
        Map<Grade, Double> result = map.entrySet()
                .stream()
                .filter(e -> e.getValue() >= 90)
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(result);
    }

    /**
     * 判断同学是否大于60分
     * @param student
     * @return
     */
    private static boolean filterPass(Student student) {
        return student.getScore() > 60;
    }
}
