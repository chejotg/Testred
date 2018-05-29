package com.example.sergio.robleredtest

import android.util.Log
import com.example.sergio.robleredtest.Utils.ParseData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Listeners {
    var flag = false
    fun get_country_parameters( database: FirebaseDatabase):Boolean{
        val myRef = database.getReference("settings/country")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (messageSnapshot in dataSnapshot.children) {
                    flag = ParseData().ParseCountry(messageSnapshot)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException())
            }

        })
        return flag
    }
    fun get_register_parameters( database: FirebaseDatabase):Boolean{
        val myRef = database.getReference("device/registrer")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (messageSnapshot in dataSnapshot.children) {
                    Log.d("registro",messageSnapshot.value.toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException())
            }

        })
        return flag
    }
    fun get_contact_parameters(database: FirebaseDatabase):Boolean{
        val myRef = database.getReference("settings/contact")
        val TAG = "prueba"
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("Firebase","value contact is:"+dataSnapshot.value)
                for (messageSnapshot in dataSnapshot.children) {
                    flag = ParseData().ParseContact(messageSnapshot)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException())
            }
        })
        return flag
    }
    fun get_menu_parameters(database: FirebaseDatabase): Boolean{
        val myRef = database.getReference("home")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (messageSnapshot in dataSnapshot.children) {
                   flag = ParseData().ParseMenu(messageSnapshot)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException())
            }
        })
        return flag
    }
}