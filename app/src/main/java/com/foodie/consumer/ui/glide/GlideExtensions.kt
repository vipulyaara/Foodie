package com.foodie.consumer.ui.glide

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.annotation.GlideType
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
@GlideExtension
object GlideExtensions {
    @GlideOption
    @JvmStatic
    fun round(options: RequestOptions, size: Int): RequestOptions {
        return options.circleCrop().override(size)
    }

    @JvmStatic
    @GlideType(Drawable::class)
    fun saturateOnLoad(requestBuilder: RequestBuilder<Drawable>) {
        requestBuilder.transition(DrawableTransitionOptions.with(SaturationTransitionFactory()))
    }
}
