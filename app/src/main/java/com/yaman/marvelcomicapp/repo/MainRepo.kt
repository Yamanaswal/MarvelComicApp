package com.yaman.marvelcomicapp.repo

import android.accounts.NetworkErrorException
import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.yaman.marvelcomicapp.models.MarvelListData
import com.yaman.marvelcomicapp.models.MarvelResponse
import com.yaman.marvelcomicapp.service.ApiService
import com.yaman.marvelcomicapp.service.RetrofitService
import okhttp3.ResponseBody
import java.io.IOException

class MainRepo {

    /*** Class Name - TAG ***/
    private val tag = "MainRepo"

    /*******************************************************************************
     * Api Service - Create Service
     * ****************************************************************************/
    private var apiService: ApiService = RetrofitService.createService(
        baseUrl = "https://gateway.marvel.com/",
        serviceClass = ApiService::class.java)


    /********************************************************************************
     * Mutable And LiveData ApiResponse - Defined Here
     * ******************************************************************************/
    private val marvelResponseMutableLiveData = MutableLiveData<ApiResponse<MarvelResponse>>()

    val marvelResponseLiveData: LiveData<ApiResponse<MarvelResponse>>
        get() = marvelResponseMutableLiveData


    /**********************************************************************************
     * Api Methods - Defined Here
     * *******************************************************************************/

    suspend fun marvelCharListApi() {
        try {
            val map = HashMap<String,String>()
            map["apikey"] = "9299b93bceefebc97341a952dcfd3d9e"
            map["hash"] = "0b9fdf22de39e6b2b6244a0915ce9876"
            map["ts"] = "1"

            val response = apiService.fetchAllChars(map)

            marvelResponseMutableLiveData.postValue(
                ApiResponse(
                    response = response.body(),
                    errorBody = response.errorBody(),
                    error = response.message()
                )
            )

        } catch (e: Exception) {
            Log.e(tag, "Exception (localizedMessage) -> marvelCharListApi: ${e.localizedMessage}")
            Log.e(tag, "Exception (message) -> marvelCharListApi: ${e.message}")
            if("Unable to resolve host \"gateway.marvel.com\": No address associated with hostname".equals(e.message)){
//                Gson().fromJson<>(MarvelListData,MarvelResponse::class.java)
            }
        }
    }

}

data class ApiResponse<T>(
    var response: T?,
    var errorBody: ResponseBody?,
    var error: String?
)