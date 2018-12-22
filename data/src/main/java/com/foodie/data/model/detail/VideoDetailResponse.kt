package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoDetailResponse {

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("response")
    @Expose
    var response: Response? = null
}
