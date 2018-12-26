package com.foodie.consumer.feature.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import com.foodie.consumer.R
import com.foodie.consumer.feature.common.BaseActivity
import com.foodie.consumer.feature.detail.VenueDetailFragment
import com.foodie.consumer.feature.favorites.FavoriteVenueFragment
import com.foodie.consumer.feature.nearby.HomeFragment
import com.foodie.consumer.ui.transition.SharedElementHelper
import com.foodie.data.feature.location.LocationProvider

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 */
class MainActivity : BaseActivity() {
    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val locationPermissionRequestCode = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkForPermission()
    }

    /** checks for permissions, if required */
    private fun checkForPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                locationPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, locationPermission
                )
            ) {
                showPermissionRationale()
            } else {
                requestPermissions()
            }
        } else {
            initUi()
        }
    }

    internal fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(locationPermission),
            locationPermissionRequestCode
        )
    }

    private fun initUi() {
        supportFragmentManager?.transaction {
            replace(R.id.fragmentContainer, HomeFragment())
        }
    }

    /**
     * handles permission result. currently it just closes the activity,
     * should handle denial gracefully.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            locationPermissionRequestCode -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    initUi()
                } else {
                    showPermissionRationale()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            when (requestCode) {
                LocationProvider.locationSettingsRequestCode -> {
                    showPermissionRationale()
                }
            }
        }
    }

    /** show dialog asking user to grant permission */
    private fun showPermissionRationale() {
        PermissionRationaleFragment().show(supportFragmentManager, "")
    }

    fun launchDetailFragment(
        venueId: String,
        sharedElements: SharedElementHelper?
    ) {
        supportFragmentManager?.transaction {
            add(
                R.id.fragmentContainer,
                VenueDetailFragment().apply { arguments = bundleOf("venueId" to venueId) })

            addToBackStack("")

            apply {
                if (sharedElements != null && !sharedElements.isEmpty()) {
                    sharedElements.applyToTransaction(this)
                } else {
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
            }
        }
    }

    fun launchFavoritesFragment() {
        supportFragmentManager?.transaction {
            add(
                R.id.fragmentContainer,
                FavoriteVenueFragment()
            )

            addToBackStack("")
        }
    }
}
