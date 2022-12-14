package com.example.foodrecipeapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CustomAdapter(val img:Array<Int>,val text:Array<String>,val desc:Array<String>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

  fun setOnItemClickListener(listener:onItemClickListener ){
      mlistener = listener
  }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomAdapter.CustomViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(v, mlistener)
    }

    override fun getItemCount(): Int {
        return text.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        holder.bindValue(img[position],text[position],desc[position])
    }

    //    our coustomeviewholder should inherit recyclerview.viewholder
    //and it will receive item view from here
    class CustomViewHolder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        //since we are getting this view using this we can also get
        // text1, text2

        fun bindValue(image: Int,txt: String, desc:String){
            itemView.findViewById<ImageView>(R.id.food).setImageResource(image)
            itemView.findViewById<TextView>(R.id.text1).text = txt
            itemView.findViewById<TextView>(R.id.text2).text = desc
        }
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


    }
}