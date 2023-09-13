package com.anju.hilt_mvvm_retro.repository

import com.anju.hilt_mvvm_retro.api.UsersAPI
import javax.inject.Inject

class UsersRepoImpl @Inject constructor(private val usersAPI: UsersAPI) : UsersRepo {
    override suspend fun getStaff() = usersAPI.getStaff()
    override suspend fun getStudents() = usersAPI.getStudents()
    override suspend fun getCharacters() = usersAPI.getCharacters()

}