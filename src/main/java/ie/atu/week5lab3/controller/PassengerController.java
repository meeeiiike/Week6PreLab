package ie.atu.week5lab3.controller;

import ie.atu.week5lab3.model.Passenger;
import ie.atu.week5lab3.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    // Constructor based Dependency Injection :D
    private final PassengerService service;
    public PassengerController(PassengerService service) {
        this.service = service;
    }

    // Get Request which forms ResponseEntity of List of Passenger, to call service instantiation so we can use the findAll method
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable String id) {
        Optional<Passenger> passengerFound = service.findByID(id);
        if (passengerFound.isPresent()) {
            return ResponseEntity.ok(passengerFound.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@Valid @RequestBody Passenger p){
        Passenger created = service.create(p);
        return ResponseEntity
                // Builds the URI, where we can pass the ID into the url "/{id}"
                .created(URI.create("/api/passengers" + created.getPassengerID())) // creates HTTP status code 201
                .body(created); // used object created to fill the response body
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@Valid @RequestBody Passenger p, @PathVariable String id){
        Optional<Passenger> passengerFound = service.findByID(p.getPassengerID());
        if (passengerFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Passenger updated = service.update(p);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> deletePassenger(@Valid @PathVariable String id){
        Optional<Passenger> passengerFound = service.findByID(id);
        if (passengerFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Passenger deleted = service.delete(passengerFound.get());
        return ResponseEntity.ok(deleted);
    }
}
