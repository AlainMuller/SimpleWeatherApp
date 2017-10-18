package com.designhumanist.faragojanos.weaterforecast.model

/**
 * Created by faragojanos on 2017. 10. 18..
 */

data class ForecastData(val dt: Long,
                        val temp: Temperature,
                        val pressure: Double,
                        val humidity: Int,
                        val weather: Weather)

data class Temperature(val day: Double,
                       val min: Double,
                       val max: Double,
                       val night: Double,
                       val eve: Double,
                       val morn: Double)
