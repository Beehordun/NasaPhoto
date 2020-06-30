package com.example.nasaphoto.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import javax.inject.Inject

class ImageLoader @Inject constructor() {

    fun loadImageWithUrl(context: Context,
                         imageUrl: String,
                         image: ImageView,
                         @DrawableRes resourceId: Int = -1,
                         placeholder: Drawable? = null) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholder)
            .into(image)
    }

    fun loadImageWithCallbacks(context:Context,
                               imageUrl: String,
                               image: ImageView,
                               @DrawableRes placeholderResourceId: Int = -1,
                               onFailedCallback: () -> Unit,
                               onSuccessCallback: () -> Unit
    ) {

        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholderResourceId)
            .centerCrop()
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onFailedCallback.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccessCallback.invoke()
                    return false
                }
            })
            .into(image)
    }
}
