//* ready function to show list of the trips
$(document).ready(function () {
    getTrips();


});
// get the trips
function getTrips() {

    $.get('api/reservations', function (trips) {
        displayTrips(trips);
    });
}
//* showing the list of trips as a table
function displayTrips(trips) {
    var tripContainer = $('#tripContainer');
    tripContainer.empty();
    $.each(trips, function (index, trip) {
        $('#tripContainer').append(' <tr><td> ' + trip.currentBoatNumber + '  </td><td> ' + trip.totalPrice + '  </td><td> ' + trip.startTrip + '  </td><td> ' + trip.stopTrip + ' </td><td> ' + trip.tripDate + '  </td><td> ' + trip.guest.name + '  </td><td> ' + trip.tripEnded + '  </td></tr>');
    });
    }

