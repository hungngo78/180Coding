package com.example.backbase1.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.backbase1.R
import com.example.backbase1.model.LocationInfo
import com.example.backbase1.utility.ItemListener

class LocationListAdapter : RecyclerView.Adapter<LocationListAdapter.LocationViewHolder>() {

    private var cityList : List<LocationInfo>? = null

    var callbackListener : ItemListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

        return LocationViewHolder(dataBinding)
    }

    fun setData(data: List<LocationInfo>) {
        cityList = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.dataBinding.setVariable(com.example.backbase1.BR.nameOfCity, cityList?.get(position)?.name)
        holder.dataBinding.setVariable(com.example.backbase1.BR.country, cityList?.get(position)?.country)
//        println("Hung, onBindViewHolder(), ${cityList?.get(position)?.coord?.lat} - ${cityList?.get(position)?.coord?.lon}")

        // bind ItemListener
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                callbackListener?.onItemClick(position, cityList!!.get(position))
            }
        })
    }

    override fun getItemCount(): Int {
        return cityList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.location_list_item
    }

    class LocationViewHolder(val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root)
}