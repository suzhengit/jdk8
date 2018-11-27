package su.jdk8.code;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public class TimeDemo {

    @Test
    void localDate() {
        LocalDate today = LocalDate.now();
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的最后一天是" + lastDayOfThisMonth);
        // 取下一天：
        LocalDate firstDayOfNextMonth = lastDayOfThisMonth.plusDays(1);
        // 取2018年11月第一个周三：
        LocalDate firstMondayOf2018 = LocalDate.parse("2018-11-01")
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
        LocalDate date = LocalDate.of(2018, 11, 3);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String text = today.format(formatters);
        System.out.println(text);
        Period between = Period.between(date, today);
        System.out.println("日期相隔" + between.getDays() + "天");
    }

    @Test
    void localTime() {
        LocalTime time = LocalTime.now();
        LocalTime time1 = LocalTime.of(15, 43);

        System.out.println(time);
    }

    @Test
    void localDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }
}
