package com.anju.hilt_mvvm_retro.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anju.hilt_mvvm_retro.model.UsersLibraryItem
import com.anju.hilt_mvvm_retro.repository.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersDetailsViewModel @Inject constructor(private val usersRepo: UsersRepo) : ViewModel() {

    val staffList = MutableLiveData<List<UsersLibraryItem>?>()
    val studentList = MutableLiveData<List<UsersLibraryItem>?>()
    val characterList = MutableLiveData<List<UsersLibraryItem>?>()

    var currentLoadedStudentsList = mutableListOf<UsersLibraryItem>()
    var currentLoadedStaffList= mutableListOf<UsersLibraryItem>()
    var currentLoadedCharaterList= mutableListOf<UsersLibraryItem>()
    private var totalStudents = emptyList<UsersLibraryItem>()
    private var totalStaff= emptyList<UsersLibraryItem>()
    private var totalCharacters= emptyList<UsersLibraryItem>()
    var loadedItemCount = 0
    private val batchSize = 20
    var isFetchingData = false
    var totalItemCount = 0
    private var i = 1

    fun fetchInitialStaff() {
        if (isFetchingData) return

        viewModelScope.launch {
            isFetchingData = true
            kotlin.runCatching {
                usersRepo.getStaff()
            }.onSuccess {
                totalStaff = it.body() ?: emptyList()
                if (totalStaff.isNotEmpty()) {
                    totalItemCount = totalStaff.size
                    currentLoadedStaffList += totalStaff.take(batchSize)
                    staffList.postValue(currentLoadedStaffList)
                    loadedItemCount = currentLoadedStaffList.size

                }

            }.onFailure {
                staffList.postValue(null)
            }
            isFetchingData = false
        }
    }


    fun fetchInitialStudents() {
        if (isFetchingData) return

        viewModelScope.launch {
            isFetchingData = true
            kotlin.runCatching {
                // Load the first batch of items (e.g., 1 to 20)
                usersRepo.getStudents()
            }.onSuccess {
                totalStudents = it.body() ?: emptyList()
                if (totalStudents.isNotEmpty()) {
                    totalItemCount = totalStudents.size
                    currentLoadedStudentsList += totalStudents.take(batchSize)
                    studentList.postValue(currentLoadedStudentsList)
                    loadedItemCount = currentLoadedStudentsList.size
                }
            }.onFailure {
                studentList.postValue(null)
            }
            isFetchingData = false
        }
    }

    fun fetchInitialCharacters() {
        if (isFetchingData) return

        viewModelScope.launch {
            isFetchingData = true
            kotlin.runCatching {
                usersRepo.getCharacters()
            }.onSuccess {
                totalCharacters = it.body() ?: emptyList()
                if (totalCharacters.isNotEmpty()) {
                    totalItemCount = totalCharacters.size
                    currentLoadedCharaterList += totalCharacters.take(batchSize)
                    characterList.postValue(currentLoadedCharaterList)
                    loadedItemCount = currentLoadedCharaterList.size

                }

            }.onFailure {
                characterList.postValue(null)
            }
            isFetchingData = false
        }
    }

    fun loadMoreStudents() {
        if (isFetchingData) return
        val newVariable = totalStudents.chunked(batchSize)[i++]
        currentLoadedStudentsList += newVariable
        studentList.postValue(currentLoadedStudentsList)
        loadedItemCount = currentLoadedStudentsList.size
        isFetchingData = false
    }
    fun loadMoreCharacters() {
        if (isFetchingData) return
        val newVariable = totalCharacters.chunked(batchSize)[i++]
        currentLoadedCharaterList += newVariable
        characterList.postValue(currentLoadedCharaterList)
        loadedItemCount = currentLoadedCharaterList.size
        isFetchingData = false
    }
    fun loadMoreStaff() {
        if (isFetchingData) return
        val newVariable = totalStaff.chunked(batchSize)[i++]
        currentLoadedStaffList += newVariable
        staffList.postValue(currentLoadedStaffList)
        loadedItemCount = currentLoadedStaffList.size
        isFetchingData = false
    }


    fun hasMoreItemsToLoad(): Boolean {
        return loadedItemCount < totalItemCount
    }




}