package com.example.sergio.robleredtest.Utils

import android.util.Log
import com.google.firebase.database.DataSnapshot


class ParseData() {


    fun ParseCountry( dataSnapshot: DataSnapshot):Boolean{
        val data = Transactions().findfirst_Country(dataSnapshot.key.toString())
        if(data!=null){
            Transactions().update_Country(data,dataSnapshot)
        }else{
            Transactions().create_Country(dataSnapshot)
        }
        if(Transactions().findfirst_Country(dataSnapshot.key.toString())!=null){
            return true
        }
        return false
    }
//adb pull /data/data/com.example.sergio.robleredtest/files/default.realm
    fun ParseContact( dataSnapshot: DataSnapshot):Boolean{
            val data = Transactions().findfirst_contact(dataSnapshot.key.toString())
            if(data!=null){
                Log.d("query before",data!!.toString())
                Transactions().update_contac_Parameters(data,dataSnapshot)
            }else{
                Transactions().create_contact_Parameters(dataSnapshot)

            }
    if(Transactions().findfirst_contact(dataSnapshot.key.toString())!=null){
        return true
    }
    return false
    }

    fun ParseMenu(dataSnapshot: DataSnapshot):Boolean{
        val data = Transactions()!!.findfirst_Menu(dataSnapshot.key.toString())
        if(data!=null){
            Log.d("query before",data.toString())
            Transactions().update_Menu(data,dataSnapshot)
        }else{
            Transactions().create_Menu(dataSnapshot)
        }
        if(Transactions().findfirst_Menu(dataSnapshot.key.toString())!=null){
            return true
        }
        return false
    }
}