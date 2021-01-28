package com.example.backbase1.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Repository {
    lateinit var cityModelList: ArrayList<LocationInfo>

    fun bindJSONDataInFacilityList(applicationContext: Context) {
        cityModelList = ArrayList<LocationInfo>()
        val cityJsonArray = loadJSONFromAssets(applicationContext, "cities.json")

        var gson = Gson()
        var t =   object : TypeToken<List<LocationInfo>>() {}.type
        var mainData = gson.fromJson<List<LocationInfo>>(cityJsonArray, t)

        cityModelList.addAll(mainData)

        sortData()
    }

    private fun loadJSONFromAssets(applicationContext: Context, fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
    private fun sortData() {
        cityModelList.sortWith(object: Comparator<LocationInfo>{
            override fun compare(p1: LocationInfo, p2: LocationInfo): Int = when {
                p1.name.equals(p2.name) -> p1.country.compareTo(p2.country)
                else -> p1.name.compareTo(p2.name)
            }
        })

    }

    fun filterData(str: String) : List<LocationInfo> {
//        return cityModelList.filter { it.name.startsWith(str, true)}
        return cityModelList.filter { it.name.contains(str, true) }
    }

}
