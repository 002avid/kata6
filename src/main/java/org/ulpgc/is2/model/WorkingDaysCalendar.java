package org.ulpgc.is2.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import static java.time.DayOfWeek.*;

public class WorkingDaysCalendar {


    public Iterator<LocalDate> iteratorFor(LocalDate date){
        LocalDate current = date;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public LocalDate next() {
                var next = current.plusDays(1);
                while (!isWorkingDay(next)) next = next.plusDays(1);
                return next;
            }

            private boolean isWorkingDay(LocalDate date) {
                return workingDays.contains(date.getDayOfWeek());
            }
        };
    }
    private final Set<DayOfWeek> workingDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
}
