package com.example.demo.controllers;

import com.example.demo.models.Boats;
import com.example.demo.repositories.BoatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//*rowing boat controller*/
@RestController
@RequestMapping("/api/Boatss")
public class BoatControllers {
    @Autowired
    private BoatsRepository boatsRepository;
    //* rowing boat getMapping
    @GetMapping
    public List<Boats> getBoats(){return boatsRepository.findAll();}
    //* rowing boat getMapping per Id
    @GetMapping("/{id}")
      public Boats getBoat(@PathVariable Long id) {
        return boatsRepository.findById(id).get();
    }
//* rowing boat postMapping
    @PostMapping
    public void addBoat(@RequestBody Boats boats) {
        boatsRepository.save(boats);

    }
//* rowing boat deleteMapping
    @DeleteMapping("/{id}")
    void deleteBoat(@PathVariable Long id) {
        boatsRepository.deleteById(id);
    }
//* rowing boat putMapping
    @PutMapping("/{id}")
    public void updateBoat(@PathVariable("id") Long id, @RequestBody Boats boats) {
        boats.setId(id);
        boatsRepository.save(boats);
    }

//* searching in rowing boat repository per 3 fields numberOfSeat and availability and boatType
    @GetMapping("/search/by/{numberOfSeat}/{boatType}")
    public List<Boats>searchBoat (@PathVariable int numberOfSeat,@PathVariable String boatType){
        return boatsRepository.findByNumberOfSeatAndIsAvailableAndBoatType(numberOfSeat,true,boatType);
    }


}
