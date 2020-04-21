package com.example.demo.repositories;

import com.example.demo.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//* trip repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

   List<Reservation> findByCurrentBoatNumberAndTripEnded(String currentBoatNumber,boolean tripEnded);
   List<Reservation> findByTripDate(LocalDate tripDate);


}
