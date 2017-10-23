package com.designhumanist.faragojanos.weaterforecast.ui.home

import com.designhumanist.faragojanos.weaterforecast.model.SimpleCity
import com.designhumanist.faragojanos.weaterforecast.nework.NetworkService
import com.designhumanist.faragojanos.weaterforecast.ui.base.BasePresenter
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save

/**
 * Created by faragojanos on 2017. 10. 20..
 */

class HomePresenter<V: HomeView>: BasePresenter<V>() {

    lateinit var networkService: NetworkService
    private val cities: MutableList<SimpleCity> = mutableListOf()

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

        cities.clear()
        cities.addAll(SimpleCity().queryAll())
    }

    fun getData() {
        if (cities.isEmpty()) {
            view!!.showEmpty()
        }
        else {
            cities.forEach {
                compositeSubscriptions.add(networkService.getWeather(it.name!!).subscribe(
                        {
                            view!!.addData(it)
                        },
                        {
                            view!!.onError()
                        }
                ))
            }
        }
    }

    fun addCity(city: String) {
        compositeSubscriptions.add(networkService.getWeather(city).subscribe(
                {
                    val simpleCity = SimpleCity(city)
                    simpleCity.save()
                    cities.add(simpleCity)
                    view!!.addData(it)
                },
                {
                    view!!.onError()
                }
        ))
    }


}
