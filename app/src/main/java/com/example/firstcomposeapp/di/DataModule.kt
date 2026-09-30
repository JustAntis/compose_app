package com.example.firstcomposeapp.di

import com.example.firstcomposeapp.data.network.ApiService
import com.example.firstcomposeapp.data.repository.LemmyRepositoryImpl
import com.example.firstcomposeapp.domain.repositories.LemmyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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


        @Provides @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor{
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides @Singleton
        fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient{
            return OkHttpClient.Builder().apply {
                addInterceptor(logging)
            }.build()
        }
        @Singleton
        @Provides
        fun provideApiService(client: OkHttpClient): ApiService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}