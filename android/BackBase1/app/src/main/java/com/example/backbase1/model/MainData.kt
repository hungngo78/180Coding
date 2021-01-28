package com.example.backbase1.model

/*
{
    "country":"UA",
    "name":"Hurzuf",
    "_id":707860,
    "coord":{
            "lon":34.283333,
        "lat":44.549999
    }
}

 */
data class LocationInfo(val name: String, val country: String, val id: Int?, val coord: Coordinate?)

data class Coordinate(val lon: Double, val lat: Double)