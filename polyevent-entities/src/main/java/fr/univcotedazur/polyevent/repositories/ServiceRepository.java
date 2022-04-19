package fr.univcotedazur.polyevent.repositories;

import fr.univcotedazur.polyevent.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
