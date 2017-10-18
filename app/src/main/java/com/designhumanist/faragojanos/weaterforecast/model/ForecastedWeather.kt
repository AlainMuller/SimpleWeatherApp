package com.designhumanist.faragojanos.weaterforecast.model

/**
 * Created by faragojanos on 2017. 10. 18..
 */
data class ForecastedWeather(val city: City,
                             val cod: Int,
                             val cnt: Int,
                             val list: List<ForecastData>)

data class City(val id: Int,
                val name: String,
                val coord: Coordinates,
                val country: String)