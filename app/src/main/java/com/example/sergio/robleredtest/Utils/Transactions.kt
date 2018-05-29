package com.example.sergio.robleredtest.Utils

import com.example.sergio.robleredtest.ModelRealm.Contact_parameters
import com.example.sergio.robleredtest.ModelRealm.Country
import com.example.sergio.robleredtest.ModelRealm.TitlesRc
import com.example.sergio.robleredtest.ModelRealm.menu_parameters
import com.google.firebase.database.DataSnapshot
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.kotlin.createObject

class Transactions {
    fun  copyorUpdate (realmObject: RealmObject){
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(realmObject)
        realm.commitTransaction()
        realm.close()
    }

    fun findfirst_contact (name:String): Contact_parameters? {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val data = realm.where(Contact_parameters::class.java)
                .equalTo("name",name)
                .findFirst()
        realm.commitTransaction()
        realm.close()
        return data
    }
    fun findfirst_Country(name:String):Country?{
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val data = realm.where(Country::class.java)
                .equalTo("name",name)
                .findFirst()
        realm.commitTransaction()
        realm.close()
        return data
    }
    fun findfirst_Menu(name:String):menu_parameters?{
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val data = realm.where(menu_parameters::class.java)
                .equalTo("name",name)
                .findFirst()
        realm.commitTransaction()
        realm.close()
        return data
    }
    fun findAllMenu():RealmResults<menu_parameters>?{
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val data = realm.where(menu_parameters::class.java)
                .findAll()
        realm.commitTransaction()
        realm.close()
        return data
    }

    fun findall (realmObject: RealmObject,params:ArrayList<String>){

    }
    fun update_contac_Parameters(data: Contact_parameters,dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            data.order = dataSnapshot.child("order").value.toString().toInt()

            for(pbx_email in dataSnapshot.children){
                if(pbx_email.key!="order"){
                    val dataquery = realm.where(TitlesRc::class.java).equalTo("name", pbx_email.key.toString()).findFirst()
                    dataquery!!.icon = pbx_email.child("icon").value.toString()
                    dataquery.name = pbx_email.key.toString()
                    dataquery.value = pbx_email.child("value").value.toString()
                    dataquery.order = pbx_email.child("order").value.toString().toInt()
                }
            }
        }
    }
    fun update_Menu(data : menu_parameters,dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            data.active = dataSnapshot.child("active").value.toString().toInt()
            data.icon = dataSnapshot.child("icon").value.toString()
            data.order =dataSnapshot.child("order").value.toString().toInt()
            data.storyboardid = dataSnapshot.child("storyboardid").value.toString()
            data.sub_title_rc = dataSnapshot.child("sub_title_rc").value.toString()
            data.title_rc = dataSnapshot.child("title_rc").value.toString()
        }
    }
    fun create_Menu(dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            var data = realm.createObject<menu_parameters>(dataSnapshot.key)
            data.active = dataSnapshot.child("active").value.toString().toInt()
            data.icon = dataSnapshot.child("icon").value.toString()
            data.order =dataSnapshot.child("order").value.toString().toInt()
            data.storyboardid = dataSnapshot.child("storyboardid").value.toString()
            data.sub_title_rc = dataSnapshot.child("sub_title_rc").value.toString()
            data.title_rc = dataSnapshot.child("title_rc").value.toString()
        }
    }
    fun update_Country(data : Country,dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            data.name = dataSnapshot.key
            data.code = dataSnapshot.child("code").value.toString().toInt()
            data.icon = dataSnapshot.child("icon").value.toString()
            data.order =dataSnapshot.child("order").value.toString().toInt()
        }
    }
    fun create_Country(dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val country = realm.createObject(Country::class.java)
            country.name = dataSnapshot.key
            country.code = dataSnapshot.child("code").value.toString().toInt()
            country.icon = dataSnapshot.child("icon").value.toString()
            country.order =dataSnapshot.child("order").value.toString().toInt()
        }
    }

    fun create_contact_Parameters(dataSnapshot: DataSnapshot){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val contact = realm.createObject<Contact_parameters>(dataSnapshot.key.toString())
            contact.order = dataSnapshot.child("order").value.toString().toInt()

            for(pbx_email in dataSnapshot.children){
                if(pbx_email.key!="order"){
                    val titlesRc = realm.createObject(TitlesRc::class.java)
                    titlesRc.fk = dataSnapshot.key.toString()
                    titlesRc.icon = pbx_email.child("icon").value.toString()
                    titlesRc.name =pbx_email.key.toString()
                    titlesRc.value = pbx_email.child("value").value.toString()
                    titlesRc.order = pbx_email.child("order").value.toString().toInt()
                    contact.titles_rc.add(titlesRc)
                }

            }
        }
    }
}