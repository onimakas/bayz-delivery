package com.bayzdelivery.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;
  @Autowired
  PersonRepository personRepository;

  public Delivery save(Delivery delivery) {
    Delivery deliveryObject=  deliveryRepository.findByStartTimeAndEndTime(delivery.getStartTime(),delivery.getEndTime());
    if(deliveryObject==null) {
      return deliveryRepository.save(delivery);
    }
    else {
      System.out.println("===============Multiple Orders in a given time period");
      return null;
    }


  }

  public Delivery findById(Long deliveryId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    return optionalDelivery.orElse(null);
  }
  public List<Delivery> findTopThreeDeliveriesByTimeRange(Timestamp startTime,Timestamp endTime) {
    System.out.print("inside delivery service implementation class======");
    List<Delivery> deliveryList = deliveryRepository.getTopThreeDeliveryByStartTimeAndEndTime(startTime,endTime);
    if (deliveryList.size()>0) {
      return deliveryList;
    }else return null;
  }

  public Double getAvgComissionByTimeRange(Timestamp startTime,Timestamp endTime) {
    return deliveryRepository.getAvgComissionByStartTimeAndEndTime(startTime,endTime);
  }


}
