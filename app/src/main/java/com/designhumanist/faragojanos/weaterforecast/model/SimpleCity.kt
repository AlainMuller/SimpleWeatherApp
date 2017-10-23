package com.designhumanist.faragojanos.weaterforecast.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SimpleCity(@PrimaryKey open var name: String? = null): RealmObject()