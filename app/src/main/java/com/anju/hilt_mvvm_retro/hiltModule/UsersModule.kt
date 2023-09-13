package com.anju.hilt_mvvm_retro.hiltModule

import com.anju.hilt_mvvm_retro.repository.UsersRepo
import com.anju.hilt_mvvm_retro.repository.UsersRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UsersModule {

    @Binds
    fun usersRepo(repo: UsersRepoImpl): UsersRepo
}