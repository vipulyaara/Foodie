package com.foodie.consumer

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.transaction
import com.foodie.consumer.feature.common.BaseActivity
import com.foodie.consumer.feature.detail.VenueDetailFragment
import com.foodie.consumer.feature.home.NearbyVenueFragment

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 * Main activity for the app. We are following single activity model.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager?.transaction {
            replace(R.id.fragmentContainer, NearbyVenueFragment())
        }
    }

    fun launchDetailFragment(venueId: String) {
        supportFragmentManager?.transaction {
            addToBackStack("")
            replace(
                R.id.fragmentContainer,
                VenueDetailFragment().apply { arguments = bundleOf("venueId" to venueId) })
        }
    }
}
