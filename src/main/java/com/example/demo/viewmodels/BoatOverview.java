package com.example.demo.viewmodels;

import java.time.LocalTime;
//* overview boat model
public class BoatOverview {

    private int totalIncome;
    private String totalTime;
   String BoatNumber;
   int	NumberOfSeat;
   int TotalTimeEndedTrips;
   int	incomePerBoat;

    public String getBoatNumber() {
        return BoatNumber;
    }

    public void setBoatNumber(String boatNumber) {
        BoatNumber = boatNumber;
    }

    public int getNumberOfSeat() {
        return NumberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        NumberOfSeat = numberOfSeat;
    }

    public int getTotalTimeEndedTrips() {
        return TotalTimeEndedTrips;
    }

    public void setTotalTimeEndedTrips(int totalTimeEndedTrips) {
        TotalTimeEndedTrips = totalTimeEndedTrips;
    }

    public int getIncomePerBoat() {
        return incomePerBoat;
    }

    public void setIncomePerBoat(int incomePerBoat) {
        this.incomePerBoat = incomePerBoat;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
