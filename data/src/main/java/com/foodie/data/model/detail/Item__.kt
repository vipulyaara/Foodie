package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item__ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("canonicalUrl")
    @Expose
    var canonicalUrl: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo_? = null
    @SerializedName("photourl")
    @Expose
    var photourl: String? = null
    @SerializedName("lang")
    @Expose
    var lang: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Likes_? = null
    @SerializedName("logView")
    @Expose
    var logView: Boolean? = null
    @SerializedName("agreeCount")
    @Expose
    var agreeCount: Int? = null
    @SerializedName("disagreeCount")
    @Expose
    var disagreeCount: Int? = null
    @SerializedName("todo")
    @Expose
    var todo: Todo? = null
    @SerializedName("user")
    @Expose
    var user: User__? = null
    @SerializedName("editedAt")
    @Expose
    var editedAt: Int? = null
    @SerializedName("authorInteractionType")
    @Expose
    var authorInteractionType: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}
