package com.designhumanist.faragojanos.weaterforecast.ui

import android.app.Activity
import android.os.Bundle
import com.designhumanist.faragojanos.weaterforecast.ui.navigation.Navigate

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigate(this).toHome()
    }
}
