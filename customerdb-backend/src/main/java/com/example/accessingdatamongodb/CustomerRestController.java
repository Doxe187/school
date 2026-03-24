package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRestController {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Aufgabe 2 – Customerliste laden
    @GetMapping("/people")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    // Aufgabe 4 - Person hinzufügen
    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    Customer newPerson(@RequestBody Customer newPerson) {
        return customerRepository.save(newPerson);
    }

    // Aufgabe 5 - Person bearbeiten
    @PutMapping("/people/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public ResponseEntity<Customer> replacePerson(@RequestBody Customer newPerson,
                                                  @PathVariable String id) {
        Optional<Customer> currentPerson = customerRepository.findById(id);
        if (currentPerson.isPresent()) {
            newPerson.setId(id);
            return ResponseEntity.status(HttpStatus.RESET_CONTENT)
                    .body(customerRepository.save(newPerson));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Aufgabe 6 - Person löschen
    @DeleteMapping("/people/{id}")
    ResponseEntity<Customer> deletePerson(@PathVariable String id) {
        Optional<Customer> currentPerson = customerRepository.findById(id);
        if (currentPerson.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok(currentPerson.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
