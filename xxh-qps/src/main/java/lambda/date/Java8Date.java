package lambda.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class Java8Date {

    public static void main(String[] args) {
        LocalDate maxDate  =LocalDate.MAX;
        LocalDate minDate = LocalDate.MIN;
        LocalDate nowDate = LocalDate.now();
        System.out.println("max:"+maxDate+",minDate:"+minDate+",nowDate:"+nowDate);
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);

        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());

    }



}
