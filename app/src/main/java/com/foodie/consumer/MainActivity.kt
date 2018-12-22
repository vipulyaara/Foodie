package com.foodie.consumer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.foodie.consumer.feature.home.NearbyVenueFragment

/**
 * @author Vipul Kumar; dated 22/10/18.
 *
 * Main activity for the app. We are following single activity model.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager?.transaction {
            replace(R.id.fragmentContainer, NearbyVenueFragment())
        }
    }
}
