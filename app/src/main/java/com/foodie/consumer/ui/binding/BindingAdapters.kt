package com.foodie.consumer.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.facebook.stetho.common.LogUtil
import com.foodie.consumer.extensions.loadImage

/**
 * Data Binding adapters specific to the app.
 */
@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView?, url: String?) {
    LogUtil.d("URL $url")
    imageView?.loadImage { load(url) }
}
