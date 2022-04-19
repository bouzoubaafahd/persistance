package fr.univcotedazur.polyevent.entities;

import java.util.Objects;

public class RoomsReservation {


    private Long roomId;

    private TimeSlot timeSlot;

    public RoomsReservation(Long roomId, TimeSlot timeSlot) {
        this.roomId = roomId;
        this.timeSlot = timeSlot;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomsReservation)) return false;
        RoomsReservation that = (RoomsReservation) o;
        return roomId.equals(that.roomId) && timeSlot.equals(that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, timeSlot);
    }
}
