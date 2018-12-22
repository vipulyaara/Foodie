package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("reasons")
    @Expose
    var reasons: Reasons? = null
    @SerializedName("venue")
    @Expose
    var venue: VenueFromResponse? = null
}
