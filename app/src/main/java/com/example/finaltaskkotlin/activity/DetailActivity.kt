package com.example.finaltaskkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.finaltaskkotlin.R
import com.example.finaltaskkotlin.StaticValues
import com.example.finaltaskkotlin.model.Movie

class DetailActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var rating: TextView
    private lateinit var releaseYear: TextView
    private lateinit var genre: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movie = intent.getParcelableExtra<Movie>(StaticValues.PARCELABLE_MOVIE)

        image = findViewById(R.id.detailImage)
        title = findViewById(R.id.detailTitle)
        rating = findViewById(R.id.detailRating)
        releaseYear = findViewById(R.id.detailReleaseYear)
        genre = findViewById(R.id.detailGenre)

        Glide.with(image).load(movie.image).centerCrop().into(image)
        title.append("Title: ${movie.title}")
        rating.append("Rating: ${movie.rating}")
        releaseYear.append("Release Year: ${movie.releaseYear}")
        genre.append("Genre: ${movie.genre?.joinToString(prefix = "[", postfix = "]", separator = ", ")}")
    }
}
