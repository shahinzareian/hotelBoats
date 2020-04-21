//* ready function of ending trip
$(document).ready(function () {
    $("#searchEndBoat").click(getEnd);

});

//* asking the boat number to find tor ending trip
function getEnd() {
    var boatNumber = $('#boatSearchEndInput').val();

    $.get('api/reservations/search/' + boatNumber, function (reservations) {
        displayEndBoat(reservations);
    });
      $("#boatTable").show();
}
//* this function show the boat for ending as a table and also show the total price and guest information.
function displayEndBoat(reservations) {

    var boatEndTripContainer = $('#boatEndTripContainer');
    boatEndTripContainer.empty();
    $.each(reservations, function (index, reservation) {
    if(reservation.electricalBoat==null){
        $('#boatEndTripContainer').append(' <tr><td> ' + reservation.currentBoatNumber + '  </td><td> ' + reservation.boats.boatType + '  </td><td> ' + reservation.actualPrice + ' € per hour  </td><td> ' + reservation.startTrip + ' </td><td><button class="endTrip-button" reservationId="' + reservation.id + '">end trip</button></td></tr>');
   }else{
           $('#boatEndTripContainer').append(' <tr><td> ' + reservation.currentBoatNumber + '  </td><td> ' + reservation.electricalBoat.boatType + '  </td><td> ' + reservation.actualPrice + ' € per hour  </td><td> ' + reservation.startTrip + ' </td><td><button class="endTrip-button" reservationId="' + reservation.id + '">end trip</button></td></tr>');

   }
    });
    $('#boatEndTripContainer .endTrip-button').click(function () {
        var reservationIdEndTrip = $(this).attr('reservationId');
        $.ajax({
            url: "api/reservations/priceCalculation/" + reservationIdEndTrip,
            type: "get",
            success: function (priceResult) {
                $('#endTripPop').modal("show");

                document.getElementById("EndTripText").innerHTML =" the trip duration is: "+priceResult.hour+" : "+priceResult.min+ " and total price is:"+priceResult.total;

                document.getElementById("endTripGuestNameText").innerHTML ="name and last name: "+priceResult.name;
                document.getElementById("endTripGuestIdTypeText").innerHTML = "id type: "+ priceResult.idType;
                document.getElementById("endTripGuestIdNumberText").innerHTML ="id Number: "+ priceResult.idNumber;

                document.getElementById("endTripGuestMobileText").innerHTML ="mobile: "+priceResult.mobileNumber;
                $("#boatTable").hide();

            },
            error: function () {

                alert('something went wrong .');
            }
        });



    });
}
