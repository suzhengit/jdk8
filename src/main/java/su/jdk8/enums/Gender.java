package su.jdk8.enums;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public enum Gender {
    MALE("男"),
    FEMALE("女");
    String sex;

    Gender(String sex) {
        this.sex = sex;
    }
}
