package lambda.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class NextWorkingDay implements TemporalAdjuster {

    public static void main(String[] args) {
        NextWorkingDay nextWorkingDay = new NextWorkingDay();
        LocalDate localDate = LocalDate.now();
        LocalDate nextLocalDate = localDate.with(nextWorkingDay);
        System.out.println("localDate:"+nextLocalDate);


//        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
//                temporal -> {
//                    DayOfWeek dow =
//                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//                    int dayToAdd = 1;
//                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
//                    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
//                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//                });

    }


    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }


}
