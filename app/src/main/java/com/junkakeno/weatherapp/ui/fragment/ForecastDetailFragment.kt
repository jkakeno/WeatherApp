package com.junkakeno.weatherapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.junkakeno.weatherapp.R
import com.junkakeno.weatherapp.data.model.ListItem
import com.junkakeno.weatherapp.databinding.ForecastDetailBinding
import com.junkakeno.weatherapp.ui.utils.Converter

class ForecastDetailFragment : Fragment() {

    private var _binding: ForecastDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var forecast:ListItem

    companion object {
        const val FORECAST = "forecast"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        arguments?.getParcelable<ListItem>(FORECAST)?.let { forecast = it }

        _binding = ForecastDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.temp.text = forecast.main?.temp?.let { Converter.fromKelvinToFahrenheit(it) }
        binding.feelsLike.text = resources.getString(R.string.feels_like,forecast.main?.feelsLike?.let { Converter.fromKelvinToFahrenheit(it) })
        binding.weather.text = forecast.weather?.get(0)?.main
        binding.weatherDescription.text = forecast.weather?.get(0)?.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}