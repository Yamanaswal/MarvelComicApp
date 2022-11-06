package com.yaman.marvelcomicapp.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaman.marvelcomicapp.models.Data
import com.yaman.marvelcomicapp.models.MarvelResponse
import com.yaman.marvelcomicapp.repo.ApiResponse
import com.yaman.marvelcomicapp.repo.MainRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepo = MainRepo()

    val getMarvelResponse: LiveData<ApiResponse<MarvelResponse>>
        get() = mainRepo.marvelResponseLiveData

    fun getMarvelCharList(){
        viewModelScope.launch {
            mainRepo.marvelCharListApi()
        }
    }




}