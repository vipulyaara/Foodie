package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User_ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("tips")
    @Expose
    var tips: Tips? = null
    @SerializedName("lists")
    @Expose
    var lists: Lists? = null
    @SerializedName("homeCity")
    @Expose
    var homeCity: String? = null
    @SerializedName("bio")
    @Expose
    var bio: String? = null
    @SerializedName("contact")
    @Expose
    var contact: Contact_? = null
}
