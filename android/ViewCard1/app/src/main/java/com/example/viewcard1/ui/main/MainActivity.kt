package com.example.viewcard1.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.viewcard1.R
import com.example.viewcard1.model.Food
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adaper: FoodListAdaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("Hung, Activity onCreate()")

        adaper = FoodListAdaper()
        adaper.setData(Food.getData())
        recyclerViewFoodList.adapter = adaper
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.menuItemAdd) {
            Toast.makeText(this, "Menu Item click", Toast.LENGTH_SHORT).show()
        }

        return true
    }
}