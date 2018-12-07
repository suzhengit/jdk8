package su.jdk8.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 苏征
 * @date 2018-11-30
 */
@Getter
@AllArgsConstructor
public enum Course {
    CHINESE("语文"),
    MATH("数学"),
    ENGLISH("英语"),
    PHYSICS("物理"),
    CHEMISTRY("化学"),
    BIOLOGY("生物"),
    POLITICS("政治"),
    HISTORY("历史"),
    GEOGRAPHY("地理");

    private String course;
}
