package com.example.sergio.robleredtest.Utils

import android.content.Context
import android.widget.Toast
import com.example.sergio.robleredtest.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import org.json.JSONException
import org.json.JSONObject

object Utils {

    /*@Throws(JSONException::class)
    fun getObject(tagName: String, dataSnapshot: DataSnapshot): JSONObject {
        val jObj = dataSnapshot.child(tagName).value
        return jObj
    }*/

    fun getString(tagName: String, dataSnapshot: DataSnapshot): String{
        return dataSnapshot.child(tagName).value.toString()
    }

    fun getFloat(tagName: String, dataSnapshot: DataSnapshot): Float{
        var float= dataSnapshot.child(tagName).value as Float
        return float
    }


    fun getDouble(tagName: String, dataSnapshot: DataSnapshot): Double{
        return dataSnapshot.child(tagName).value as Double
    }


    fun getInt(tagName: String, dataSnapshot: DataSnapshot): Int{
        return dataSnapshot.child(tagName).value as Int
    }


    fun getLong(tagName: String, dataSnapshot: DataSnapshot): Long{
        return dataSnapshot.child(tagName).value as Long
    }

}