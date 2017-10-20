package com.designhumanist.faragojanos.weaterforecast.ui.citydetails

import com.designhumanist.faragojanos.weaterforecast.model.ForecastData
import com.designhumanist.faragojanos.weaterforecast.ui.base.BaseView

/**
 * Created by faragojanos on 2017. 10. 20..
 */
interface CityDetailsView: BaseView {
    fun addData(foreCast: List<ForecastData>)
}