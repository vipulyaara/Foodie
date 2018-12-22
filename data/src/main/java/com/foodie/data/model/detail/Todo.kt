package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Todo {

    @SerializedName("count")
    @Expose
    var count: Int? = null
}
