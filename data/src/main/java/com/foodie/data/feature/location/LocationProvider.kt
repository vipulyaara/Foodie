package com.foodie.data.feature.location

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.IntentSender
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.foodie.data.annotations.UseDI
import com.foodie.data.annotations.UseSingleton
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 24/12/18.
 *
 * Utility to fetch current location of a user.
 */
@UseSingleton
@UseDI
@SuppressLint("MissingPermission")
class LocationProvider(val application: Application) {
    companion object {
        const val locationSettingsRequestCode = 1001
    }

    private val logger: Logger by kodeinInstance.instance()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            p0?.lastLocation?.let { locationLiveData.postValue(it) }
        }
    }

    val locationLiveData = MutableLiveData<Location>()

    fun fetchCoarseLocation(activity: Activity) {
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(application.applicationContext)
        val locationRequest = createLocationRequest()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient =
            LocationServices.getSettingsClient(application.applicationContext)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }

        task.addOnFailureListener { exception ->
            if (locationLiveData.value == null) {
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(
                            activity,
                            locationSettingsRequestCode
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }
                }
            }
        }
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun createLocationRequest() = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}
