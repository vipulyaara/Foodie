package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("pluralName")
    @Expose
    var pluralName: String? = null
    @SerializedName("shortName")
    @Expose
    var shortName: String? = null
    @SerializedName("icon")
    @Expose
    var icon: Icon? = null
    @SerializedName("primary")
    @Expose
    var primary: Boolean? = null
}
