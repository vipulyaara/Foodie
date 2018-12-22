package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item___ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("user")
    @Expose
    var user: User___? = null
    @SerializedName("editable")
    @Expose
    var editable: Boolean? = null
    @SerializedName("public")
    @Expose
    var public: Boolean? = null
    @SerializedName("collaborative")
    @Expose
    var collaborative: Boolean? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("canonicalUrl")
    @Expose
    var canonicalUrl: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("updatedAt")
    @Expose
    var updatedAt: Int? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo____? = null
    @SerializedName("followers")
    @Expose
    var followers: Followers? = null
    @SerializedName("listItems")
    @Expose
    var listItems: ListItems? = null
}
