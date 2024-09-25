package com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.adapter.CategoriesRecyclerViewAdapter
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinmoviesappfirebaserealtimedatabase.model.Categories

class MainScreenActivity : AppCompatActivity() {
    private lateinit var bindingMainScreen : ActivityMainScreenBinding

    private lateinit var categoryList:ArrayList<Categories>
    private lateinit var adapterCategory: CategoriesRecyclerViewAdapter
    private lateinit var refCategories:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbarCategory.title = "Categories"
        setSupportActionBar(bindingMainScreen.toolbarCategory)

        bindingMainScreen.recyclerViewCategory.setHasFixedSize(true)
        bindingMainScreen.recyclerViewCategory.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refCategories = db.getReference("categories")

        categoryList = ArrayList()

        /*
        val c1 = Categories(1,"Science Fiction")
        val c2 = Categories(2,"Drama")
        val c3 = Categories(3,"Action")
        val c4 = Categories(4,"Adventure")
        val c5 = Categories(5,"Fantastic")
        val c6 = Categories(6,"Romantic")
        val c7 = Categories(1,"Comedy")
        val c8 = Categories(2,"Thriller")
        val c9 = Categories(1,"Horror")
        val c10 = Categories(2,"War")

        categoryList.add(c1)
        categoryList.add(c2)
        categoryList.add(c3)
        categoryList.add(c4)
        categoryList.add(c5)
        categoryList.add(c6)
        categoryList.add(c7)
        categoryList.add(c8)
        categoryList.add(c9)
        categoryList.add(c10)*/

        adapterCategory = CategoriesRecyclerViewAdapter(this, categoryList)
        bindingMainScreen.recyclerViewCategory.adapter = adapterCategory

        allCategories()
    }

    fun allCategories(){
        refCategories.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()
                for (c in snapshot.children) {
                    val category = c.getValue(Categories::class.java)
                    if (category != null) {
                        category.category_id = c.key
                        categoryList.add(category)
                    }
                }
                adapterCategory.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}