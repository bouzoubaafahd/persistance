package fr.univcotedazur.polyevent.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class TimeSlot {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final Date endDate;

    public TimeSlot(Date startTime, Date endTime) {
        this.startDate = startTime;
        this.endDate = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(startDate, timeSlot.startDate) && Objects.equals(endDate, timeSlot.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
