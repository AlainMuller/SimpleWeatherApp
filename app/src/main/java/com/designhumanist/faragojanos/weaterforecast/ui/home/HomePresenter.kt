package com.designhumanist.faragojanos.weaterforecast.ui.home

import com.designhumanist.faragojanos.weaterforecast.nework.NetworkService
import com.designhumanist.faragojanos.weaterforecast.ui.base.BasePresenter

/**
 * Created by faragojanos on 2017. 10. 20..
 */

class HomePresenter<V: HomeView>: BasePresenter<V>() {

    lateinit var networkService: NetworkService

    override fun bind(view: V) {
        super.bind(view)

        networkService = NetworkService.create(this.view!!.getContext())

        compositeSubscriptions.add(networkService
                .networkChangeListener().subscribe {
            if (!it) {
                this.view!!.showOffline()
            }
        })
    }

    fun getData(city: String) {
        compositeSubscriptions.add(networkService.getWeather(city).subscribe(
                {
                    view!!.addData(it)
                },
                {
                    view!!.onError()
                }
        ))
    }


}
