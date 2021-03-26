package com.junkakeno.weatherapp.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.junkakeno.weatherapp.R
import com.junkakeno.weatherapp.data.model.ListItem
import com.junkakeno.weatherapp.ui.utils.Converter
import com.junkakeno.weatherapp.ui.utils.format

class ForecastAdapter(private val context: Activity, list: List<ListItem?>) :
    ArrayAdapter<ListItem>(context, R.layout.forecast_item, list) {

    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = context.layoutInflater.inflate(R.layout.forecast_item, parent, false)

        val forecast = getItem(position)

        val weather = rowView.findViewById(R.id.weather) as TextView
        val temperature = rowView.findViewById(R.id.temperature) as TextView

        weather.text = forecast?.weather?.get(0)?.main?.format()
        temperature.text = context.resources.getString(R.string.temp,forecast?.main?.temp?.let { Converter.fromKelvinToFahrenheit(it) })

        return rowView
    }
}
