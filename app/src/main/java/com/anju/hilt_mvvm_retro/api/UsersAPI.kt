package com.anju.hilt_mvvm_retro.api

import com.anju.hilt_mvvm_retro.model.UsersLibraryItem
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPI {

    @GET("/api/characters/staff")
    suspend fun getStaff(): Response<List<UsersLibraryItem>>

    @GET("/api/characters/students")
    suspend fun getStudents(): Response<List<UsersLibraryItem>>

    @GET("/api/characters")
    suspend fun getCharacters(): Response<List<UsersLibraryItem>>
}