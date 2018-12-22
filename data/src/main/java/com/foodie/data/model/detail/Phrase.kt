package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Phrase {

    @SerializedName("phrase")
    @Expose
    var phrase: String? = null
    @SerializedName("sample")
    @Expose
    var sample: Sample? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
}
