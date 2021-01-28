package com.example.backbase1.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.wifi.rtt.WifiRttManager
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceControl
import android.view.View
import android.widget.AdapterView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.backbase1.R
import com.example.backbase1.model.LocationInfo
import com.example.backbase1.utility.ConstantKey
import com.example.backbase1.utility.ItemListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback, ItemListener {
    private lateinit var gMap: GoogleMap
    private var cityAndCountry: String = ""
    private var lon: Double = 0.0
    private var lat: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Hung, onCreate()")
        setContentView(R.layout.activity_main)

        // fragment
        var listViewFragment : Fragment? = supportFragmentManager.findFragmentById(R.id.listViewContainer)
        var mapFragment : Fragment? = supportFragmentManager.findFragmentById(R.id.mapViewContainer)

        val tran : FragmentTransaction = supportFragmentManager.beginTransaction()
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (listViewFragment == null) {
                listViewFragment = FirstFragment()
                tran.replace(R.id.listViewContainer, listViewFragment)
            }

            if (mapFragment == null) {
                mapFragment = SupportMapFragment()
                mapFragment.getMapAsync(this)
                tran.replace(R.id.mapViewContainer, mapFragment)
            }

            tran.commit()
        } else {
            if (listViewFragment == null) {
                listViewFragment = FirstFragment()
                tran.replace(R.id.listViewContainer, listViewFragment)
                tran.commit()
            }
        }

        // check and request permission
        setUpMap()
    }

    override fun onItemClick(position: Int, info: LocationInfo) {
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            println("Hung, Activity, onItemClick() = ${info.coord?.lon} - ${info.coord?.lat}")
            val coordinate = LatLng(info.coord?.lon ?: 0.0, info.coord?.lat ?: 0.0)
            gMap.clear()
            gMap.addMarker(MarkerOptions().position(coordinate).title(info.name + ", " + info.country))
            gMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
        } else {
            println("Hung, Activity, onItemClick(), PORTRAIT")

            var intent = Intent(this, MapActivity::class.java).apply {
                this.putExtra("lon", info.coord?.lon ?: 0.0)
                this.putExtra("lat", info.coord?.lat ?: 0.0)
                this.putExtra("cityAndCountry", info.name + ", " + info.country)
            }

            startActivity(intent)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        println("Hung, onMapReady")
        gMap = p0
        gMap.clear()
        val coordinate = LatLng(lon, lat)
        gMap.addMarker(MarkerOptions().position(coordinate).title(cityAndCountry))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate))
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                ConstantKey.LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
    }
}