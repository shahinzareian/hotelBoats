package com.example.demo.controllers;

import com.example.demo.models.Boats;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.BoatsRepository;
import com.example.demo.repositories.ElectricalBoatRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.viewmodels.BoatOverview;
import com.example.demo.viewmodels.TripOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//* overviews controller
@RestController
@RequestMapping("/api/overview/")
public class OverviewController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ElectricalBoatRepository electricalBoatRepository;
    @Autowired
    private BoatsRepository boatsRepository;
//* calculate the trip overview
    @GetMapping("/totalTripOverview")
    public TripOverview getTripOverview() {
        LocalDate date=LocalDate.now();
        TripOverview tripOverview=new TripOverview();
        int totalIncome=0;
        int averageHour=0;
        int averageMins=0;
        int numberOfTripsEnded=0;
        int	numberOfOngoingTrips=0;
        String 	averageDuration;
        int	numberOfUsed=0;


        for (Reservation reservation: reservationRepository.findByTripDate(date)) {
            if(reservation.isTripEnded()){
                numberOfTripsEnded+=1;
                averageHour+=reservation.getDuring().getHour();
                averageMins+=reservation.getDuring().getMinute();
            }else
            {
                numberOfOngoingTrips+=1;
            }
            totalIncome+=reservation.getTotalPrice();
            numberOfUsed+=1;

        }
        averageHour+=((averageHour*60)+averageMins);
        int minimumMinute= 0;
        minimumMinute=averageHour/numberOfTripsEnded;

        averageHour= minimumMinute/60;
        averageMins=minimumMinute%60;
        tripOverview.setTotalIncome(totalIncome);
        tripOverview.setAverageDuration(averageHour+" hour and "+averageMins+" minutes");
        tripOverview.setNumberOfTripsEnded(numberOfTripsEnded);
        tripOverview.setNumberOfOngoingTrips(numberOfOngoingTrips);
        tripOverview.setNumberOfUsed(numberOfUsed);
        return tripOverview;
    }

  //* showing list of rowing boat overview as a table
    @GetMapping("/rowingBoatOverview")
    public List<Reservation> searchRowingBoatOverview(){
        LocalDate date=LocalDate.now();
        List<Reservation> overviewBoats = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findByTripDate(date)) {
            if (reservation.getElectricalBoat()==null) {
                overviewBoats.add(reservation);
            }
        }
        return overviewBoats;

    }
    //* showing list of electrical boat overview as a table
    @GetMapping("/electricalBoatOverview")
    public List<Reservation> searchElectricalBoatOverview(){
        LocalDate date=LocalDate.now();
        List<Reservation> overviewBoats = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findByTripDate(date)) {
            if (reservation.getBoats()==null) {
                overviewBoats.add(reservation);
            }
        }
        return overviewBoats;

    }
//* showing and calculating boats per type total time and total income

    @GetMapping("/totalBoatOverview/{boatType}")
    public BoatOverview getTotalBoatOverview(@PathVariable String boatType) {
        LocalDate date=LocalDate.now();
        BoatOverview boatOverview=new BoatOverview();
        int rowingTotalIncome=0;
        int rowingTotalHour=0;
        int rowingTotalMins=0;
        int electricalTotalIncome=0;
        int electricalTotalHour=0;
        int electricalTotalMins=0;
        for (Reservation reservation: reservationRepository.findByTripDate(date)) {
            if (reservation.getElectricalBoat()==null) {
            rowingTotalIncome+=reservation.getTotalPrice();
            if (reservation.isTripEnded()){
                rowingTotalHour+=reservation.getDuring().getHour();
                rowingTotalMins+=reservation.getDuring().getMinute();
            }}
            else{
                electricalTotalIncome+=reservation.getTotalPrice();
            }
                if (reservation.isTripEnded()){
                    electricalTotalHour+=reservation.getDuring().getHour();
                    electricalTotalMins+=reservation.getDuring().getMinute();

            }
        }

       if (boatType.equals("rowing")) {
           boatOverview.setTotalIncome(rowingTotalIncome);
           rowingTotalHour += rowingTotalMins / 60;
           rowingTotalMins %= 60;
           boatOverview.setTotalTime(rowingTotalHour + " hour and" + rowingTotalMins + " minutes ");
       }else
       {
           boatOverview.setTotalIncome(electricalTotalIncome);
           electricalTotalHour += electricalTotalMins / 60;
           electricalTotalMins %= 60;
           boatOverview.setTotalTime(electricalTotalHour + " hour and" + electricalTotalMins + " minutes ");
       }

        return boatOverview;
    }

}
