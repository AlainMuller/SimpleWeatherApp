package com.designhumanist.faragojanos.weaterforecast.ui.navigation

import android.content.Context
import android.content.Intent
import com.designhumanist.faragojanos.weaterforecast.ui.citydetails.CityDetailsActivity
import com.designhumanist.faragojanos.weaterforecast.ui.home.HomeActivity

class Navigate(val context: Context) {
    fun toHome() {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

    fun toCity(city: String) {
        context.startActivity(Intent(context, CityDetailsActivity::class.java)
                .putExtra("city", city))
    }
}