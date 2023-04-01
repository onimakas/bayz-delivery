package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;
import java.sql.Timestamp;
import java.util.List;

public interface DeliveryService {

  public Delivery save(Delivery delivery);

  public Delivery findById(Long deliveryId);

  public List<Delivery> findTopThreeDeliveriesByTimeRange(Timestamp startTime,Timestamp endTime);
  public Double getAvgComissionByTimeRange(Timestamp startTime,Timestamp endTime);
}
