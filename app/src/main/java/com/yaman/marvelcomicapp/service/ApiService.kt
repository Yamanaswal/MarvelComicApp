package com.yaman.marvelcomicapp.service

import com.yaman.marvelcomicapp.models.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiService {

    @GET("v1/public/characters")
    suspend fun fetchAllChars(@QueryMap hashMap: HashMap<String,String>): Response<MarvelResponse>

}