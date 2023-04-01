package com.bayzdelivery.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import com.bayzdelivery.model.Person;
import java.util.Optional;

@RestResource(exported=false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    @Query(name="select * from person p where p.email= :email or p.name= :name or p.registration_number= :registrationNumber", nativeQuery=true)
    Optional<Person> getByEmailOrNameOrRegistrationNumber(String email,String name,String registrationNumber);

}
