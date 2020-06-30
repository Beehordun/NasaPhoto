package com.example.nasaphoto.di

import android.content.Context
import com.example.core.interceptors.AuthInterceptor
import com.example.core.interceptors.NoInternetConnectionInterceptor
import com.example.data.remote.NasaApi
import com.example.nasaphoto.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DaggerHiltNetworkModule {

    private const val baseUrl = BuildConfig.BASE_URL
    private const val TIME_OUT_SECONDS = 10L

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NasaApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(NasaApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .callTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(NoInternetConnectionInterceptor(context))
            .addInterceptor(loggerInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()
    }
}
