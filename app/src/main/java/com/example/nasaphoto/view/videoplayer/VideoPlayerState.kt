package com.example.nasaphoto.view.videoplayer

import android.os.Parcelable
import com.google.android.exoplayer2.C
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoPlayerState(
    var playWhenReady: Boolean = true,
    var currentWindow: Int = C.INDEX_UNSET,
    var playBackPosition: Long = 0,
    var videoUrl: String? = null
) : Parcelable
