package su.jdk8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import su.jdk8.enums.Gender;
import su.jdk8.enums.Grade;

import java.util.List;

/**
 * @author 苏征
 * @date 2018-11-27
 */
@Data
@NoArgsConstructor

public class Student {

    private Integer id;

    private String name;
    /**
     * 性别
     */
    private Gender gender;
    /**
     * 班级
     */
    private Grade grade;
    /**
     * 分数
     */
    private Integer score;

    /**
     * 班级老师
     */
    private List<Teacher> teachers;

    public Student(Integer id, String name, Gender gender, Grade grade, Integer score) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }


}
