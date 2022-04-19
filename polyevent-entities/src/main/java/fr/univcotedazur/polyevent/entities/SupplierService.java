package fr.univcotedazur.polyevent.entities;

import java.util.Objects;

public class SupplierService {

    Long supplierId;

    Long serviceId;


    public SupplierService(Long supplierId, Long serviceId) {
        this.supplierId = supplierId;
        this.serviceId = serviceId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierService)) return false;
        SupplierService that = (SupplierService) o;
        return Objects.equals(supplierId, that.supplierId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, serviceId);
    }
}
