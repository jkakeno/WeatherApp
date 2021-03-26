package com.junkakeno.weatherapp.ui.utils

fun String.format() = this.split(' ').joinToString(" ") { it.capitalize() }