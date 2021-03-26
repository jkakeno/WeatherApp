package com.junkakeno.weatherapp.ui.utils

class Converter{

    companion object{
        fun fromKelvinToFahrenheit(kelvin : Double):String{

            val fahrenheit = ((kelvin - 273.15)*9/5+32).toInt()

            return fahrenheit.toString()
        }
    }

}