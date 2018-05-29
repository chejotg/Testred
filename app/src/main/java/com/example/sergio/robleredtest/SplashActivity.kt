package com.example.sergio.robleredtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import android.widget.Toast
import com.example.sergio.robleredtest.Utils.Utils
import com.google.firebase.database.*
import io.realm.Realm
import io.realm.RealmConfiguration


class SplashActivity : AppCompatActivity() {
    var flag = false
    val utils = Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setvaluesRemoteConfig()
        Realm.init(this)
        var config =  RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance()
// Use the config


       // val welcomeMessage = mFirebaseRemoteConfig.getString("tabbar_home")
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val listeners =Listeners()
        flag = listeners.get_menu_parameters(database)
        flag = listeners.get_contact_parameters(database)
        flag = listeners.get_country_parameters(database)



        val intent = Intent(this,MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        // Read from the database Firebase


        //SystemClock.sleep(3000)



    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun setvaluesRemoteConfig(){
        //recepcion de datos de firebase y actualizacion de base de datos
        var mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
        // will use fetch data from the Remote Config service, rather than cached parameter values,
        // if cached parameter values are more than cacheExpiration seconds old.
        // See Best Practices in the README for more information.
        mFirebaseRemoteConfig.fetch(5)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@SplashActivity, "Fetch Succeeded",
                                Toast.LENGTH_SHORT).show()
                        // mFirebaseRemoteConfig.getString("tabbar_home")
                        // After config data is successfully fetched, it must be activated before newly fetched
                        // values are returned.
                        mFirebaseRemoteConfig.activateFetched()
                    } else {
                        Toast.makeText(this@SplashActivity, "Fetch Failed",
                                Toast.LENGTH_SHORT).show()
                        mFirebaseRemoteConfig.setDefaults(R.xml.remoteconfig)
                    }

                }
    }
}
