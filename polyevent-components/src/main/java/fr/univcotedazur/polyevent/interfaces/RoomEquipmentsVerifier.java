package fr.univcotedazur.polyevent.interfaces;

import fr.univcotedazur.polyevent.entities.Equipment;
import fr.univcotedazur.polyevent.entities.Room;
import java.util.List;

public interface RoomEquipmentsVerifier {
    List<Equipment> listAllEquipments(Long idRoom);
    List<Equipment> listFaultyEquipments(Long idRoom);
    List<Equipment> listFaultyEquipmentsOfTicket(Long title);
}
