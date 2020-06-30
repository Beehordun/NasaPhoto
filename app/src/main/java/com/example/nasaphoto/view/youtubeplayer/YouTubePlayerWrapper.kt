package com.example.nasaphoto.view.youtubeplayer

import android.app.FragmentManager
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.core.videoplayer.VideoUtil
import com.example.nasaphoto.util.clearShimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubeThumbnailView
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener
import javax.inject.Inject

class YouTubePlayerWrapper @Inject constructor() {

    lateinit var mYouTubePlayer: YouTubePlayer

    fun playVideo(
        fragmentManager: FragmentManager,
        @IdRes viewID: Int,
        videoUrl: String,
        apiKey: String
    ) {
        val youTubePlayerFragment =
            fragmentManager.findFragmentById(viewID) as YouTubePlayerFragment

        youTubePlayerFragment.initialize(apiKey,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    mYouTubePlayer = youTubePlayer
                    youTubePlayer.loadVideo(
                        VideoUtil.getVideoIdFromVideoUrl(videoUrl)
                    )
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    // Nothing
                }
            })
    }

    fun showVideoThumbnail(
        youTubeThumbnail: YouTubeThumbnailView,
        videoUrl: String,
        apiKey: String,
        loadImageBackground: (drawable: Drawable) -> Unit
    ) {
        youTubeThumbnail.initialize(apiKey,
            object : YouTubeThumbnailView.OnInitializedListener {
                override fun onInitializationSuccess(
                    youTubeThumbnailView: YouTubeThumbnailView,
                    youTubeThumbnailLoader: YouTubeThumbnailLoader
                ) {
                    youTubeThumbnailLoader.setVideo(VideoUtil.getVideoIdFromVideoUrl(videoUrl))
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                        OnThumbnailLoadedListener {

                        override fun onThumbnailLoaded(
                            youTubeThumbnailView: YouTubeThumbnailView,
                            s: String
                        ) {
                            loadImageBackground.invoke(youTubeThumbnailView.drawable)
                            youTubeThumbnailLoader.release()
                        }

                        override fun onThumbnailError(
                            youTubeThumbnailView: YouTubeThumbnailView,
                            errorReason: YouTubeThumbnailLoader.ErrorReason
                        ) {
                           //Nothing
                        }
                    })
                }

                override fun onInitializationFailure(
                    youTubeThumbnailView: YouTubeThumbnailView,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    // Nothing
                }
            })
    }

    fun releaseVideo() {
        mYouTubePlayer.release()
    }
}
