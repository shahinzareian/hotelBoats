package com.example.demo.controllers;

import com.example.demo.models.Boats;
import com.example.demo.models.Guest;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.GuestRepository;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//*guest controller
@RestController
@RequestMapping("/api/guests")
public class guestController {
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @GetMapping
    public List<Guest> getGuests(){return guestRepository.findAll();}

    @PostMapping
    public Long addGuest(@RequestBody Guest guest) {
      Guest createGuest=  guestRepository.save(guest);
    return createGuest.getId();
    }

}
