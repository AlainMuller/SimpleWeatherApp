package com.designhumanist.faragojanos.weaterforecast.nework

import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.model.ForecastedWeather
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by faragojanos on 2017. 10. 18..
 */

interface WeatherApiService {
    @GET("weather")
    fun getCurrentWeater(@Query("q") city: String): Observable<CurrentWeather>

    @GET("forecast/daily")
    fun getForeCast(@Query("q") city: String,
                    @Query("cnt") days: Int,
                    @Query("units") unit: String): Observable<ForecastedWeather>

    companion object Factory {
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("api.openweathermap.org/data/2.5")
                    .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}