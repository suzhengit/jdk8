package su.jdk8.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 苏征
 * @date 2018-11-27
 */
@Getter
@AllArgsConstructor
public enum Gender {

    MALE("男"),
    FEMALE("女");

   private String sex;

}
