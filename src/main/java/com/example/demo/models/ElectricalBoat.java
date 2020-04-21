package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
//* electrical boat model
@Entity
public class ElectricalBoat {
    @Id
    @GeneratedValue
    private Long Id;
    private String boatNumber;
    private int chargingTime;
    private LocalTime availableTime;
    private String boatType;
    private int numberOfSeat;
    private int minPrice;
    private boolean isAvailable;
    private LocalDate availableDate;
    private int usageNumber;

    public ElectricalBoat(Long id, String boatNumber, int chargingTime, LocalTime availableTime, String boatType, int numberOfSeat, int minPrice, boolean isAvailable, LocalDate availableDate, int usageNumber) {
        Id = id;
        this.boatNumber = boatNumber;
        this.chargingTime = chargingTime;
        this.availableTime = availableTime;
        this.boatType = boatType;
        this.numberOfSeat = numberOfSeat;
        this.minPrice = minPrice;
        this.isAvailable = isAvailable;
        this.availableDate = availableDate;
        this.usageNumber = usageNumber;
    }

    public ElectricalBoat() {
    }

    public int getUsageNumber() {
        return usageNumber;
    }

    public void setUsageNumber(int usageNumber) {
        this.usageNumber = usageNumber;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public String getBoatNumber() {
        return boatNumber;
    }

    public void setBoatNumber(String boatNumber) {
        this.boatNumber = boatNumber;
    }

    public int getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    public LocalTime getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(LocalTime availableTime) {
        this.availableTime = availableTime;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
