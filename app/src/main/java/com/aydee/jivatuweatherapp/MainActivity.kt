package com.aydee.jivatuweatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aydee.jivatuweatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            getResults()
    }

    fun getResults(){
        val base_url = "https://api.open-meteo.com/"
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url).build()
        val fetchApi = retrofit.create<MyApiInterface>().getWeatherData(28.535517, 77.391029,
            arrayOf("temperature_2m","wind_speed_10m"))

        fetchApi.enqueue(object : Callback<WeatherApi>{
            override fun onResponse(call: Call<WeatherApi>, response: Response<WeatherApi>) {
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null){
                    Toast.makeText(applicationContext, "Api fetched!!" , Toast.LENGTH_SHORT).show()

                    val lat = responseBody.latitude.toString()
                    val lon = responseBody.longitude.toString()
                    val temp = responseBody.current.temperature_2m.toString()
                    val temp_unit = responseBody.current_units.temperature_2m.toString()
                    val wind = responseBody.current.wind_speed_10m.toString()
                    val wind_unit = responseBody.current_units.wind_speed_10m.toString()

                    with(binding){
                        txtTemperature.text = "${temp}"
                        txtTempUnit.text = "${temp_unit}"
                        txtLatitude.text = "Latitude : $lat"
                        txtLongitude.text = "Longitude : $lon"
                        txtWind.text = "Wind : ${wind} ${wind_unit}"
                    }
                }
            }

            override fun onFailure(call: Call<WeatherApi>, t: Throwable) {
                Toast.makeText(applicationContext, "Unable to fetch API!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}