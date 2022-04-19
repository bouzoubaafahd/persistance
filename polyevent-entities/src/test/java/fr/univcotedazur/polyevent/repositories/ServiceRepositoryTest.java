package fr.univcotedazur.polyevent.repositories;

import fr.univcotedazur.polyevent.configurations.PersistenceJpaConfig;
import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.ServiceCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


@SpringBootTest(classes = {PersistenceJpaConfig.class, ServiceRepository.class})
@Transactional
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

 //   @Test
 //   public void testFindByName() {
 //       Service service = new Service(110.0f, ServiceCategory.CLEANING);
 //       serviceRepository.saveAndFlush(service);
 //       Assertions.assertNotNull(service.getId());
 //   }
}
