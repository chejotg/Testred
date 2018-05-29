package com.example.sergio.robleredtest.ModelRealm

import io.realm.RealmList
import io.realm.RealmObject

open class Settings_Parameters(
        var contact : RealmList<Contact_parameters>?=null
) : RealmObject() {

}