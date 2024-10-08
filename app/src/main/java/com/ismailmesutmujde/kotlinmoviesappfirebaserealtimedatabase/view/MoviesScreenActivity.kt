package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.adapter.MoviesRecyclerViewAdapter
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.databinding.ActivityMoviesScreenBinding
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Categories
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Directors
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Movies

class MoviesScreenActivity : AppCompatActivity() {
    private lateinit var bindingMoviesScreen : ActivityMoviesScreenBinding

    private lateinit var moviesList:ArrayList<Movies>
    private lateinit var adapterMovies: MoviesRecyclerViewAdapter
    private lateinit var refMovies: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMoviesScreen = ActivityMoviesScreenBinding.inflate(layoutInflater)
        val view = bindingMoviesScreen.root
        setContentView(view)

        val category = intent.getSerializableExtra("categoryObject") as Categories

        bindingMoviesScreen.toolbarMovies.title = "Movies : ${category.category_name}"
        setSupportActionBar(bindingMoviesScreen.toolbarMovies)

        bindingMoviesScreen.recyclerViewMovies.setHasFixedSize(true)
        bindingMoviesScreen.recyclerViewMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val db = FirebaseDatabase.getInstance()
        refMovies = db.getReference("movies")

        moviesList = ArrayList()

        /*
        val c1 = Categories(1,"Science Fiction")
        val c2 = Categories(2,"Drama")
        val c3 = Categories(3,"Action")

        val d1 = Directors(1,"Christopher Nolan")
        val d2 = Directors(2,"Quentin Tarantino")
        val d3 = Directors(3,"Roman Polanski")

        val m1 = Movies(1,"Interstellar",2014,"interstellar",c1,d1)
        val m2 = Movies(2,"Django",2012,"django",c2,d2)
        val m3 = Movies(3,"Inception",2010,"inception",c3,d1)
        val m4 = Movies(4,"The Pianist",2002,"thepianist",c2,d3)

        moviesList.add(m1)
        moviesList.add(m2)
        moviesList.add(m3)
        moviesList.add(m4)
        */

        adapterMovies = MoviesRecyclerViewAdapter(this, moviesList)
        bindingMoviesScreen.recyclerViewMovies.adapter = adapterMovies

        allMoviesByCategoryId(category.category_name)
    }

    fun allMoviesByCategoryId(category_name:String?){
        val query = refMovies.orderByChild("category_name").equalTo(category_name)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                moviesList.clear()
                for (m in snapshot.children) {
                    val movie = m.getValue(Movies::class.java)
                    if (movie != null) {
                        movie.movie_id = m.key
                        moviesList.add(movie)
                    }
                }
                adapterMovies.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}