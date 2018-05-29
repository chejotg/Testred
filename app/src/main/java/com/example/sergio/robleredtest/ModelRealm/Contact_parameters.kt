package com.example.sergio.robleredtest.ModelRealm

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Contact_parameters (
        @PrimaryKey
        var name: String="",
        var order:Int= 0,
        var titles_rc: RealmList<TitlesRc> = RealmList()
):RealmObject(){
}

