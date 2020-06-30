package com.example.nasaphoto.view.videoplayer

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.example.nasaphoto.R
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import kotlinx.android.synthetic.main.video_player.view.*

class VideoPlayer constructor(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private lateinit var videoComponent: VideoPlayerComponent

    init {
        isSaveEnabled = true
        View.inflate(context, R.layout.video_player, this)
    }

    fun init(owner: LifecycleOwner, playerState: VideoPlayerState) {
        initVideoComponent(playerState)
        if (playerState.videoUrl.isNullOrEmpty()) {
            player_view.onPause()
            videoComponent.disposePlayer()
            owner.lifecycle.removeObserver(videoComponent)
        } else {
            owner.lifecycle.removeObserver(videoComponent)
            owner.lifecycle.addObserver(videoComponent)
        }
    }

    private fun initVideoComponent(playerState: VideoPlayerState) {
        if (!this::videoComponent.isInitialized) {
            videoComponent =
                VideoPlayerComponent(
                    context,
                    player_view,
                    playerState
                )
        }
    }

    var isFullScreen: Boolean
        set(value) {
            if (value) {
                hideSystemUi()
                expand()
            }
        }
        get() = this.systemUiVisibility == View.SYSTEM_UI_FLAG_FULLSCREEN

    private fun hideSystemUi() {
        player_view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun expand() {
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        player_view.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
    }
}
