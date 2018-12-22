package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Page {

    @SerializedName("pageInfo")
    @Expose
    var pageInfo: PageInfo? = null
    @SerializedName("user")
    @Expose
    var user: User_? = null
}
