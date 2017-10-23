package com.designhumanist.faragojanos.weaterforecast.ui.citydetails

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.bumptech.glide.Glide

import com.designhumanist.faragojanos.weaterforecast.R
import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.model.ForecastData
import com.designhumanist.faragojanos.weaterforecast.ui.citydetails.adapter.ForecastAdapter
import com.designhumanist.faragojanos.weaterforecast.ui.statelayout.LayoutState
import kotlinx.android.synthetic.main.activity_city_details.*
import java.text.SimpleDateFormat
import java.util.*

class CityDetailsActivity : AppCompatActivity(), CityDetailsView {

    val presenter = CityDetailsPresenter<CityDetailsView>()
    val adapter = ForecastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)

        forecastRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        forecastRecyclerView.adapter = adapter

        if (intent.hasExtra("city")) {
            presenter.city = intent.getStringExtra("city")
        }
        else {
            cityStateLayout.state = LayoutState.Error
        }

        presenter.bind(this)

        citySwipeRefreshLayout.setOnRefreshListener {
            presenter.getData()
        }
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

    override fun addForecast(foreCast: List<ForecastData>) {
        adapter.addForecast(foreCast)
    }

    override fun addWeather(weather: CurrentWeather) {
        cityTextView.text = weather.name
        descriptionTextView.text = weather.weather[0].description
        Glide.with(this)
                .load("http://openweathermap.org/img/w/${weather.weather[0].icon}.png")
                .into(imageView)
        currentTempTextView.text = weather.main.temp.toString()
        sunTextView.text = getString(R.string.sun,
                SimpleDateFormat("hh:mm").format(Date(weather.sys.sunrise)),
                SimpleDateFormat("hh:mm").format(Date(weather.sys.sunset))
        )
    }


    override fun getContext(): Context = this

    override fun showEmpty() {
        cityStateLayout.state = LayoutState.Empty
    }

    override fun showOffline() {
        cityStateLayout.state = LayoutState.Offline
    }

    override fun showLoading() {
        cityStateLayout.state = LayoutState.Loading
    }

    override fun showContent() {
        cityStateLayout.state = LayoutState.Content
    }

    override fun onError() {
        cityStateLayout.state = LayoutState.Error
    }
}
