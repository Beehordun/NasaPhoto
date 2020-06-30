package com.example.nasaphoto.di

import com.example.core.dispatcher.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ApplicationComponent::class)
object DaggerHiltCoreModule {

    @Provides
    fun provideAppCoroutineDispatchers() =
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            main = Dispatchers.Main,
            default = Dispatchers.Default
        )
}
