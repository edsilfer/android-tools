// CONSTANTS
var DEFAULT_ZOOM = 18
var BASE_URL = "http://localhost:8080/api/v1/"
var DEVICE_API = BASE_URL + "/device";
var LOCATION_API = BASE_URL + "/location";

// GLOBAL VARS
var doc = document.getElementById("demo");
var gMap;
var marker;

function initMap() {
  getCurrentLocation();
  gMap = new google.maps.Map(document.getElementById('map'))
  addOnClickListener ();
  getDevices ();
}

function getCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        doc.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function addOnClickListener () {
  google.maps.event.addListener(gMap, 'click', function(event) {
    addMarker(event.latLng, "Your're here")
    var automaticSend = $("#sendOnClick").prop('checked');

    if(automaticSend) {
        postLocation(event.latLng);
    }
  });
}

function onSendLocationClick() {
    postLocation(marker.position);
}

function showPosition(position) {
  var currentLocation = {lat:  position.coords.latitude, lng: position.coords.longitude};
  gMap.setZoom(DEFAULT_ZOOM);
  gMap.setCenter(currentLocation);
  addMarker(currentLocation, "You're here!");
}

function addMarker (location, _label) {
  if (marker != null) marker.setMap(null);
  marker = new google.maps.Marker({
    position: location,
    map: gMap,
    draggable: false,
    raiseOnDrag: true,
    labelContent: 'A',
    labelAnchor: new google.maps.Point(15, 65),
    labelClass: "labels", // the CSS class for the label
    labelInBackground: false
    //icon: pinSymbol('yellow')
  });

  var iw = new google.maps.InfoWindow({
    content: _label
  });

  google.maps.event.addListener(marker, "click", function(e) {
    iw.open(map, this);
  });
}

function pinSymbol(color) {
  return {
    path: 'M 0,0 C -2,-20 -10,-22 -10,-30 A 10,10 0 1,1 10,-30 C 10,-22 2,-20 0,0 z',
    fillColor: color,
    fillOpacity: 1,
    strokeColor: '#000',
    strokeWeight: 1,
    scale: 1
  };
}

/**
SERVICES
**/
function getDevices () {
     var request = $.ajax({
      url: DEVICE_API,
      type: "GET"
    });

    request.done(function(response) {
        var options = $("#devices");
        $.each(response.payload, function() {
            console.log(this.name);
            options.append($("<option />").text(this.name));
            $("#console").append(response.consoleOutput);
        });
    });

    request.fail(function(jqXHR, textStatus) {
        $("#console").append("Request failed: " + textStatus );
    });
}

function postLocation (location) {
    var device = $("#devices :selected").text();
    var provider = $("#providers :selected").text();

    if (device === "Choose a device") {
        var time = new Date();
        $("#console").append(time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds() + ": you must specify a device first!\n");
    } else {
        var request = $.ajax({
          url: LOCATION_API,
          type: "POST",
          data: {provider: provider, device: device, latitude : location.lat, longitude: location.lng}
        });

        request.done(function(response) {
            $("#console").append(response.consoleOutput);
        });

        request.fail(function(jqXHR, textStatus) {
            $("#console").append("Request failed: " + textStatus);
        });
    }
}