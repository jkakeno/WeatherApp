package com.junkakeno.weatherapp.ui.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.initToolbar(title: String, backEnabled: Boolean) {
  val appCompatActivity = activity as AppCompatActivity
  appCompatActivity.supportActionBar?.show()
  appCompatActivity.supportActionBar?.title = title.format()
  appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(backEnabled)
}

fun Fragment.hideToolbar() {
  val appCompatActivity = activity as AppCompatActivity
  appCompatActivity.supportActionBar?.hide()
}