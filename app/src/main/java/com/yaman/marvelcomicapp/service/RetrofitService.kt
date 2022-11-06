package com.yaman.marvelcomicapp.service

object RetrofitService {

    fun <S> createService(baseUrl:String, serviceClass: Class<S>): S {
        return RetrofitClient.getAPIClient(baseUrl)!!.create(serviceClass)
    }

}