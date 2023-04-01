package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.sql.Timestamp;
import java.util.List;

@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    @Query(name="select * from delivery d where d.start_time >= :startTime and d.end_time <= :endTime", nativeQuery=true)
    Delivery findByStartTimeAndEndTime(Timestamp startTime, Timestamp endTime);

    @Query(name="select * from delivery d where d.start_time >= :startTime and d.end_time <= :endTime order by d.comission desc limit 3", nativeQuery=true)
    List<Delivery> getTopThreeDeliveryByStartTimeAndEndTime(Timestamp startTime, Timestamp endTime);

    @Query(name="select AVG(comission) from delivery d where d.start_time >= :startTime and d.end_time <= :endTime", nativeQuery=true)
    Double getAvgComissionByStartTimeAndEndTime(Timestamp startTime, Timestamp endTime);

}
