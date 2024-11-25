package com.example.androidbottomnavigationviewtest.ui.fragments.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidbottomnavigationviewtest.R
import com.example.androidbottomnavigationviewtest.databinding.FragmentWeatherBinding
import com.example.androidretrofiteweaterapi.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this).get(WeatherViewModel::class.java)

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentWeather("Moscow")
        binding.getData.setOnClickListener {
            getCurrentWeather(binding.cityET.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                RetrofitInstance.api.getCurrentWeather(
//                    "Moscow",
                    city,
                    "metric",
                    requireActivity().applicationContext.getString(R.string.key)
                )

            } catch (e: IOException) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()
                    binding.cityET.setText(data!!.name)
                    binding.tempTV.text = "${data.main.temp} Cels"
                    binding.directTV.text = "${data.wind.deg}"
                    binding.speedTV.text = "${data.wind.speed} m/sec"
                    val icon = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$icon@4x.png"
                    Picasso.get().load(imageUrl).into(binding.imageIV)
                    val convertPressure = (data.main.pressure / 1.33).toInt()
                    binding.pressureTV.text = "${convertPressure} mm Hg"
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}