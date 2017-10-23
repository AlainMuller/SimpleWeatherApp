package com.designhumanist.faragojanos.weaterforecast

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class WeatherForecast: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("citiesRealm")
                .build())
    }
}
