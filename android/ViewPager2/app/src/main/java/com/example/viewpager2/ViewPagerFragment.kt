package com.example.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewpager2.model.Card
import kotlinx.android.synthetic.main.page_item.*
import org.w3c.dom.Text

class ViewPagerFragment () : Fragment() {

    private lateinit var topSuit : TextView
    private lateinit var bottomSuit : TextView
    private lateinit var value : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.page_item, container, false)

        val card = Card.fromBundle(arguments!!)

//        println("Hung, onCreateView(), ${card.suit} - ${card.value}")
        topSuit = view.findViewById(R.id.testViewTopSuit) as TextView
        bottomSuit = view.findViewById(R.id.testViewBottomSuit) as TextView
        value = view.findViewById(R.id.textViewValue) as TextView

        topSuit.text = card.suit
        value.text = card.value
        bottomSuit.text = card.suit

        return view
    }
}