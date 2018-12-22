package com.foodie.data.mapper

import com.foodie.data.entities.VenueDetail
import com.foodie.data.entities.toVenueDetail
import com.foodie.data.model.detail.VideoDetailResponse

/**
 * @author Vipul Kumar; dated 21/12/18.
 */
class VenueDetailMapper : Mapper<VideoDetailResponse, VenueDetail> {
    override fun map(from: VideoDetailResponse): VenueDetail {
        return from.response?.venue?.toVenueDetail() ?: VenueDetail()
    }
}
