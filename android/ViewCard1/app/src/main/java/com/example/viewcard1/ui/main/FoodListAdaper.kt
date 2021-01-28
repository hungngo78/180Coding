package com.example.viewcard1.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewcard1.model.Food
import com.example.viewcard1.R

class FoodListAdaper : RecyclerView.Adapter<FoodListAdaper.ViewHolder>() {

    private lateinit var data : List<Food>

    fun setData(_data: List<Food>) {
        data = _data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food : Food = data?.get(position)
        holder.textViewName.text = food.name
        holder.textViewVersion.text = food.version
        holder.imageView.setImageResource(food.image)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.food_list_item

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val textViewName : TextView = view.findViewById(R.id.textViewName)
        val textViewVersion : TextView = view.findViewById(R.id.textViewVersion)
        val imageView : ImageView = view.findViewById(R.id.imageView)
    }
}