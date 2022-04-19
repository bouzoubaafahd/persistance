package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Equipment;
import fr.univcotedazur.polyevent.interfaces.EquipmentReporter;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.RoomEquipmentsVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan("fr.univcotedazur")
public class RoomEquipments implements RoomEquipmentsVerifier, EquipmentReporter {

    @Autowired
    private InMemoryDatabase base;

    @Autowired
    private Finder finder;

    @Override
    public List<Equipment> listAllEquipments(Long idRoom) {
        return this.finder.findRoomById(idRoom).getListEquipments();
    }

    @Override
    public List<Equipment> listFaultyEquipments(Long idRoom) {
        List<Equipment> faultyEquipments = new ArrayList<Equipment>();
        for (Equipment eq : this.finder.findRoomById(idRoom).getListEquipments()) {
            if(!eq.isFunctioning()){
                faultyEquipments.add(eq);
            }
        }
        return faultyEquipments;
    }

    @Override
    public List<Equipment> listFaultyEquipmentsOfTicket(Long title) {
        return this.base.getTicketByTitle(title).getFaultyEquipment();
    }

    @Override
    public void addFaultyEquipment(Long idRoom, Long idEquipment) {
        this.finder.findRoomById(idRoom).getListEquipments().stream()
                .filter(e -> e.getId().equals(idEquipment)).findAny().get().setFunctioning(false);
    }


    // créer un nouveau ticket => y ajouter les equipements non fonctionnels d'une salle
    // => rend automatiquement ces équipments non fonctionnel dans la salle
}
