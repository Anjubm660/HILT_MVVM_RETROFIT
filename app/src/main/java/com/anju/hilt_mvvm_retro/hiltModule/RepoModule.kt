package com.anju.hilt_mvvm_retro.hiltModule

import com.anju.hilt_mvvm_retro.api.UsersAPI
import com.anju.hilt_mvvm_retro.hiltModule.NetworkModule.provideApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideUsersAPI(retrofit: Retrofit): UsersAPI {
        return provideApiClient(retrofit)
    }
}