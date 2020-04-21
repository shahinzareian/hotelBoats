
//* function ready of starting trip
$(document).ready(function () {
    $("#searchBoat").click(getBoats);
    $('#rowingBoatTable').hide();
    $('#electricalBoatTable').hide();
    $('#startTrip').click(startTrip);
     $('#elStartTrip').click(ElectricStartTrip);


});

var boatNumberVar;

var boatNumberSeatVar;
var boarMinPriceVar;
var BoatTypeVar;
var boatUsageNumber;

//* find the boats per number of seat and type of the boat
function getBoats() {

    var numberOfSeatInput = $('#boatSearchInput').val();
     var boatTypeInput = $('#boatTypeSelect').val();

    if (boatTypeInput==="rowing"){
     $.get('api/Boatss/search/by/' + numberOfSeatInput + "/" + "rowing", function (boats) {
            displayRowingBoats(boats);
             $('#electricalBoatTable').hide();
             $('#rowingBoatTable').show();
     });
    }else{
     $.get('api/electricalBoats/search/by/' + numberOfSeatInput + "/" + "electrical", function (boats) {
                displayElectricalBoats(boats);
                 $('#rowingBoatTable').hide();
                 $('#electricalBoatTable').show();

            });
    }

//
}
var boatIdEdit
// showing the ready and available rowing  boats that found for starting as a table
function displayRowingBoats(boats) {
     var boatContainer = $('#rowingBoatSearchContainer');
     boatContainer.empty();
     $.each(boats, function (index, boat) {
         $('#rowingBoatSearchContainer').append(' <tr><td> ' + boat.boatNumber + '  </td><td> ' + boat.boatType + '  </td><td> ' + boat.numberOfSeat + '  </td><td> ' + boat.minPrice + '€ per hour   </td><td> ' + boat.usageNumber + '  </td><td><button class="reserve-button" boatId="' + boat.id + '">reserve</button></td></tr>');
     });
     $('#rowingBoatSearchContainer .reserve-button').click(function () {
         boatIdEdit = $(this).attr('boatId');

         $.get('api/Boatss/' + boatIdEdit, function (boat) {
             $('#boatGuestRegisterPop').modal({ backdrop: 'static', keyboard: false });

             boatNumberVar = boat.boatNumber;

             boatNumberSeatVar = boat.numberOfSeat;
             boarMinPriceVar = boat.minPrice;
             BoatTypeVar = boat.boatType;
             boatUsageNumber=boat.usageNumber+1;


         });
     });

 }
 var boatChargeTimeVar;
var availableTimeVar;
var electricalBoatIdEdit;

// showing the ready and available electrical  boats that found for starting as a table

 function displayElectricalBoats(boats) {
     var boatContainer = $('#electricalBoatContainer');
     boatContainer.empty();
     $.each(boats, function (index, boat) {
         $('#electricalBoatContainer').append(' <tr><td> ' + boat.boatNumber + '  </td><td> ' + boat.boatType + '  </td><td> ' + boat.numberOfSeat + '  </td><td> ' + boat.minPrice + '€ per hour   </td><td> ' + boat.usageNumber + '  </td><td><button class="reserve-button" boatId="' + boat.id + '">reserve</button></td></tr>');
     });
     $('#electricalBoatContainer .reserve-button').click(function () {
         electricalBoatIdEdit = $(this).attr('boatId');

         $.get('api/electricalBoats/' + electricalBoatIdEdit, function (boat) {
             $('#elBoatGuestRegisterPop').modal({ backdrop: 'static', keyboard: false });

             boatNumberVar = boat.boatNumber;

             boatNumberSeatVar = boat.numberOfSeat;
             boarMinPriceVar = boat.minPrice;
             BoatTypeVar = boat.boatType;
            boatChargeTimeVar= boat.chargingTime;
             availableTimeVar=null;
             boatUsageNumber=boat.usageNumber+1;

         });
     });

 }
