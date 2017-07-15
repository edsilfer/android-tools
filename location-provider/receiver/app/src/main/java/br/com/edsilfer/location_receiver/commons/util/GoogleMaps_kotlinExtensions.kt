package br.com.edsilfer.location_receiver.commons.util

import android.content.Context
import android.location.Location
import android.support.v4.content.ContextCompat
import br.com.edsilfer.location_receiver.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

/**
 * Created by ferna on 5/13/2017.
 */
private val ARG_DEFAULT_ZOOM = 12F
private val ARG_DEFAULT_CIRCLE_RADIUS = 30

private val ERR_001 = "Unable to move camera because Location object is null"
private val ERR_002 = "Unable to place marker because Location object is null"

fun GoogleMap.mark(
        label: String = "",
        latitude: Double = -1.toDouble(),
        longitude: Double = -1.toDouble(),
        location: Location? = null,
        latLng: LatLng? = null,
        zoom: Float = ARG_DEFAULT_ZOOM,
        animate: Boolean = true,
        clearPreviousMarks: Boolean = true
) {
    var loc: LatLng? = null

    if (latitude != -1.toDouble() && longitude != -1.toDouble()) {
        loc = LatLng(latitude, longitude)
    } else if (location != null) {
        loc = LatLng(location.latitude, location.longitude)
    } else if (latLng != null) {
        loc = latLng
    }

    if (clearPreviousMarks) {
        clear()
    }

    if (loc != null) {
        addMarker(MarkerOptions().position(loc).title(label))

        if (animate) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
        } else {
            moveCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
        }
    } else {
        Timber.e(ERR_001)
    }
}

fun GoogleMap.move(
        latitude: Double = -1.toDouble(),
        longitude: Double = -1.toDouble(),
        location: Location? = null,
        latLng: LatLng? = null,
        zoom: Float = ARG_DEFAULT_ZOOM,
        animate: Boolean = true
) {
    var loc: LatLng? = null

    if (latitude != -1.toDouble() && longitude != -1.toDouble()) {
        loc = LatLng(latitude, longitude)
    } else if (location != null) {
        loc = LatLng(location.latitude, location.longitude)
    } else if (latLng != null) {
        loc = latLng
    }

    if (loc != null) {
        if (animate) {
            animateCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
        } else {
            moveCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
        }
    } else {
        Timber.e(ERR_001)
    }
}

fun GoogleMap.makeCircle(
        context: Context,
        radius: Int = ARG_DEFAULT_CIRCLE_RADIUS,
        borderColor: Int = R.color.color_circle_border,
        fillColor: Int = R.color.color_circle_fill,
        latitude: Double = -1.toDouble(),
        longitude: Double = -1.toDouble(),
        location: Location? = null,
        latLng: LatLng? = null,
        clearPreviousCircles: Boolean = true,
        zoom: Float = ARG_DEFAULT_ZOOM,
        animate: Boolean = true,
        moveCamera: Boolean = true
): Circle? {
    var loc: LatLng? = null

    if (latitude != -1.toDouble() && longitude != -1.toDouble()) {
        loc = LatLng(latitude, longitude)
    } else if (location != null) {
        loc = LatLng(location.latitude, location.longitude)
    } else if (latLng != null) {
        loc = latLng
    }

    if (loc != null) {

        if (clearPreviousCircles) {
            clear()
        }

        val circleOptions = CircleOptions()
        circleOptions.center(loc)
        circleOptions.radius(radius.toDouble())
        circleOptions.strokeColor(ContextCompat.getColor(context, borderColor))
        circleOptions.fillColor(ContextCompat.getColor(context, fillColor))
        circleOptions.strokeWidth(1f)

        if (moveCamera) {
            if (animate) {
                animateCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
            } else {
                moveCamera(CameraUpdateFactory.newLatLngZoom(loc, zoom))
            }
        }

        return addCircle(circleOptions)
    } else {
        Timber.e(ERR_001)
    }

    return null
}
