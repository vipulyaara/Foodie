package com.foodie.consumer.feature.nearby

import android.os.Bundle
import android.view.View
import androidx.fragment.app.transaction
import com.foodie.consumer.R
import com.foodie.consumer.databinding.FragmentHomeBinding
import com.foodie.consumer.feature.common.DataBindingFragment

/**
 * @author Vipul Kumar; dated 25/12/18.
 */
class HomeFragment : DataBindingFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.transaction {
            replace(R.id.fragmentContainer, NearbyVenueFragment())
        }
    }
}
