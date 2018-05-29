package com.example.sergio.robleredtest.ModelRealm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class menu_parameters(
        @PrimaryKey
        var name : String?=null,
        var active :Int?=null,
        var icon: String?=null,
        var order : Int??=null,
        var storyboardid : String?=null,
        var sub_title_rc : String?= null,
        var title_rc : String?=null
) : RealmObject() {
}