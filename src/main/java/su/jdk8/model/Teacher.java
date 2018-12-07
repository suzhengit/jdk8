package su.jdk8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import su.jdk8.enums.Course;
import su.jdk8.enums.Grade;

/**
 * @author 苏征
 * @date 2018-11-30
 */
@Data
@AllArgsConstructor
public class Teacher {
    private Integer id;

    private String name;

    private Grade grade;

    private Course course;
}
