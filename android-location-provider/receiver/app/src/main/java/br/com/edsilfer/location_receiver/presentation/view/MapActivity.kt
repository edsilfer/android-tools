package br.com.edsilfer.location_receiver.presentation.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.FragmentActivity
import android.support.v4.content.PermissionChecker
import br.com.edsilfer.geolocation.core.Event
import br.com.edsilfer.geolocation.core.EventBus
import br.com.edsilfer.geolocation.domain.GeolocationMonitor
import br.com.edsilfer.geolocation.domain.GeolocationMonitorBinder
import br.com.edsilfer.location_receiver.R
import br.com.edsilfer.location_receiver.commons.util.move
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import io.reactivex.disposables.Disposable

class MapActivity : FragmentActivity(), OnMapReadyCallback {

    companion object {
        private val ARG_REQUEST_GEOLOCATION_PERMISSION_FOR_OPENING_MAPS_ACTIVITY = 98
    }

    private var mMap: GoogleMap? = null
    private var service: GeolocationMonitorBinder? = null
    private var isBound = false
    private var observeLocation: Disposable? = null

    /**
     * LIFECYCLE
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(ACCESS_COARSE_LOCATION) + checkSelfPermission(ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION),
                        ARG_REQUEST_GEOLOCATION_PERMISSION_FOR_OPENING_MAPS_ACTIVITY
                )
            } else {
                init()
            }
        }
    }

    private fun init() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        bindService(GeolocationMonitor.getIntent(this), serviceConnectionManager, BIND_AUTO_CREATE)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onStart() {
        super.onStart()
        observeLocation = onLocationChanged()
    }

    override fun onStop() {
        super.onStop()
        observeLocation?.dispose()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            ARG_REQUEST_GEOLOCATION_PERMISSION_FOR_OPENING_MAPS_ACTIVITY -> {
                if (grantResults.filter { result -> result != PermissionChecker.PERMISSION_GRANTED }.isNotEmpty()) {
                    init()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * LISTENERS
     */
    private fun onLocationChanged(): Disposable? {
        return EventBus.events.subscribe {
            payload ->
            if (payload is Event) {
                service?.startLocationListener()
            } else if (payload is Location) {
                mMap?.move(location = payload, zoom = 18f)
            }
        }
    }

    private val serviceConnectionManager = object : android.content.ServiceConnection {
        override fun onServiceConnected(className: android.content.ComponentName, service: IBinder) {
            this@MapActivity.service = service as GeolocationMonitorBinder
            isBound = true
        }

        override fun onServiceDisconnected(arg0: android.content.ComponentName) {
            isBound = false
        }
    }
}
