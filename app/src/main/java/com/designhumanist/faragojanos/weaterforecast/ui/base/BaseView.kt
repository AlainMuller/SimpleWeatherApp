package com.designhumanist.faragojanos.weaterforecast.ui.base

import android.content.Context

/**
 * Created by faragojanos on 2017. 10. 19..
 */
interface BaseView {
    fun getContext(): Context
    fun showEmpty()
    fun showOffline()
    fun showLoading()
    fun showContent()
    fun onError()
}