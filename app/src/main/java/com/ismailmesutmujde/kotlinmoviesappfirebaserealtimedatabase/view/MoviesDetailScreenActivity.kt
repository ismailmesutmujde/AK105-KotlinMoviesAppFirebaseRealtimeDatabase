package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.databinding.ActivityMoviesDetailScreenBinding
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Movies
import com.squareup.picasso.Picasso

class MoviesDetailScreenActivity : AppCompatActivity() {
    private lateinit var bindingMoviesDetail : ActivityMoviesDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMoviesDetail = ActivityMoviesDetailScreenBinding.inflate(layoutInflater)
        val view = bindingMoviesDetail.root
        setContentView(view)

        val movie = intent.getSerializableExtra("movieObject") as Movies

        bindingMoviesDetail.textViewMovieNameDetail.text = movie.movie_name
        bindingMoviesDetail.textViewMovieYearDetail.text = (movie.movie_year).toString()
        bindingMoviesDetail.textViewMovieDirectorDetail.text = movie.director_name
        val url = "http://kasimadalan.pe.hu/filmler/resimler/${movie.movie_poster}"
        Picasso.get().load(url).into(bindingMoviesDetail.imageViewMoviePoster)
        //bindingMoviesDetail.imageViewMoviePoster.setImageResource(resources.getIdentifier(movie.movie_poster,"drawable",packageName))
    }
}