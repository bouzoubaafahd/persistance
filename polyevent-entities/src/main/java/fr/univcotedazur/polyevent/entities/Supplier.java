package fr.univcotedazur.polyevent.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.*;

public class Supplier {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;


    private ArrayList<Service> services;

    public Supplier() {
        this.services = new ArrayList<>();
    }

    public Supplier(String name, String address, String phoneNumber, String email) {
        this.id = new Random().nextLong();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.services = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(name, supplier.name) && Objects.equals(address, supplier.address) && Objects.equals(phoneNumber, supplier.phoneNumber) && Objects.equals(email, supplier.email) && Objects.equals(services, supplier.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber, email, services);
    }
}
