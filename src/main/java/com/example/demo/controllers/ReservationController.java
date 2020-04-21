package com.example.demo.controllers;

import com.example.demo.models.Reservation;
import com.example.demo.repositories.BoatsRepository;
import com.example.demo.repositories.ElectricalBoatRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.viewmodels.PriceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//*trip controller

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BoatsRepository boatsRepository;
    @Autowired
    private ElectricalBoatRepository electricalBoatRepository;

    //* trip getMapping
    @GetMapping
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();

    }

    //* trip getMapping per id
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationRepository.findById(id).get();
    }

    // trip postMapping
    @PostMapping
    public Long addReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation).getId();
    }

    //*trip deleteMapping
    @DeleteMapping("/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
    //*trip deleteMapping all

    @DeleteMapping("/deleteAll")
    void deleteAllReservation() {
        reservationRepository.deleteAll();
    }
//*trip putMapping

    @PutMapping("/{id}")

    public void updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        reservationRepository.save(reservation);
    }

    //* searching and showing boat that want to stop the trip
    @GetMapping("/search/{currentBoatNumber}")
    public List<Reservation> searchBoat(@PathVariable String currentBoatNumber) {


        List<Reservation> endTripBoat = new ArrayList<>(reservationRepository.findByCurrentBoatNumberAndTripEnded(currentBoatNumber, false));

        return endTripBoat;


    }
//* calculating and showing the price of ended trip

    @GetMapping("/priceCalculation/{id}")
    public PriceResult priceCalculation(@PathVariable Long id) {
        Reservation cal = reservationRepository.findById(id).get();
        LocalTime startTrip = cal.getStartTrip();
        LocalTime endTrip = LocalTime.now();
        int hour = 0;
        int min = 0;
        if (endTrip.getHour() == startTrip.getHour()) {
            min = endTrip.getMinute() - startTrip.getMinute();

        } else {
            min = (60 - startTrip.getMinute()) + endTrip.getMinute();
            hour = (endTrip.getHour() - startTrip.getHour()) - 1;
        }

        PriceResult priceResult = new PriceResult();
        LocalTime duration = LocalTime.of(hour, min);
        cal.setStopTrip(endTrip);
        cal.setDuring(duration);
        int total = (hour * cal.getActualPrice()) + ((cal.getActualPrice() * min) / 60);
        cal.setTotalPrice(total);
        cal.setTripEnded(true);


        reservationRepository.save(cal);
        if (cal.getElectricalBoat() == null) {
            cal.getBoats().setAvailable(true);
            boatsRepository.save(cal.getBoats());
        } else {
            cal.getElectricalBoat().setAvailable(true);
            LocalTime endTripAvailable = LocalTime.now();
            int elHour = endTripAvailable.getHour() + cal.getElectricalBoat().getChargingTime();
            int elMinute = endTripAvailable.getMinute();
            endTripAvailable = LocalTime.of(elHour, elMinute);
            LocalDate endTripDate = LocalDate.now();
            cal.getElectricalBoat().setAvailableTime(endTripAvailable);
            cal.getElectricalBoat().setAvailableDate(endTripDate);
            electricalBoatRepository.save(cal.getElectricalBoat());
        }

        priceResult.setIdNumber(cal.getGuest().getIdNumber());
        priceResult.setIdType(cal.getGuest().getIdType());
        priceResult.setMobileNumber(cal.getGuest().getMobileNumber());
        priceResult.setName(cal.getGuest().getName());
        priceResult.setHour(hour);
        priceResult.setMin(min);
        priceResult.setTotal(total);

        return priceResult;
    }


}





