package com.example.foodrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        val name : TextView =findViewById(R.id.nam)
        val img: ImageView = findViewById(R.id.imageView2)
        val instruc: TextView = findViewById(R.id.textView2)

       val bundle:Bundle? = intent.extras
        val recname = bundle!!.getString("n")
        val recimage = bundle!!.getInt("im")
        val inst = bundle!!.getString("in")

        name.text = recname
        instruc.text = inst
        img.setImageResource(recimage)
    }
}


