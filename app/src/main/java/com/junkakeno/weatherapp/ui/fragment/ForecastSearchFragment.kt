package com.junkakeno.weatherapp.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.junkakeno.weatherapp.R
import com.junkakeno.weatherapp.databinding.ForecastSearchBinding
import com.junkakeno.weatherapp.ui.utils.hideToolbar

class ForecastSearchFragment : Fragment() {

    private var _binding: ForecastSearchBinding? = null
    private val binding get() = _binding!!
    private var city:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = ForecastSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideToolbar()

        val textWatcher = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                city = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }

        binding.queryEt.addTextChangedListener(textWatcher)

        binding.lookupBt.setOnClickListener {
            if (city.isNotEmpty() && city.isNotBlank()) {
                findNavController().navigate(
                    R.id.action_forecastSearchFragment_to_forecastCollectionFragment,
                    bundleOf(Pair(ForecastCollectionFragment.CITY, city))
                )
            }else{
                Toast.makeText(context,"Enter a city",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}