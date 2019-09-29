package com.example.finaltaskkotlin.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finaltaskkotlin.R
import com.example.finaltaskkotlin.model.Movie

class MovieAdapter(val clickListener: OnMovieClicked): ListAdapter<Movie,
        MovieAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(LayoutInflater.
        from(parent.context).inflate(R.layout.view_movie, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION ){
                clickListener.onItemClicked(movie)
            } }
    }

    inner class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var movieImage: ImageView = view.findViewById(R.id.movieImage)
        private var movieTitle: TextView = view.findViewById(R.id.movieTitle)
        private var movieRating: TextView = view.findViewById(R.id.movieRating)
        private var movieReleaseYear: TextView = view.findViewById(R.id.movieReleaseYear)

        fun bind(movie: Movie){
            Glide.with(itemView).load(movie.image).centerCrop().into(movieImage)
            movieTitle.text = movie.title
            movieRating.text = movie.rating.toString()
            movieReleaseYear.text = movie.releaseYear.toString()


        }
    }

    interface OnMovieClicked{
        fun onItemClicked(movie: Movie)
    }

    companion object {
        class DiffCallback : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.image == newItem.image &&
                        oldItem.rating == newItem.rating &&
                        oldItem.releaseYear == newItem.releaseYear &&
                        oldItem.genre == newItem.genre
            }
        }
    }
}




