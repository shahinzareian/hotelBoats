package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//* rowing boat model

@Entity
public class Boats {
    @Id
    @GeneratedValue
    private Long Id;
    private String boatNumber;
    private String boatType;
    private int numberOfSeat;
    private int minPrice;
    private boolean isAvailable;
    private int usageNumber;

    public Boats(Long id, String boatNumber, String boatType, int numberOfSeat, int minPrice, boolean isAvailable, int usageNumber) {
        Id = id;
        this.boatNumber = boatNumber;
        this.boatType = boatType;
        this.numberOfSeat = numberOfSeat;
        this.minPrice = minPrice;
        this.isAvailable = isAvailable;
        this.usageNumber = usageNumber;
    }

    public Boats() {
    }

    public int getUsageNumber() {
        return usageNumber;
    }

    public void setUsageNumber(int usageNumber) {
        this.usageNumber = usageNumber;
    }

    public Long getId() {       return Id;   }
    public void setId(Long id) {        Id = id;    }

    public String getBoatNumber() {        return boatNumber;    }

    public void setBoatNumber(String boatNumber) {        this.boatNumber = boatNumber;    }

    public String getBoatType() {        return boatType;    }

    public void setBoatType(String boatType) {        this.boatType = boatType;    }

    public int getNumberOfSeat() {        return numberOfSeat;    }

    public void setNumberOfSeat(int numberOfSeat) {        this.numberOfSeat = numberOfSeat;    }

    public int getMinPrice() {        return minPrice;    }

    public void setMinPrice(int minPrice) {        this.minPrice = minPrice;    }


    public boolean isAvailable() {        return isAvailable;    }

    public void setAvailable(boolean available) {        isAvailable = available;    }
}
