package com.junkakeno.weatherapp.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.junkakeno.weatherapp.R
import com.junkakeno.weatherapp.data.util.Status
import com.junkakeno.weatherapp.databinding.ForecastCollectionBinding
import com.junkakeno.weatherapp.ui.adapter.ForecastAdapter
import com.junkakeno.weatherapp.ui.utils.initToolbar
import com.junkakeno.weatherapp.ui.viewmodel.ForecastCollectionViewModel
import timber.log.Timber

class ForecastCollectionFragment : Fragment() {

    private var _binding: ForecastCollectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var collectionViewModel: ForecastCollectionViewModel
    private lateinit var city:String

    companion object {
        const val CITY = "city"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = ForecastCollectionBinding.inflate(inflater, container, false)

        city = arguments?.getString(CITY) ?: "Empty city"

        buildViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(city,true)

        activity?.let { activity ->
            collectionViewModel.getForecast(city).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.processBar.visibility = View.GONE

                        it.data?.list?.let { list ->

                            binding.forecastList.adapter = ForecastAdapter(activity, list)

                            binding.forecastList.setOnItemClickListener { _, _, position, _ ->
                                val forecast = list[position]
                                findNavController().navigate(R.id.action_forecastCollectionFragment_to_forecastDetailFragment,
                                    bundleOf(Pair(ForecastDetailFragment.FORECAST, forecast))
                                )
                            }

                        }
                    }
                    Status.ERROR -> {
                        binding.processBar.visibility = View.GONE
                        binding.errorMsg.visibility = View.VISIBLE
                        binding.errorMsg.text = it.message
                    }
                    Status.LOADING -> {
                        binding.processBar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun buildViewModel(){
        collectionViewModel = ViewModelProvider(this).get(ForecastCollectionViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}