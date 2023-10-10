package com.bcan.arabamcomassignment.di

import com.bcan.arabamcomassignment.data.service.ArabamService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideArabamService(retrofit: Retrofit): ArabamService =
        retrofit.create(ArabamService::class.java)

}