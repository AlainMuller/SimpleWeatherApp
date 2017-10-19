package com.designhumanist.faragojanos.weaterforecast.model

/**
 * Created by faragojanos on 2017. 10. 18..
 */

data class CurrentWeather(val id: Int,
                          val cod: Int,
                          val name: String,
                          val dt: Long,
                          val coord: Coordinates,
                          val weather: List<Weather>,
                          val base: String,
                          val main: Details,
                          val wind: Wind,
                          val clouds: Clouds,
                          val sys: Sys )

data class Details(val temp: Double,
                   val pressure: Double,
                   val humidity: Int,
                   val temp_min: Double,
                   val temp_max: Double,
                   val sea_level: Double,
                   val grnd_level: Double )

data class Sys(val type: Int,
               val id: Int,
               val message: Double,
               val country: String,
               val sunrise: Long,
               val sunset: Long)

data class Wind(val speed: Double,
                val deg: Double)

data class Clouds(val all: Int)