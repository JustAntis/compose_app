package com.example.firstcomposeapp.di

import com.example.firstcomposeapp.data.network.ApiService
import com.example.firstcomposeapp.data.repository.LemmyRepositoryImpl
import com.example.firstcomposeapp.domain.repositories.LemmyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindLemmyRepository(impl: LemmyRepositoryImpl): LemmyRepository

    companion object {
        const val BASE_URL = "https://lemmy.world/api/v3/"

        @Singleton
        @Provides
        fun provideApiService(): ApiService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}