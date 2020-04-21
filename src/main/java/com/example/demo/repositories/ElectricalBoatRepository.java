package com.example.demo.repositories;


import com.example.demo.models.ElectricalBoat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//* electrical boat repository
public interface ElectricalBoatRepository extends JpaRepository<ElectricalBoat,Long> {
    List<ElectricalBoat> findByNumberOfSeatAndIsAvailableAndBoatType(int numberOfSeat, boolean isAvailable, String boatType);
}
