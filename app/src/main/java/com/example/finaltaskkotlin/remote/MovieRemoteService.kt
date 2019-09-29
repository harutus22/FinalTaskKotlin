package com.example.finaltaskkotlin.remote

import com.example.finaltaskkotlin.model.Movie
import retrofit2.Call
import retrofit2.http.GET


interface MovieRemoteService {
    @GET("movies.json")
    fun movies(): Call<List<Movie>>
}