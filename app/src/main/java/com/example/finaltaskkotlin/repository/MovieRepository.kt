package com.example.finaltaskkotlin.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finaltaskkotlin.model.Movie
import com.example.finaltaskkotlin.remote.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository{
    var movies: MutableLiveData<List<Movie>>

    companion object{
        private var movieRepository: MovieRepository? = null

        fun instanceOf(): MovieRepository{
            if (movieRepository == null){
                movieRepository = MovieRepository()
            }
            return movieRepository as MovieRepository
        }
    }

    init {
        movies = MutableLiveData()
        val call: Call<List<Movie>>? = ApiManager.getInstance()?.getMovies()?.movies()
        call?.enqueue(object : Callback<List<Movie>>{
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("Make", "On failed cause" + t.message)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful){
                    Log.d("Make", "Response is successful")
                    movies.value = response.body()
                } else {
                    Log.d("Make", "Response is not successful")
                }
            }
        })
    }
}