//* starting the trip for rowing boat and saving guest information and trip information and boat information.
function startTrip() {
     var actualPriceInput = $('#actualPrice').val();
    var nameInput = $('#nameInput').val();
    var idTypeInput = $('#idTypeInput').val();
    var idNumberInput = $('#idNumberInput').val();
    var mobileNumberInput = $('#mobileNumberInput').val();

    if (!actualPriceInput||actualPriceInput<boarMinPriceVar) {
        $("#noActualPrice").modal("show");
        return;
    }
    var boat = {
        boatNumber: boatNumberVar,
        boatType: BoatTypeVar,
        numberOfSeat: boatNumberSeatVar,
        available: false,
        minPrice: boarMinPriceVar,
        usageNumber:  boatUsageNumber

    }
    var jsonBoat = JSON.stringify(boat);
    $.ajax({
        url: 'api/Boatss/' + boatIdEdit,
        type: 'PUT',
        contentType: "application/json",
        data: jsonBoat,
        success: function () {


            $('#rowingBoatTable').hide();
            $("#boatGuestRegisterPop").modal("hide");
        },
        error: function () {
            $("#boatEditPop").modal("hide");
            alert('something went wrong about boat.');
        }
    });
    var guest = {
        name: nameInput,
        idType: idTypeInput,
        idNumber: idNumberInput,
        mobileNumber: mobileNumberInput

    };
    var jsonGuest = JSON.stringify(guest);
    $.ajax({
        url: 'api/guests',
        type: 'post',
        contentType: "application/json",
        data: jsonGuest,
        success: function (guestId) {

            var date = new Date();
            var hh = date.getHours();
            var mm = date.getMinutes();
            hh = hh < 10 ? '0' + hh : hh;
            mm = mm < 10 ? '0' + mm : mm;

            curr_time = hh + ':' + mm;
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mo = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();

            today = dd + '-' + mo + '-' + yyyy;
            var reservation = {
                startTrip: curr_time,
                currentBoatNumber: boatNumberVar,
                actualPrice: actualPriceInput,
                tripDate:today,

                boats: {
                    id: boatIdEdit
                },
                guest: {
                    id: guestId
                }
            }

            var jsonReservation = JSON.stringify(reservation);
            $.ajax({
                url: "api/reservations",
                type: "post",
                contentType: "application/json",
                data: jsonReservation,
                success: function () {
                    alert('you created a trip.');
                        $('#nameInput').val('');
                         $('#idTypeInput').val('');
                       $('#idNumberInput').val('');
                         $('#mobileNumberInput').val('');


                },
                error: function () {

                    alert('something went wrong .');
                }
            });


        },
        error: function () {

            alert('something went wrong about guest.');
        }
    });


}
//* starting the trip for electrical boat and saving guest information and trip information and boat information.

function ElectricStartTrip(){
    var actualPriceInput = $('#elActualPrice').val();
    var nameInput = $('#elNameInput').val();
    var idTypeInput = $('#elIdTypeInput').val();
    var idNumberInput = $('#elIdNumberInput').val();
    var mobileNumberInput = $('#elMobileNumberInput').val();

    if (!actualPriceInput||actualPriceInput<boarMinPriceVar) {
        $("#noActualPrice").modal("show");
        return;
    }
    var boat = {
        boatNumber: boatNumberVar,
        boatType: BoatTypeVar,
        numberOfSeat: boatNumberSeatVar,
        available: false,
        minPrice: boarMinPriceVar,
       chargingTime: boatChargeTimeVar,
        availableTime:null,
        availableDate:null,
        usageNumber:  boatUsageNumber

    }
    var jsonBoat = JSON.stringify(boat);
    $.ajax({
        url: 'api/electricalBoats/' + electricalBoatIdEdit,
        type: 'PUT',
        contentType: "application/json",
        data: jsonBoat,
        success: function () {


            $('#electricalBoatTable').hide();
            $("#elBoatGuestRegisterPop").modal("hide");
        },
        error: function () {
            $("#boatEditPop").modal("hide");
            alert('something went wrong about boat.');
        }
    });
    var guest = {
        name: nameInput,
        idType: idTypeInput,
        idNumber: idNumberInput,
        mobileNumber: mobileNumberInput

    };
    var jsonGuest = JSON.stringify(guest);
    $.ajax({
        url: 'api/guests',
        type: 'post',
        contentType: "application/json",
        data: jsonGuest,
        success: function (guestId) {

            var date = new Date();
            var hh = date.getHours();
            var mm = date.getMinutes();
            hh = hh < 10 ? '0' + hh : hh;
            mm = mm < 10 ? '0' + mm : mm;

            curr_time = hh + ':' + mm;
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mo = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();

            today = dd + '-' + mo + '-' + yyyy;
            var reservation = {
                startTrip: curr_time,
                currentBoatNumber: boatNumberVar,
                actualPrice: actualPriceInput,
                tripDate:today,

                electricalBoat: {
                    id: electricalBoatIdEdit
                },
                guest: {
                    id: guestId
                }
            }

            var jsonReservation = JSON.stringify(reservation);
            $.ajax({
                url: "api/reservations",
                type: "post",
                contentType: "application/json",
                data: jsonReservation,
                success: function () {
                    alert('you created a trip.');
                     $('#elActualPrice').val('');
                     $('#elNameInput').val('');
                     $('#elIdTypeInput').val('');
                     $('#elIdNumberInput').val('');
                    $('#elMobileNumberInput').val('');

                },
                error: function () {

                    alert('something went wrong .');
                }
            });


        },
        error: function () {

            alert('something went wrong about guest.');
        }
    });
}