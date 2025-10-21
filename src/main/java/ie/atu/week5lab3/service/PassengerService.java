package ie.atu.week5lab3.service;

import ie.atu.week5lab3.controller.errorHandling.DuplicateException;
import ie.atu.week5lab3.controller.errorHandling.NotFoundException;
import ie.atu.week5lab3.model.Passenger;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final List<Passenger> store = new ArrayList<>();

    // Defensive Copy of list to protect against changes made (prevent corruption/mistakes)
    public List<Passenger> findAll(){
        return new ArrayList<>(store);
    }

    // Uses Optional, which allows us to test true/false cases and return regardless using Optionals methods
    public Optional<Passenger> findByID(String id){
        for (Passenger p : store){
            if(p.getPassengerID().equals(id)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    // Uses findByID method to ensure no duped ID's are entered
    public Passenger create(Passenger p){
        if(findByID(p.getPassengerID()).isPresent()){
            throw new DuplicateException("Passenger with ID: " + p.getPassengerID() + " already exists");
        }
        store.add(p);
        return p;
    }

    // Updates Passenger by creating new Passengegr Objects t o
    public Passenger update(Passenger p){
        Optional<Passenger> passengerFound = findByID(p.getPassengerID());
        if(passengerFound.isEmpty()){
            throw new NotFoundException("Passenger " + p.getPassengerID() + " doesn't exist");
        }
        Passenger updated = passengerFound.get();
        updated.setName(p.getName());
        updated.setEmail(p.getEmail());
        return updated;
    }

    public Passenger delete(Passenger p){
        if(findByID(p.getPassengerID()).isPresent()){
            store.remove(p);
            return p;
        }
        throw new NotFoundException("Passenger doesn't exist");
    }
}
