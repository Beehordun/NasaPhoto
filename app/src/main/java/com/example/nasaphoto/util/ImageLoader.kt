package com.example.nasaphoto.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

    fun loadBackgroundImageWithCallback(
        context:Context,
        imageUrl: String,
        @DrawableRes placeholderResourceId: Int = -1,
        onFailedCallback: () -> Unit,
        onSuccessCallback: (resource: Drawable) -> Unit
    ) {
        Glide.with(context)
            .load(imageUrl)
            .into(object : CustomTarget<Drawable?>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {
                    onFailedCallback.invoke()
                }
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    onSuccessCallback.invoke(resource)
                }
            })

    }
}
