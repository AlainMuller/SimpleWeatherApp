package com.designhumanist.faragojanos.weaterforecast.ui.base

import rx.subscriptions.CompositeSubscription

/**
 * Created by faragojanos on 2017. 10. 19..
 */
open class BasePresenter<V: BaseView> {
    protected var view: V? = null
    protected val compositeSubscriptions: CompositeSubscription = CompositeSubscription()

    open fun bind(view: V) {
        this.view = view
    }

    open fun unBind() {
        compositeSubscriptions.clear()
        view = null
    }
}