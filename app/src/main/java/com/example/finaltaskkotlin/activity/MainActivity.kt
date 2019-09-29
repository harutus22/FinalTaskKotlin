package com.example.finaltaskkotlin.activity

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltaskkotlin.ConnectivityBroadcastReceiver
import com.example.finaltaskkotlin.R
import com.example.finaltaskkotlin.StaticValues
import com.example.finaltaskkotlin.Util.NetWorkUtil
import com.example.finaltaskkotlin.model.Movie
import com.example.finaltaskkotlin.recyclerview.MovieAdapter
import com.example.finaltaskkotlin.viewModel.MovieViewModel

class MainActivity : AppCompatActivity(), MovieAdapter.OnMovieClicked {
    lateinit var movieAdapter: MovieAdapter
    lateinit var receiver: ConnectivityBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!NetWorkUtil.getConnectivityStatus(this)){
            Toast.makeText(this,
                StaticValues.ASK_CONNECTION, Toast.LENGTH_LONG).show();
            finish();
        }

        registerBroadcastReceiver()

        createRecyclerView()

        createViewModel()
    }

    private fun createRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        movieAdapter = MovieAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = movieAdapter
    }

    private fun createViewModel(){
        val movieViewModel:MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.liveData.observe(this, Observer<List<Movie>>{
            movieAdapter.submitList(it)
        })
    }

    private fun registerBroadcastReceiver(){
            receiver = ConnectivityBroadcastReceiver()
            registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    override fun onItemClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(StaticValues.PARCELABLE_MOVIE, movie)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
