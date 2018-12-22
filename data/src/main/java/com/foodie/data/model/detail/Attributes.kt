package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Attributes {

    @SerializedName("groups")
    @Expose
    var groups: List<Group______>? = null
}
