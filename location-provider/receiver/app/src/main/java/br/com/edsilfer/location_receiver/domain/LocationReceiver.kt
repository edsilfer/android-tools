package br.com.edsilfer.location_receiver.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.FusedLocationApi
import timber.log.Timber
import java.util.*

/**
 * Created by ferna on 6/3/2017.
 */

class LocationReceiver :
        BroadcastReceiver(),
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    companion object {
        private val ARG_EXTRA_LATITUDE = "latitude"
        private val ARG_EXTRA_LONGITUDE = "longitude"
        private val ARG_EXTRA_PROVIDER = "provider"
    }

    private var googleClient: GoogleApiClient? = null
    private var mockedLocation: Location? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            mockedLocation = readMockedLocation(intent)
            createGoogleAPIClient(context)
        } else {
            Timber.e("Unable to update received location because received context is null")
        }
    }

    private fun createGoogleAPIClient(context: Context) {
        if (googleClient == null) {
            googleClient = GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build()
        }

        if (!googleClient!!.isConnected) {
            googleClient!!.connect()
        }
    }

    /**
     * GOOGLE API CALLBACKS
     */
    override fun onConnected(extras: Bundle?) {
        if (mockedLocation != null) {
            FusedLocationApi.setMockMode(googleClient, true)
            FusedLocationApi.setMockLocation(googleClient, mockedLocation)
        }
    }

    private fun readMockedLocation(intent: Intent?): Location? {
        if (intent != null) {
            val provider = intent.getStringExtra(ARG_EXTRA_PROVIDER)
            val location = if (provider != null) Location(provider.toLowerCase()) else Location("network")
            location.latitude = intent.getFloatExtra(ARG_EXTRA_LATITUDE, 0f).toDouble()
            location.longitude = intent.getFloatExtra(ARG_EXTRA_LONGITUDE, 0f).toDouble()
            location.time = Date().time
            location.accuracy = 3.0f
            location.elapsedRealtimeNanos = System.nanoTime()

            return location
        }

        return null
    }

    override fun onConnectionSuspended(flag: Int) {
        Timber.w("Connection with Google Play suspended")
    }

    override fun onConnectionFailed(error: ConnectionResult) {
        Timber.e("Failed to connect with Google Play Services")
    }
}