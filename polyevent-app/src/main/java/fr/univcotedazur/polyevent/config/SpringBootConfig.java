package fr.univcotedazur.polyevent.config;



import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("fr.univcotedazur.polyevent.components")
@EntityScan("fr.univcotedazur.polyevent.entities")
public class SpringBootConfig {

}

