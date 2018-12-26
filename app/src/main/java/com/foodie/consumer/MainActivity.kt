package com.foodie.consumer

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import com.foodie.consumer.feature.common.BaseActivity
import com.foodie.consumer.feature.detail.VenueDetailFragment
import com.foodie.consumer.feature.nearby.HomeFragment
import com.foodie.consumer.feature.quickDetail.QuickDetailFragment
import com.foodie.consumer.ui.transition.SharedElementHelper

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager?.transaction {
            replace(R.id.fragmentContainer, HomeFragment())
        }
    }

    fun launchDetailFragment(
        venueId: String,
        sharedElements: SharedElementHelper
    ) {
        supportFragmentManager?.transaction {
            add(
                R.id.fragmentContainer,
                VenueDetailFragment().apply { arguments = bundleOf("venueId" to venueId) })

            addToBackStack("")

            apply {
                if (!sharedElements.isEmpty()) {
                    sharedElements.applyToTransaction(this)
                } else {
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
            }
        }
    }

    fun launchQuickDetailFragment(
        venueId: String,
        sharedElements: SharedElementHelper
    ) {
        supportFragmentManager?.transaction {
            add(
                R.id.fragmentContainer,
                QuickDetailFragment().apply { arguments = bundleOf("venueId" to venueId) })

            addToBackStack("")

            apply {
                if (!sharedElements.isEmpty()) {
                    sharedElements.applyToTransaction(this)
                } else {
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
            }
        }
    }
}