package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model

import java.io.Serializable

data class Movies (var movie_id:Int,
                   var movie_name:String,
                   var movie_year:Int,
                   var movie_poster:String,
                   var category: Categories,
                   var director: Directors) : Serializable {

}