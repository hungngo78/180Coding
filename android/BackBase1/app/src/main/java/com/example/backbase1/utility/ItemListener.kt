package com.example.backbase1.utility

import com.example.backbase1.model.LocationInfo

interface ItemListener {
    fun onItemClick(position: Int, info: LocationInfo)
}