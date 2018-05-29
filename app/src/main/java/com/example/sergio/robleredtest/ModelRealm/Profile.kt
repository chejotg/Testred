package com.example.sergio.robleredtest.ModelRealm

import io.realm.RealmObject

open class Profile(
        var uuid: String?=null
) : RealmObject() {
}