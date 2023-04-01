package com.bayzdelivery.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{

  private static final long serialVersionUID = 123765351514001L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @Column(name = "start_time")
  Timestamp startTime;

  @NotNull
  @Column(name = "end_time")
  Timestamp endTime;

  @Column(name = "distance")
  Double distance;

  @Column(name = "price")
  Double price;

  @Column(name = "comission")
  Double comission;

  @ManyToOne
  @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
  Person deliveryMan;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  Person customer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getComission() {
    return comission;
  }

  public void setComission(double d) {
    this.comission = d;
  }

  public Person getDeliveryMan() {
    return deliveryMan;
  }

  public void setDeliveryMan(Person deliveryMan) {
    this.deliveryMan = deliveryMan;
  }

  public Person getCustomer() {
    return customer;
  }

  public void setCustomer(Person customer) {
    this.customer = customer;
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((distance == null) ? 0 : distance.hashCode());
    result = prime * result + ((deliveryMan == null) ? 0 : deliveryMan.hashCode());
    result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((customer == null) ? 0 : customer.hashCode());
    result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Delivery other = (Delivery) obj;
    if (distance == null) {
      if (other.distance != null)
        return false;
    } else if (!distance.equals(other.distance))
      return false;
    if (deliveryMan == null) {
      if (other.deliveryMan != null)
        return false;
    } else if (!deliveryMan.equals(other.deliveryMan))
      return false;
    if (endTime == null) {
      if (other.endTime != null)
        return false;
    } else if (!endTime.equals(other.endTime))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (customer == null) {
      if (other.customer != null)
        return false;
    } else if (!customer.equals(other.customer))
      return false;
    if (startTime == null) {
      if (other.startTime != null)
        return false;
    } else if (!startTime.equals(other.startTime))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Delivery [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", distance=" + distance + ", deliveryMan=" + deliveryMan + ", customer=" + customer + "]";
  }



}
