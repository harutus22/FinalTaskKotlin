package com.example.finaltaskkotlin.remote

import com.example.finaltaskkotlin.StaticValues
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager{
    companion object Factory{
        var retrofit : Retrofit? = null
        var apiManager: ApiManager? = null


        @Synchronized
        fun getInstance(): ApiManager? {
            if (apiManager == null){
                apiManager = ApiManager()
            }
            return apiManager
        }

        init {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(StaticValues.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    fun getMovies(): MovieRemoteService? {
        return retrofit?.create(MovieRemoteService::class.java)
    }
}