//* ready function of overviews
$(document).ready(function () {
    $('#boatOverviewButton').click(getBoats);
    $('#tripOverviewButton').click(displayTripOverview);

});
//* asking the user to know which kind of boat do you want to have bat over view
function getBoats() {
    $('#overviewBoatPop').modal("show");
    var boatTypeInput = $('#boatTypeSelect').val();
    if (boatTypeInput === "rowing") {
        $.get('api/overview/rowingBoatOverview', function (reservations) {
            displayRowingBoats(reservations);
        });
        displayBoatOverview(boatTypeInput);
    } else {
        $.get('api/overview/electricalBoatOverview', function (reservations) {
            displayElectricalBoats(reservations);
        });
        displayBoatOverview(boatTypeInput);
    }

}
//* showing the rowing boat overview as a table
function displayRowingBoats(reservations) {

    var boatContainer = $('#boatOverviewContainer');
    boatContainer.empty();

    $.each(reservations, function (index, reservation) {
        $('#boatOverviewContainer').append(' <tr><td> ' + reservation.boats.boatNumber + '  </td><td> ' + reservation.boats.boatType + '  </td><td> ' + reservation.boats.numberOfSeat + '  </td><td> ' + reservation.during + '  </td><td> ' + reservation.totalPrice + '€  </td></tr>');


    });
}
//* showing the electrical boat overview as a table
function displayElectricalBoats(reservations) {


    var boatContainer = $('#boatOverviewContainer');
    boatContainer.empty();

    $.each(reservations, function (index, reservation) {
        $('#boatOverviewContainer').append(' <tr><td> ' + reservation.electricalBoat.boatNumber + '  </td><td> ' + reservation.electricalBoat.boatType + '  </td><td> ' + reservation.electricalBoat.numberOfSeat + '  </td><td> ' + reservation.during + '  </td><td> ' + reservation.totalPrice + '€  </td></tr>');
    });
}


//* showing boats overviews as total income and total time

function displayBoatOverview(boatTypeInput) {

    $.ajax({
        url: "api/overview/totalBoatOverview/"+ boatTypeInput,
        type: "get",
        success: function (boatOverview) {
            document.getElementById("totalIncome").innerHTML = "   total income is: " + boatOverview.totalIncome;

            document.getElementById("totalTime").innerHTML = "   total time is: " + boatOverview.totalTime;

        },
        error: function () {

            alert('something went wrong .');
        }
    });
}

//* showing trip overview in total
function displayTripOverview() {

    $.ajax({
        url: "api/overview/totalTripOverview",
        type: "get",
        success: function (tripOverview) {
            $('#overviewTripPop').modal("show");
            document.getElementById("numberOfTripsEnded").innerHTML = "   Number of trips ended  is: " + tripOverview.numberOfTripsEnded;
            document.getElementById("numberOfOngoingTrips").innerHTML = "   Number of ongoing trips is: " + tripOverview.numberOfOngoingTrips;
            document.getElementById("averageDuration").innerHTML = "   Average duration of the ended trips is: " + tripOverview.averageDuration;
            document.getElementById("numberOfUsed").innerHTML = "   Number of used boats: " + tripOverview.numberOfUsed;
            document.getElementById("totalIncomeOverview").innerHTML = "   Total income: " + tripOverview.totalIncome;

        },
        error: function () {

            alert('you had no trip today .');
        }
    });
}