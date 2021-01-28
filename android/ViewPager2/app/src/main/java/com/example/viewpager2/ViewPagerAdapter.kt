package com.example.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.viewpager2.model.Card

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val card = Card.DUCKs[position]
        val fragment = ViewPagerFragment()
        fragment.arguments = card.toBundle()

        return fragment
    }

    override fun getItemCount(): Int {
        return Card.DUCKs.size
    }
}