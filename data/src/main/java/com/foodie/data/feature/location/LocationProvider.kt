package com.foodie.data.feature.location

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.foodie.data.annotations.UseDI
import com.foodie.data.annotations.UseSingleton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/**
 * @author Vipul Kumar; dated 24/12/18.
 */
@UseSingleton
@UseDI
class LocationProvider(val application: Application) {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationLiveData = MutableLiveData<Location>()

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(application.applicationContext)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let { locationLiveData.value = location }
            }
    }
}
