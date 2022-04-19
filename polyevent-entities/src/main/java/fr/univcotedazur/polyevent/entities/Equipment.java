package fr.univcotedazur.polyevent.entities;

import javax.persistence.*;
import java.util.Objects;

public class Equipment {

    private Long id;


    private EquipmentType type;

    private boolean isFunctioning;

    public Equipment() {
    }

    public Equipment(Long id, EquipmentType type, boolean isFunctioning) {
        this.id = id;
        this.type = type;
        this.isFunctioning = isFunctioning;
    }

    public Equipment(Long id, EquipmentType type) {
        this.id = id;
        this.type = type;
        this.isFunctioning = true;
    }

    public EquipmentType getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFunctioning() {
        return isFunctioning;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public void setFunctioning(boolean functioning) {
        isFunctioning = functioning;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipment eq = (Equipment) obj;
        return type == eq.type && isFunctioning == eq.isFunctioning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, isFunctioning);
    }
}
