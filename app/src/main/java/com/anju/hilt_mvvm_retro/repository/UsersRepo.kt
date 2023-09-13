package com.anju.hilt_mvvm_retro.repository

import com.anju.hilt_mvvm_retro.model.UsersLibraryItem
import retrofit2.Response

interface UsersRepo {
    suspend fun getStaff(): Response<List<UsersLibraryItem>>
    suspend fun getStudents(): Response<List<UsersLibraryItem>>
    suspend fun getCharacters(): Response<List<UsersLibraryItem>>
}