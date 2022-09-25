package com.example.firebasepost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasepost.daos.PostDao
import kotlinx.coroutines.DelicateCoroutinesApi

class CreatePost : AppCompatActivity() {

    private lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        postDao = PostDao()

        findViewById<Button>(R.id.b_post).setOnClickListener {
            val input = findViewById<EditText>(R.id.et_addContent).text.toString().trim()
            if (input != null) {
                postDao.addPost(input)
                finish()
            }
            else{
                Toast.makeText(this,"Request Failed" ,Toast.LENGTH_SHORT).show()
            }

        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

    }


}
