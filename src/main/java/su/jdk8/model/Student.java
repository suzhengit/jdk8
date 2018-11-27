package su.jdk8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import su.jdk8.enums.Gender;
import su.jdk8.enums.Grade;

/**
 * @author 苏征
 * @date 2018-11-27
 */
@Data
@AllArgsConstructor
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

}
