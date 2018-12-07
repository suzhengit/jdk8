package su.jdk8.code;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 苏征
 * @date 2018-11-27
 */
public class DateTimeApi {

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
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println("标准解析日期格式为:" + today.format(localDateFormatter));
        System.out.println("自定义解析日期格式为:"+ today.format(formatters));
        Period betweenDay = Period.between(date, today);  //用于计算两个“日期”间隔
        long differDay = ChronoUnit.DAYS.between(date, today);  //ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒。
        System.out.println("日期相隔" + betweenDay.getDays() + "天");
        System.out.println("日期相隔" + differDay + "天");
    }

    @Test
    void localTime() {
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.of(15, 43);
        System.out.println(start);
        Duration duration = Duration.between(start, end); //用于计算两个“时间”间隔
        System.out.println(duration.getSeconds());
    }

    @Test
    void localDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        //LocalDateTime转Date
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        //Date转LocalDateTime
        LocalDateTime dateTime0 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        //LocalDateTime自定义格式
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    void instant()  {
        Instant start = Instant.now(); //0区时间
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)); //北京时间
        Date date = new Date();
        Instant instant = date.toInstant();
        ZonedDateTime time = start.atZone(ZoneOffset.ofHours(8));
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
    }
}
