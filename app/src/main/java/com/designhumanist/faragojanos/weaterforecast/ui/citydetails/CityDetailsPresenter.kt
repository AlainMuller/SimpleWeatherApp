package com.designhumanist.faragojanos.weaterforecast.ui.citydetails

import com.designhumanist.faragojanos.weaterforecast.nework.NetworkService
import com.designhumanist.faragojanos.weaterforecast.ui.base.BasePresenter

/**
 * Created by faragojanos on 2017. 10. 20..
 */
class CityDetailsPresenter<V: CityDetailsView>: BasePresenter<V>() {

    lateinit var networkService: NetworkService
    var city = ""

    override fun bind(view: V) {
        super.bind(view)

        networkService = NetworkService.create(this.view!!.getContext())

        compositeSubscriptions.add(networkService
                .networkChangeListener().subscribe {
            if (!it) {
                this.view!!.showOffline()
            }
            else {
                getData()
            }
        })
    }

    fun getData() {
        compositeSubscriptions.add(networkService.getWeather(city).subscribe(
                {
                    view!!.addWeather(it)
                },
                {
                    view!!.onError()
                }
        ))
        compositeSubscriptions.add(networkService.getForecast(city)
                .subscribe(
                        {
                            view!!.addForecast(it.list)
                        },
                        {
                            //ignoring api key error
                        }
                )
        )
    }
}