package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Movies
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.view.MoviesDetailScreenActivity

class MoviesRecyclerViewAdapter (private val mContext: Context, private val moviesList:List<Movies>)
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(view: View) : RecyclerView.ViewHolder(view) {
        var movie_card: CardView
        var textViewMovieName: TextView
        var imageViewMoviePoster: ImageView

        init {
            movie_card = view.findViewById(R.id.movie_card)
            textViewMovieName = view.findViewById(R.id.textViewMovieName)
            imageViewMoviePoster = view.findViewById(R.id.imageViewMoviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design =
            LayoutInflater.from(mContext).inflate(R.layout.movie_card_design, parent, false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val movie = moviesList.get(position)
        holder.textViewMovieName.text = movie.movie_name

        holder.imageViewMoviePoster.setImageResource(
            mContext.resources.getIdentifier(
                movie.movie_poster,
                "drawable",
                mContext.packageName
            )
        )

        holder.movie_card.setOnClickListener {
            val intent = Intent(mContext, MoviesDetailScreenActivity::class.java)
            intent.putExtra("movieObject", movie)
            mContext.startActivity(intent)
        }

    }
}