package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

data class NasaAPODModel(val explanation: String,
                         @SerializedName("hdurl")
                         val mediaUrl: String? = "",
                         @SerializedName("media_type")
                         val mediaType: String,
                         val title: String,
                         val url: String)
