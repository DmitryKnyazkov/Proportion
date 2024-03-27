package com.example.proportion

class ProportionModel {


    fun calculate(a: Float, b: Float, c: Float, ): Float? {

        return if (c!=0F) a*b/c else null
    }

}