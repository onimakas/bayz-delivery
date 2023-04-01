package com.bayzdelivery.controller;

import com.bayzdelivery.jobs.DelayedDeliveryNotifier;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bayzdelivery.service.DeliveryService;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @Autowired
  PersonService personService;

  @Autowired
  DelayedDeliveryNotifier delayedNotifier;


  @PostMapping(path ="/api/delivery")
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody Delivery delivery) {
    Person customer = delivery.getCustomer();
    Person deliveryPerson = delivery.getDeliveryMan();
    //Customer and Delivery Man should not be same for the Delivery service
    if(customer.getId()==deliveryPerson.getId()||customer.getName().equals(deliveryPerson.getName())||customer.getEmail().equals(deliveryPerson.getEmail())||customer.getRegistrationNumber().equals(deliveryPerson.getRegistrationNumber())){

      System.out.println("============Customer and Deliver Man should be different");
      return ResponseEntity.badRequest().build();

    }
    System.out.println("================Delivery Object"+"===="+delivery.getId()+"====="+delivery);
    //To find out the delayed delivery time of 45 minutes
    delayedNotifier.checkDelayedDeliveries(delivery);


    //System.out.println("================Delivery Object person list inside Controller"+delivery.getPersonList());
    if(delivery.getPrice()!=null|| delivery.getDistance()!=null)
      delivery.setComission(delivery.getPrice()*0.05+delivery.getDistance()*0.5);
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @GetMapping(path = "/api/delivery/{id}")
  //Solved Functional bug for Get Request
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name="id",required=true)Long deliveryId){
    Delivery delivery = deliveryService.findById(deliveryId);
    if (delivery !=null)
      return ResponseEntity.ok(delivery);
    return ResponseEntity.notFound().build();
  }

  @GetMapping(path = "/api/delivery/{startTime}/{endTime}")
  //Added New End Point for Task 2
  public ResponseEntity<List<Delivery>> getTopThreeDeliveryByTimeRange(@PathVariable(name="startTime",required=true) Timestamp startTime, @PathVariable(name="endTime",required=true)Timestamp endTime){
    List<Delivery> deliveryList = deliveryService.findTopThreeDeliveriesByTimeRange(startTime,endTime);
    if(deliveryList!=null) {
      if (deliveryList.size() > 0) {
        System.out.print("inside delivery controller======");
        return ResponseEntity.ok(deliveryList);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping(path = "/api/delivery/averageComission/{startTime}/{endTime}")
  //Added New End Point for Task 2
  public ResponseEntity<Double> getAvgComissionByTimeRange(@PathVariable(name="startTime",required=true)Timestamp startTime,@PathVariable(name="endTime",required=true)Timestamp endTime){
    Double comission = deliveryService.getAvgComissionByTimeRange(startTime,endTime);

    if (comission!=null) {

      return ResponseEntity.ok(comission);
    }
    return ResponseEntity.notFound().build();
  }

}