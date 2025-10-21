package ie.atu.week5lab3.service;

import ie.atu.week5lab3.controller.errorHandling.DuplicateException;
import ie.atu.week5lab3.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setup(){
        service = new PassengerService();
    }

    @Test
    void createThenFindByID(){
        Passenger p = Passenger.builder()
                .passengerID("P001")
                .name("Meike")
                .email("meike@gmail.com")
                .build();
        service.create(p);

        Optional<Passenger> passengerFound = service.findByID("P001");
        assertTrue(passengerFound.isPresent());
        assertEquals("Meike", passengerFound.get().getName());
    }

    @Test
    void duplicateIdThrowsException(){
        service.create(Passenger.builder()
                .passengerID("P002")
                .name("Mark")
                .email("Mark@gmail.com")
                .build());

        assertThrows(DuplicateException.class, () ->
            service.create(Passenger.builder()
                    .passengerID("P002")
                    .name("Mark")
                    .email("Mark@gmail.com")
                    .build()));
    }

    @Test
    void createThenUpdatePassenger(){
        Passenger oldPassenger = Passenger.builder()
                .passengerID("P001")
                .name("Meike")
                .email("m@gmail.com")
                .build();
        service.create(oldPassenger);

        Passenger newPassenger = Passenger.builder()
                .passengerID("P001")
                .name("John")
                .email("meike@gmail.com")
                .build();
        Passenger updatedPassenger = service.update(newPassenger);
        assertEquals("John", updatedPassenger.getName());
        assertEquals("meike@gmail.com", updatedPassenger.getEmail());
    }

    @Test
    void deleteThenFindByID(){
        Passenger oldPassenger = Passenger.builder()
                .passengerID("P001")
                .name("Mike")
                .email("m@gmail.com")
                .build();
        service.create(oldPassenger);

        service.delete(oldPassenger);
        Optional<Passenger> passengerFound = service.findByID("P001");
        assertTrue(passengerFound.isEmpty());
    }
}
