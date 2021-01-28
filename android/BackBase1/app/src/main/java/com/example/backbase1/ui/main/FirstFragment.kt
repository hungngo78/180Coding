package com.example.backbase1.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.backbase1.R
import com.example.backbase1.model.LocationInfo
import com.example.backbase1.utility.ItemListener
import kotlinx.android.synthetic.main.fragment_first.*
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel : LocationListViewModel
    private lateinit var callbackListener : ItemListener

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }



    /*fun setCallbackListener(callback : ItemListener) {
        callbackListener = callback
    }*/
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callbackListener = context as ItemListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LocationListViewModel::class.java)
        viewModel?.let {
            location_list.adapter = it.adapter

            if (it.adapter.itemCount == 0) {
                it.loadData(requireActivity().applicationContext).observe(viewLifecycleOwner,  Observer<List<LocationInfo>> {data ->
                    // update UI
                    it.adapter.setData(data)
                })
            } //else {
                //it.adapter.setData(mutableListOf())
            //}

            it.adapter.callbackListener = callbackListener
        }

        handleSearch()
    }

    private fun handleSearch() {
        search_bar?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let {
                    viewModel?.filterData(it)
                }
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    viewModel?.filterData(it)
                }
                return false
            }
        })
    }


}