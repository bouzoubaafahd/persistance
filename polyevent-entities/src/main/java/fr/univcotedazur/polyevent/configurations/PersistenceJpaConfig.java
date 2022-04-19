package fr.univcotedazur.polyevent.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("fr.univcotedazur.polyevent.components")
@EnableJpaRepositories("fr.univcotedazur.polyevent.repositories")
@EntityScan("fr.univcotedazur.polyevent.entities")
@EnableAutoConfiguration
@PropertySource("classpath:persistence-h2.properties")
public class PersistenceJpaConfig {

}
