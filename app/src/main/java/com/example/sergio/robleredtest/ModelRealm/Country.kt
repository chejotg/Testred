package com.example.sergio.robleredtest.ModelRealm

import io.realm.RealmObject

open class Country(
        var name: String?=null,
        var code : Int?=null,
        var icon : String?=null,
        var order : Int?=null
): RealmObject() {
}