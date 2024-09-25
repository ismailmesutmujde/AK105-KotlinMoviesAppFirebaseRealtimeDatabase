package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Categories (var category_id:String?="",
                       var category_name:String?="") : Serializable {

}