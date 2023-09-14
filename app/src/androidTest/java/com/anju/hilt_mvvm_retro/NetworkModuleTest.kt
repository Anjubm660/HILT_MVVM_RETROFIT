package com.anju.hilt_mvvm_retro

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleTest {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    inline fun <reified T> provideApiClient(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, loggingInterceptor: HttpLoggingInterceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}