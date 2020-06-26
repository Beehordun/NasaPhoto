package com.example.nasaphoto.di

import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.datasource.RemoteDataSourceImpl
import com.example.data.remote.repository.NasaAPODRepositoryImpl
import com.example.domain.repository.NasaAPODRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DaggerHiltDataModule {
    @Binds
    abstract fun bindNasaAPODRepositoryImpl(nasaAPODRepositoryImpl: NasaAPODRepositoryImpl): NasaAPODRepository
    @Binds
    abstract fun bindRemoteDataSourceImpl(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
