package com.designhumanist.faragojanos.weaterforecast.ui.citydetails

import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.model.ForecastData
import com.designhumanist.faragojanos.weaterforecast.ui.base.BaseView

interface CityDetailsView: BaseView {
    fun addForecast(foreCast: List<ForecastData>)
    fun addWeather(weather: CurrentWeather)
}