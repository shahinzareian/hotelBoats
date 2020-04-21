package com.example.demo.viewmodels;
//* trip overview model
public class TripOverview {
    int numberOfTripsEnded;
    int	numberOfOngoingTrips;
    String 	averageDuration;
    int	numberOfUsed;
    int	totalIncome;

    public String getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(String averageDuration) {
        this.averageDuration = averageDuration;
    }

    public int getNumberOfTripsEnded() {
        return numberOfTripsEnded;
    }

    public void setNumberOfTripsEnded(int numberOfTripsEnded) {
        this.numberOfTripsEnded = numberOfTripsEnded;
    }

    public int getNumberOfOngoingTrips() {
        return numberOfOngoingTrips;
    }

    public void setNumberOfOngoingTrips(int numberOfOngoingTrips) {
        this.numberOfOngoingTrips = numberOfOngoingTrips;
    }



    public int getNumberOfUsed() {
        return numberOfUsed;
    }

    public void setNumberOfUsed(int numberOfUsed) {
        this.numberOfUsed = numberOfUsed;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }
}
