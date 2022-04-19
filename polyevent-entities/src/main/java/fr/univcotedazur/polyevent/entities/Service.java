package fr.univcotedazur.polyevent.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


public class Service {

    private Long id;
    private float price;
    private ServiceCategory serviceCategory;

    public Service() {
    }

    public Service(float price, ServiceCategory serviceCategory) {
        this.id = new Random().nextLong();
        this.price = price;
        this.serviceCategory = serviceCategory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Float.compare(service.price, price) == 0 && Objects.equals(id, service.id) && serviceCategory == service.serviceCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, serviceCategory);
    }
}
