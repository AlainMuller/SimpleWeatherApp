package com.designhumanist.faragojanos.weaterforecast.nework

import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.model.ForecastedWeather

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by faragojanos on 2017. 10. 18..
 */

interface WeatherApiService {
    @GET("weather")
    fun getCurrentWeather(@Query("q") city: String,
                         @Query("units") unit: String,
                         @Query("APPID") apiKey: String): Observable<CurrentWeather>

    @GET("forecast/daily")
    fun getForeCast(@Query("q") city: String,
                    @Query("cnt") days: Int,
                    @Query("units") unit: String,
                    @Query("APPID") apiKey: String): Observable<ForecastedWeather>

    companion object Factory {

        fun create(): WeatherApiService =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("api.openweathermap.org/data/2.5")
                    .build().create(WeatherApiService::class.java)

    }
}