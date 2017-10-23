package com.designhumanist.faragojanos.weaterforecast.ui.home

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText

import com.designhumanist.faragojanos.weaterforecast.R
import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.ui.home.adapter.CityListAdapter
import com.designhumanist.faragojanos.weaterforecast.ui.statelayout.LayoutState
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {

    private val adapter = CityListAdapter()
    private val presenter = HomePresenter<HomeView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cityListRecyclerView.layoutManager = LinearLayoutManager(this)
        cityListRecyclerView.adapter = adapter

        presenter.bind(this)

        swipeRefreshLayout.setOnRefreshListener {
            reload()
        }

        floatingActionButton.setOnClickListener {
            val cityName = EditText(this)
            AlertDialog.Builder(this)
                    .setTitle(getString(R.string.new_city_title))
                    .setMessage(getString(R.string.new_city_message))
                    .setView(cityName)
                    .setPositiveButton(getString(R.string.add_city_button),
                            {
                                _, _ -> presenter.addCity(cityName.text.toString())
                            })
                    .show()
        }


        //reload()
    }

    override fun getContext(): Context = this

    override fun showEmpty() {
        homeStateLayout.state = LayoutState.Empty
    }

    override fun showOffline() {
        homeStateLayout.state = LayoutState.Offline
    }

    override fun showLoading() {
        homeStateLayout.state = LayoutState.Loading
    }

    override fun showContent() {
        homeStateLayout.state = LayoutState.Content
    }

    override fun onError() {
        homeStateLayout.state = LayoutState.Error
    }

    override fun addData(weather: CurrentWeather) {
        adapter.add(weather)
        showContent()
    }

    override fun reload() {
        swipeRefreshLayout.isRefreshing = false
        showLoading()
        adapter.clear()
        presenter.getData()
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }
}
