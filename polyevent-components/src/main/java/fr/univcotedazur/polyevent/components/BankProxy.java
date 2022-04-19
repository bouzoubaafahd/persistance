package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.interfaces.payment.Bank;
import fr.univcotedazur.polyevent.components.externaldto.PaymentDto;
import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ComponentScan("fr.univcotedazur")
public class BankProxy implements Bank {

    private static final Logger LOG = LoggerFactory.getLogger(BankProxy.class);

    @Autowired
    private Finder finder;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:9090")
    private String bankHostandPort;
    @Value("12E235645743")
    private  String creditCard;


    @Override
    public boolean payAllValue(Organizer organizer, double value) {
        int result = restTemplate.postForObject(bankHostandPort+"/mailbox",
                new PaymentDto(organizer.getCreditCard(), value),
                Integer.class);
        return result >0 ;
    }

    @Override
    public boolean pay(Long OrganizerId, double value) {

        LOG.info("POLYEVENT:BankProxy-Component: calling external system...");


        int result = restTemplate.postForObject(bankHostandPort+"/mailbox",
                new PaymentDto(finder.findOrganizerById(OrganizerId).getCreditCard(), value),
                Integer.class);
        return result >0 ;
    }


}
