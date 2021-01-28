package com.example.backbase1.ui.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.view.*
import com.example.backbase1.R
import com.example.backbase1.utility.ConstantKey
import com.google.android.gms.maps.SupportMapFragment

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    private var lon : Double = 0.0
    private var lat: Double = 0.0
    private var cityAndCountry : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lon = intent.getDoubleExtra("lon", 0.0)
        lat = intent.getDoubleExtra("lat", 0.0)
        cityAndCountry = intent.getStringExtra("cityAndCountry").toString()

        println("Hung, MapActivity, onCreate(), ${lon} - ${lat} - ${cityAndCountry}")
        setContentView(com.example.backbase1.R.layout.activity_map)

        val fragment = SupportMapFragment()
        fragment.getMapAsync(this)
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

        setUpMap()
    }

    override fun onMapReady(p0: GoogleMap) {
        gMap =  p0
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