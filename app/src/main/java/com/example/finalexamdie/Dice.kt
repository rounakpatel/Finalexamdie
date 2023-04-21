package com.example.finalexamdie

import android.util.Log

class Dice(private var numOfSides: Int) {
    var sideUp: Int = 0

    init {
        roll()
    }

    fun roll() {
        sideUp = (0 until numOfSides).random()
    }
}
