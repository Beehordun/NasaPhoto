package com.example.core.videoplayer

object VideoUtil {
    fun getVideoIdFromVideoUrl(videoUrl: String): String {
        val slashIndex = videoUrl.lastIndexOf('/')
        val queryIndex = videoUrl.indexOf('?')
        return videoUrl.substring(slashIndex + 1, queryIndex)
    }
}
