package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private int actualPrice;
    private int totalPrice;

    private LocalTime startTrip;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate tripDate;
    private LocalTime stopTrip;
    private String currentBoatNumber;
    private boolean tripEnded;
    private LocalTime during;
//* trip model
    @ManyToOne
    Boats boats;
    @ManyToOne
    Guest guest;
    @ManyToOne
    ElectricalBoat electricalBoat;

    public Reservation(Long id, int actualPrice, int totalPrice, LocalTime startTrip, LocalDate tripDate, LocalTime stopTrip, String currentBoatNumber, boolean tripEnded, LocalTime during, Boats boats, Guest guest, ElectricalBoat electricalBoat) {
        this.id = id;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.startTrip = startTrip;
        this.tripDate = tripDate;
        this.stopTrip = stopTrip;
        this.currentBoatNumber = currentBoatNumber;
        this.tripEnded = tripEnded;
        this.during = during;
        this.boats = boats;
        this.guest = guest;
        this.electricalBoat = electricalBoat;
    }

    public Reservation() {
    }

    public ElectricalBoat getElectricalBoat() {
        return electricalBoat;
    }

    public void setElectricalBoat(ElectricalBoat electricalBoat) {
        this.electricalBoat = electricalBoat;
    }

    public boolean isTripEnded() {
        return tripEnded;
    }

    public void setTripEnded(boolean tripEnded) {
        this.tripEnded = tripEnded;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public LocalTime getDuring() {
        return during;
    }

    public void setDuring(LocalTime during) {
        this.during = during;
    }

    public String getCurrentBoatNumber() {
        return currentBoatNumber;
    }

    public void setCurrentBoatNumber(String currentBoatNumber) {
        this.currentBoatNumber = currentBoatNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Boats getBoats() {
        return boats;
    }

    public void setBoats(Boats boats) {
        this.boats = boats;
    }


    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Long getId() {        return id;    }

    public void setId(Long id) {        this.id = id;    }

    public int getTotalPrice() {        return totalPrice;    }

    public void setTotalPrice(int totalPrice) {        this.totalPrice = totalPrice;    }

    public LocalTime getStartTrip() {        return startTrip;    }

    public void setStartTrip(LocalTime startTrip) {        this.startTrip = startTrip;    }

    public LocalTime getStopTrip() {        return stopTrip;    }

    public void setStopTrip(LocalTime stopTrip) {        this.stopTrip = stopTrip;    }


}

