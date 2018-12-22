package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VenueDetailFromResponse {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("contact")
    @Expose
    var contact: Contact? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("canonicalUrl")
    @Expose
    var canonicalUrl: String? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @SerializedName("verified")
    @Expose
    var verified: Boolean? = null
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Likes? = null
    @SerializedName("rating")
    @Expose
    var rating: Double? = null
    @SerializedName("ratingColor")
    @Expose
    var ratingColor: String? = null
    @SerializedName("ratingSignals")
    @Expose
    var ratingSignals: Int? = null
    @SerializedName("beenHere")
    @Expose
    var beenHere: BeenHere? = null
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("storeId")
    @Expose
    var storeId: String? = null
    @SerializedName("page")
    @Expose
    var page: Page? = null
    @SerializedName("hereNow")
    @Expose
    var hereNow: HereNow? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("tips")
    @Expose
    var tips: Tips_? = null
    @SerializedName("shortUrl")
    @Expose
    var shortUrl: String? = null
    @SerializedName("timeZone")
    @Expose
    var timeZone: String? = null
    @SerializedName("listed")
    @Expose
    var listed: Listed? = null
    @SerializedName("phrases")
    @Expose
    var phrases: List<Phrase>? = null
    @SerializedName("hours")
    @Expose
    var hours: Hours? = null
    @SerializedName("popular")
    @Expose
    var popular: Popular? = null
    @SerializedName("pageUpdates")
    @Expose
    var pageUpdates: PageUpdates? = null
    @SerializedName("inbox")
    @Expose
    var inbox: Inbox? = null
    @SerializedName("venueChains")
    @Expose
    var venueChains: List<Any>? = null
    @SerializedName("attributes")
    @Expose
    var attributes: Attributes? = null
    @SerializedName("bestPhoto")
    @Expose
    var bestPhoto: BestPhoto? = null
}
