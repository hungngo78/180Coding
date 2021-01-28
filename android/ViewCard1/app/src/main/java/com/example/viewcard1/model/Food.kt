package com.example.viewcard1.model

import com.example.viewcard1.R

class Food private constructor(val id: Int, val name: String, val version: String, val image: Int) {

    companion object {
        val NAMES = setOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb",
                        "Ice Cream Sandwich","JellyBean", "Kitkat", "Lollipop", "Marshmallow")
        val VERSIONS = setOf("1.5", "1.6", "2.0-2.1", "2.2-2.2.3", "2.3-2.3.7", "3.0-3.2.6",
                        "4.0-4.0.4", "4.1-4.3.1", "4.4-4.4.4", "5.0-5.1.1","6.0-6.0.1")

        val IDs = setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val DRAWABLES = listOf(R.drawable.cupcake, R.drawable.donut, R.drawable.eclair,
            R.drawable.froyo, R.drawable.gingerbread, R.drawable.honeycomb, R.drawable.ics,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop,R.drawable.marsh)

        fun getData() : List<Food>  {
            var data = mutableListOf<Food>()

            for (id in IDs) {
                data.add(Food(IDs.elementAt(id), NAMES.elementAt(id), VERSIONS.elementAt(id), DRAWABLES.elementAt(id)))
            }

            return data
        }

        fun List<Food>.find(name: String, version: String) : Food? {
            return find { it.name == name && it.version == version }
        }
    }
}