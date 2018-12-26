package com.foodie.consumer.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.foodie.consumer.ui.glide.SaturationTransitionFactory

/**
 * Created by VipulKumar on 21/09/18.
 */

inline fun ImageView.loadImage(
    requestManager: RequestManager = Glide.with(this),
    func:
    RequestManager.() -> RequestBuilder<Drawable>
) {
    requestManager.func().apply(
        RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    )
        .transition(
            GenericTransitionOptions<Drawable>()
                .transition(SaturationTransitionFactory())
        )
        .into(this)
}
