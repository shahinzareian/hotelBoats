package com.example.demo.controllers;

import com.example.demo.models.Boats;
import com.example.demo.models.ElectricalBoat;
import com.example.demo.repositories.BoatsRepository;
import com.example.demo.repositories.ElectricalBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
//* electrical boat controller
@RestController
@RequestMapping("/api/electricalBoats")
public class ElectricalBoatController {
    @Autowired
    private ElectricalBoatRepository electricalBoatRepository;
  //* electrical boat getMapping
    @GetMapping
    public List<ElectricalBoat> getElectricalBoats(){return electricalBoatRepository.findAll();}
    //* electrical boat getMapping per id
    @GetMapping("/{id}")
    public ElectricalBoat geElectricalBoat(@PathVariable Long id) {
        return electricalBoatRepository.findById(id).get();
    }
//* electrical boat postMapping
    @PostMapping
    public void addElectricalBoat(@RequestBody ElectricalBoat electricalBoat) {
        electricalBoatRepository.save(electricalBoat);

    }
//* electrical boat deleteMapping
    @DeleteMapping("/{id}")
    void deleteElectricalBoat(@PathVariable Long id) {
        electricalBoatRepository.deleteById(id);
    }
//* electrical boat putMapping
    @PutMapping("/{id}")
    public void updateElectricalBoat(@PathVariable("id") Long id, @RequestBody ElectricalBoat electricalBoat) {
        electricalBoat.setId(id);
        electricalBoatRepository.save(electricalBoat);
    }
  //* search in electrical repository per 3 fields numberOfSeat and availability
    @GetMapping("/search/by/{numberOfSeat}/{boatType}")
    public List<ElectricalBoat>searchBoat (@PathVariable int numberOfSeat,@PathVariable String boatType){
        List<ElectricalBoat> availableBoats= new ArrayList<>();
        LocalTime startTrip = LocalTime.now();
        LocalDate startTripDate=LocalDate.now();
        for(ElectricalBoat electricalBoat:electricalBoatRepository.findByNumberOfSeatAndIsAvailableAndBoatType(numberOfSeat,true,boatType))
            if(electricalBoat.getAvailableDate().isBefore(startTripDate))
            { availableBoats.add(electricalBoat);}else
            if(electricalBoat.getAvailableTime().isBefore(startTrip))
                availableBoats.add(electricalBoat);

        return availableBoats;
    }
}
