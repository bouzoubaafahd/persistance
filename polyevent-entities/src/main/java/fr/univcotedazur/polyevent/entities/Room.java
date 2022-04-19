package fr.univcotedazur.polyevent.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Room {


    private Long id;

    private RoomType roomType;

    private int capacity;

    private List<Equipment> listEquipments;

    public Room() {
    }

    public Room(Long id){
        this.id = id;
        this.listEquipments = new ArrayList<Equipment>();
    }

    public Room(Long id, RoomType roomType, int capacity){
        this.id = id;
        this.roomType = roomType;
        this.capacity = capacity;
        this.listEquipments = new ArrayList<Equipment>();
    }

    public Room(Long id, RoomType roomType, int capacity, ArrayList<Equipment> equipments){
        this.id = id;
        this.roomType = roomType;
        this.capacity = capacity;
        this.listEquipments = equipments;
    }

    public Long getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Equipment> getListEquipments() {
        return listEquipments;
    }

    public void setListEquipments(List<Equipment> listEquipments) {
        this.listEquipments = listEquipments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType, capacity);
    }
}
