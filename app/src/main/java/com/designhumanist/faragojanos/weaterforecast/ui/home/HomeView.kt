package com.designhumanist.faragojanos.weaterforecast.ui.home

import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.ui.base.BaseView

/**
 * Created by faragojanos on 2017. 10. 20..
 */
interface HomeView: BaseView {
    fun addData(weather: CurrentWeather)
}