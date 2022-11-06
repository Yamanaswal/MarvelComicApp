package com.yaman.marvelcomicapp.service


import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null

    private var isDebug : Boolean = true

    fun setDebugMode(isDebug : Boolean){
        this.isDebug = isDebug
    }


    fun getAPIClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }

    private val okHttpClient: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClientBuilder = OkHttpClient.Builder()

            okHttpClientBuilder
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addNetworkInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val request: Request.Builder = chain.request().newBuilder()
                    request.method(chain.request().method, chain.request().body)
                    request.header("Accept", "application/json")
                    request.build()
                    chain.proceed(request.build())
                })

            if(isDebug) {
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }

            return okHttpClientBuilder.build()
        }
}

