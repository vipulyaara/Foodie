package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stats {

    @SerializedName("checkinsCount")
    @Expose
    var checkinsCount: Int? = null
    @SerializedName("usersCount")
    @Expose
    var usersCount: Int? = null
    @SerializedName("tipCount")
    @Expose
    var tipCount: Int? = null
    @SerializedName("visitsCount")
    @Expose
    var visitsCount: Int? = null
}
