package com.example.nasaphoto

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nasaphoto.util.DateUtil
import com.example.nasaphoto.util.visible
import com.example.nasaphoto.util.invisible
import com.example.nasaphoto.util.gone
import com.example.nasaphoto.util.ImageLoader
import com.example.nasaphoto.view.videoplayer.VideoPlayerState
import com.example.nasaphoto.view.youtubeplayer.YouTubePlayerWrapper
import com.example.presentation.model.NasaAPOD
import com.example.presentation.util.ViewState
import com.example.presentation.viewmodel.NasaAPODViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nasa_apod.*
import kotlinx.android.synthetic.main.full_image_screen.*
import kotlinx.android.synthetic.main.video_screen.*
import java.util.Stack
import javax.inject.Inject

const val MAIN_SCREEN = 0
const val IMAGE_SCREEN = 1
const val VIDEO_SCREEN = 2
const val YOUTUBE = "youtube"
const val IMAGE = "image"

@AndroidEntryPoint
class NasaAPODActivity : AppCompatActivity() {

    @Inject
    lateinit var youTubePlayerWrapper: YouTubePlayerWrapper

    @Inject
    lateinit var imageLoader: ImageLoader

    private val nasaAPODViewModel: NasaAPODViewModel by viewModels()
    private val owner = { lifecycle }
    private var playerState: VideoPlayerState = VideoPlayerState()
    lateinit var nasaAPOD: NasaAPOD
    private var animatorStack = Stack<Int>()
    private val youtubeApiKey = BuildConfig.YOUTUBE_API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_apod)
        configureView()
        nasaAPODViewModel.getNasaAstronomyPictureOfDay()
        observeLiveData()
    }

    override fun onBackPressed() {
        if (animatorStack.isEmpty()) {
            super.onBackPressed()
        } else {
           goToMainScreen()
        }
    }

    private fun configureView() {
        toolbar_title.text = getString(R.string.app_name)
        date_picker.setOnDatePickedListener { date ->
            if(!DateUtil.isDateGreaterThanNow(date)) {
                nasaAPODViewModel.getNasaAstronomyPictureOfDayForDate(date)
            } else {
                Toast.makeText(this,
                    getString(R.string.date_not_valid_error_msg),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        zoom_play_btn.setOnClickListener {
            if (nasaAPOD.mediaType == IMAGE) {
                view_animator.displayedChild = IMAGE_SCREEN
                animatorStack.push(IMAGE_SCREEN)
                displayImageFullScreen(nasaAPOD.mediaUrl)
            } else {
                view_animator.displayedChild = VIDEO_SCREEN
                animatorStack.push(VIDEO_SCREEN)
                playVideo(nasaAPOD.url)
            }
        }
    }

    private fun observeLiveData() {
       nasaAPODViewModel.nasaAPODLiveData.observe(owner) { viewState ->
           when(viewState) {
               is ViewState.Loading -> {
                   background_shade.invisible()
               }
               is ViewState.Success -> {
                   handleDataFetchedSuccess(viewState.data)
                   nasaAPOD = viewState.data
               }
               is ViewState.Error.NoConnectionError -> {
                   no_internet_text.apply {
                       text = getString(R.string.no_internet_text)
                       visible()
                   }
               }
               is ViewState.Error.ServerError -> {
                   background_shade.invisible()
               }
           }
       }
    }

    private fun displayImageFullScreen(imageUrl: String) {
        imageLoader.loadImageWithUrl(this, imageUrl, full_image)
    }

    private fun playVideo(videoUrl: String) {
        if (videoUrl.contains(YOUTUBE)) {
            youTubePlayerWrapper.playVideo(
                fragmentManager,
                R.id.youtube_video_view,
                videoUrl,
                youtubeApiKey)
        } else {
            playerState.videoUrl = videoUrl
            video_view.init(this, playerState)
        }
    }

    private fun handleDataFetchedSuccess(data: NasaAPOD) {
        title_text.text = data.title
        explanation_text.text = data.explanation
        background_shade.visible()
        if (data.mediaType == IMAGE) {
           showNasaPhoto(data)
        } else {
          showVideoThumbnail(data)
        }
    }

    private fun goToMainScreen() {
        if (animatorStack.peek() == VIDEO_SCREEN) {
            youTubePlayerWrapper.releaseVideo()
        } else {
            full_image.setBackgroundColor(Color.WHITE)
        }
        view_animator.displayedChild = MAIN_SCREEN
        animatorStack.pop()
    }

    private fun showNasaPhoto(data: NasaAPOD) {
        zoom_play_btn.setImageDrawable(getDrawable(R.drawable.zoom))

        imageLoader.loadBackgroundImageWithCallback(
            context = this,
            imageUrl = data.url,
            onFailedCallback = {  println()},
            onSuccessCallback = { drawable ->
                main_layout.background = drawable
                println()
            }
        )
        video_thumbnail_view.gone()
    }

    private fun showVideoThumbnail(data: NasaAPOD) {
        youTubePlayerWrapper.showVideoThumbnail(
            video_thumbnail_view,
            data.url,
            youtubeApiKey) {
            main_layout.background = it
        }

        zoom_play_btn.setImageDrawable(getDrawable(R.drawable.play))
    }
}
