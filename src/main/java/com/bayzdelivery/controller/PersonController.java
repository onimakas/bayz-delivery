package com.bayzdelivery.controller;

import java.util.List;

import com.bayzdelivery.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bayzdelivery.service.PersonService;

@RestController
public class PersonController {

  @Autowired
  PersonService personService;

  @PostMapping(path = "/api/person")
  public ResponseEntity<Person> register(@RequestBody Person p) {
    if(!personService.findByEmailNameReg(p.getEmail(),p.getName(),p.getRegistrationNumber())) {
      return ResponseEntity.ok(personService.save(p));
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping(path = "/api/person")
  public ResponseEntity<List<Person>> getAllPersons() {
    return ResponseEntity.ok(personService.getAll());
  }

//Solved Functional bug for Get Request

  @GetMapping(path = "/api/person/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable(name="id", required=true)Long personId) {
    Person person = personService.findById(personId);
    if (person != null) {
      return ResponseEntity.ok(person);
    }
    return ResponseEntity.notFound().build();
  }

}
