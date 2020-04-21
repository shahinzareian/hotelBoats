//*get the rowing boat
function getBoats() {

    $.get('api/Boatss', function (boats) {
        displayBoats(boats);
    });
}
//* ready function of rowing boat page
$(document).ready(function () {
    getBoats();
    $('#createBoatButton').click(function () {
        $('#boatCreatePop').modal("show");
        $('#boatNumberInput').val('');
        $('#numberOfSeatInput').val('');
        $('#minimumPriceInput').val('');
    });
    $('#savePop').click(createBoat);
    $('#confirmDelete').click(removeBoat);
    $('#editPop').click(createEditBoat);

});
var boatIdDelete;
var boatIdEdit;
var boatAvailable;
var boatUsageNumber;

//*showing rowing boat as a table in the html page
function displayBoats(boats) {
    var boatContainer = $('#boatContainer');
    boatContainer.empty();
    $.each(boats, function (index, boat) {
        $('#boatContainer').append(' <tr><td> ' + boat.boatNumber + '  </td><td> ' + boat.boatType + '  </td><td> ' + boat.numberOfSeat + '  </td><td> ' + boat.available + '  </td><td> ' + boat.minPrice + '€ per hour  </td><td> ' + boat.usageNumber + '  </td><td><button class="remove-button" boatId="' + boat.id + '">delete</button></td><td><button class="edit-button" boatId="' + boat.id + '">edit</button></td></tr>');
    });
    $('#boatContainer .remove-button').click(function () {
        boatIdDelete = $(this).attr('boatId');

        $('#areYouSure').modal({ backdrop: 'static', keyboard: false });
    });
    $('#boatContainer .edit-button').click(function () {
        boatIdEdit = $(this).attr('boatId');
        $.get('api/Boatss/' + boatIdEdit, function (boat) {

            $('#boatEditPop').modal({ backdrop: 'static', keyboard: false });
            $('#boatNumberEdit').val(boat.boatNumber);

            $('#numberOfSeatEdit').val(boat.numberOfSeat);
            $('#minimumPriceEdit').val(boat.minPrice);
            boatAvailable=boat.available;
           boatUsageNumber=boat.usageNumber;


        });
    });


}

//* save the rowing boat that is edited

function editBoat(boat) {

    var jsonBoat = JSON.stringify(boat);
    $.ajax({
        url: 'api/Boatss/' + boatIdEdit,
        type: 'PUT',
        contentType: "application/json",
        data: jsonBoat,
        success: function () {
            alert('you edited the boat.');
            getBoats();
            $("#boatEditPop").modal("hide");
        },
        error: function () {
            $("#boatEditPop").modal("hide");
            alert('something went wrong.' + boatIdEdit);
        }
    });
}
//* put the field for the boat that want to edit in a variable
function createEditBoat() {
    var boatNumberVar = $('#boatNumberEdit').val();
    var numberOfSeatVar = $('#numberOfSeatEdit').val();
    var minPriceVar = $('#minimumPriceEdit').val();
    if (!boatNumberVar) {
        $("#noName").modal("show");
        return;
    }
    var boat = {
        boatNumber: boatNumberVar,
        boatType: "rowing",
        numberOfSeat: numberOfSeatVar,
        available:  boatAvailable,
        minPrice: minPriceVar,
        usageNumber:  boatUsageNumber
    };
    editBoat(boat);
}

//* by this function can delete a rowing boat from db
function removeBoat() {
    var boatId = boatIdDelete;
    $.ajax({
        url: 'api/Boatss/' + boatId,
        type: "DELETE",
        success: function () {
            getBoats();
            $("#areYouSure").modal("hide");

        },
        error: function () {
            $("#areYouSure").modal("hide");
            alert('You can not delete a boat who has a reservation. First delete the reservation..');
        }

    });
}

//* by this function can post a rowing boat in db
function postBoat(boat) {
    var jsonBoat = JSON.stringify(boat);
    $.ajax({
        url: "api/Boatss",
        type: "post",
        contentType: "application/json",
        data: jsonBoat,
        success: function () {
            alert('you created a new boat.');
            $('#boatCreatePop').modal("hide");
            getBoats();
        },
        error: function () {
            $('#boatCreatePop').modal("hide");
            alert('something went wrong.');
        }
    });
}

//* put the field for the boat that want to save in a variable

function createBoat() {
    var boatNumberVar = $('#boatNumberInput').val();
    var numberOfSeatVar = $('#numberOfSeatInput').val();
    var minPriceVar = $('#minimumPriceInput').val();
    if (!boatNumberVar) {
        $("#noName").modal("show");
        return;
    }
var number=0;
    var boat = {
        boatNumber: boatNumberVar,
        boatType: "rowing",
        numberOfSeat: numberOfSeatVar,
        available: true,
        minPrice: minPriceVar,
        usageNumber:  number

    };
    postBoat(boat);
}