package com.example.sergio.robleredtest.ModelRealm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TitlesRc (
        var fk : String = "",
        var name : String="",
        var order: Int = 0,
        var icon: String="",
        var value : String=""
):RealmObject() {
}