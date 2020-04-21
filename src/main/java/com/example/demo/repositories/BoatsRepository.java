package com.example.demo.repositories;

import com.example.demo.models.Boats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//* rowing boat repository
public interface BoatsRepository extends JpaRepository<Boats,Long> {
    List<Boats> findByNumberOfSeatAndIsAvailable(int numberOfSeat,boolean isAvailable);
    List <Boats> findByNumberOfSeatAndIsAvailableAndBoatType( int numberOfSeat,boolean isAvailable,String boatType);

}
