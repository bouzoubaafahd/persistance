package fr.univcotedazur.polyevent.interfaces;

import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.Supplier;

import java.util.ArrayList;

public interface ServicePriceCalculator {

       float calculatorSupplierPrice(ArrayList<Service> services);
}
