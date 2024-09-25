package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Movies (var movie_id:String?="",
                   var movie_name:String?="",
                   var movie_year:Int?=0,
                   var movie_poster:String?="",
                   var category_name:String?="",
                   var director_name:String?="") : Serializable {

}