package com.designhumanist.faragojanos.weaterforecast.nework

import android.content.Context
import com.designhumanist.faragojanos.weaterforecast.R
import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.model.ForecastedWeather
import com.laimiux.rxnetwork.RxNetwork
import rx.Observable
import rx.Single
import rx.Single.just
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by faragojanos on 2017. 10. 19..
 */

class NetworkService(private val context: Context) {
    val DAYS: Int = 5

    fun networkChangeListener(): Observable<Boolean> =
            RxNetwork.stream(context).observeOn(AndroidSchedulers.mainThread())

    fun getWeather(city: String): Single<CurrentWeather> {
        return WeatherApiService.Factory.create().getCurrentWeather(city,
                context.getString(R.string.metric),
                context.getString(R.string.APIKEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
                .flatMap {
                    if (it.cod == 200) {
                        just(it)
                    }
                    else error(Throwable())
                }
    }

    fun getForecast(city: String): Single<ForecastedWeather> {
        return WeatherApiService.Factory.create().getForeCast(city,
                DAYS,
                context.getString(R.string.metric),
                context.getString(R.string.APIKEY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
                .flatMap( {
                    if (it.cod == 200) {
                        just(it)
                    }
                    else error(Throwable())
                })
    }

}