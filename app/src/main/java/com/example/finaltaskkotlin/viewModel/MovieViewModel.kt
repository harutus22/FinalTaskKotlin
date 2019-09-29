package com.example.finaltaskkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finaltaskkotlin.model.Movie
import com.example.finaltaskkotlin.repository.MovieRepository

class MovieViewModel: ViewModel(){
    private var movieRepository: MovieRepository = MovieRepository.instanceOf()
    var liveData: MutableLiveData<List<Movie>> = movieRepository.movies
}