package su.jdk8.enums;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public enum Grade {
    ONE("一班"),
    TWO("二班"),
    THREE("三班"),
    FOUR("四班");
    String desc;

    Grade(String desc) {
        this.desc = desc;
    }
}
