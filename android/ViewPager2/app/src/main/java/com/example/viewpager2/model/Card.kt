package com.example.viewpager2.model

import android.os.Bundle
import java.util.*

class Card (val suit: String, val value: String) {

    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putStringArray(Card.BUNDLE_CARD_ARG, arrayOf(suit, value))

        return bundle
    }


    companion object {
        val BUNDLE_CARD_ARG = "Bundle_Card_Argument"

        val SUITS  = setOf<String>("♣" /* clubs*/, "♦" /* diamonds*/, "♥" /* hearts*/, "♠" /*spades*/)
        val VALUES = setOf<String>("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")

        val DUCKs = SUITS.flatMap { suit -> VALUES.map { value ->  Card(suit, value)} }

        fun fromBundle(bundle: Bundle): Card {
            val arr = bundle.getStringArray(Card.BUNDLE_CARD_ARG)
            return Card(arr!![0], arr!![1])
        }
    }
}