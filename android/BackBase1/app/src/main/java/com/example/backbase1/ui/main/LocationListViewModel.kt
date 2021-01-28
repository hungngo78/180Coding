package com.example.backbase1.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.backbase1.model.LocationInfo
import com.example.backbase1.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocationListViewModel : ViewModel() {
    private var repo = Repository()
    private var cityListLiveData = MutableLiveData<List<LocationInfo>>()

    var adapter : LocationListAdapter = LocationListAdapter()

    fun loadData(applicationContext : Context) : LiveData<List<LocationInfo>> {
        /*
        Thread(Runnable {
            repo.bindJSONDataInFacilityList(applicationContext)
            if (repo.cityModelList.size > 0) {
                cityListLiveData.postValue(repo.cityModelList)
            }
        }).start() */

        val myJob = CoroutineScope(Dispatchers.IO).launch {
            repo.bindJSONDataInFacilityList(applicationContext)

            val res = withContext(Dispatchers.Main) {
                if (repo.cityModelList.size > 0) {
                    cityListLiveData.value = repo.cityModelList
                }
                1
            }

            println("Hung, res of Load = ${res}")
        }

        return cityListLiveData;
    }

    fun filterData(str: String) {
        cityListLiveData.value = repo.filterData(str)
        adapter.setData(cityListLiveData.value as List<LocationInfo>)
    }
}