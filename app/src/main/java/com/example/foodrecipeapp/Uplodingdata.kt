package com.example.foodrecipeapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream

class Uplodingdata : AppCompatActivity() {
//    private var img:ImageView?= findViewById(R.id.imageView)
    var img:ImageView? = null
//    var name:TextView= findViewById(R.id.ed)
//    var desc:TextView= findViewById(R.id.ed1)
//    var inst:TextView= findViewById(R.id.ed2)
//    var uplod:Button = findViewById(R.id.button2)
//    var add:Button = findViewById(R.id.button3)
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uplodingdata)
         img= findViewById(R.id.img2)
        var name:TextView?= findViewById(R.id.ed)
        var desc:TextView?= findViewById(R.id.ed1)
        var inst:TextView?= findViewById(R.id.ed2)
        var uplod:Button ?= findViewById(R.id.button2)
        var add:Button ?= findViewById(R.id.button3)
         uplod?.setOnClickListener {
             val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
             startActivityForResult(gallery, pickImage)
        }
        add?.setOnClickListener {
            val db = DBHelper(this,null)

            val nameText = name?.text.toString()
            val descr = desc?.text.toString()
            val instruction= inst?.text.toString()
            var i = getDrawable(R.drawable.a)
            val bitmap = (i as BitmapDrawable).getBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()


            db.addUser(nameText,descr,image,instruction)



            Snackbar.make(it,"$nameText is added to database", Snackbar.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            img?.setImageURI(imageUri)
        }
    }


}