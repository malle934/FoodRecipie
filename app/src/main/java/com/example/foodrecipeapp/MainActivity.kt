package com.example.foodrecipeapp


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp.databinding.ActivityMainBinding
import com.example.foodrecipeapp.databinding.ActivitySignUpBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var texts: Array<String>
//    val next: FloatingActionButton = findViewById(R.id.btnInsert)
   lateinit var newarray : ArrayList<recipie>
  //   lateinit var newarray : ArrayList<recipie>
    private val desc = arrayOf("non veg","pure veg","pure veg","customizable","pure veg","pure veg","pure veg")
    private val img = arrayOf(R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.a,R.drawable.g)

    private lateinit var instruction: Array<String>

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next: FloatingActionButton = findViewById(R.id.btnInsert)

         texts = arrayOf("Special Noodles","Veg Biryani","Veg Sandwich","Pizza Friday","Pancakes","Ice Creams","Veg Burger")
        instruction = arrayOf(
            getString(R.string.noodles),
            getString(R.string.noodles),getString(R.string.noodles),getString(R.string.noodles),getString(R.string.noodles),
            getString(R.string.noodles),
            getString(R.string.noodles),
        )
        val recyclerView:RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        var adapter = CustomAdapter(img,texts,desc)
        recyclerView?.adapter = adapter



        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity,Description::class.java)
                try {
                    intent.putExtra("n", texts[position])
                    intent.putExtra("im", img[position])
                    intent.putExtra("in", instruction[position])
                    startActivity(intent)
                }
                catch (e:Exception){
                    Toast.makeText(this@MainActivity,e.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }

        })
        next.setOnClickListener {
            val intent = Intent(this@MainActivity,Uplodingdata::class.java)
            startActivity(intent)
        }


    }


}