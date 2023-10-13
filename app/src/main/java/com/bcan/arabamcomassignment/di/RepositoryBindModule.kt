package com.bcan.arabamcomassignment.di

import com.bcan.arabamcomassignment.data.repository.ArabamRepository
import com.bcan.arabamcomassignment.data.repository.ArabamRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBindModule {
    @Binds
    abstract fun bindArabamRepository(
        arabamRepositoryImpl: ArabamRepositoryImpl
    ): ArabamRepository

}