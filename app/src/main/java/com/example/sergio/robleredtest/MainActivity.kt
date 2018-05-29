package com.example.sergio.robleredtest

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.TransactionTooLargeException
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sergio.robleredtest.Utils.Transactions
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var drawerLayout : DrawerLayout? = null
    var navigationView : NavigationView?= null
    var mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    var realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val menus = Transactions().findAllMenu()
        if(menus!=null){
            for(menu in menus){

            }
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationDrawer)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.abc_ic_menu_share_mtrl_alpha)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        informacion.text= intent.getStringExtra("value")
        drawerLayout!!.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }

        })

        navigationView!!.setNavigationItemSelectedListener{ item ->
            var gestorDeFragmentos = false
            var fragment : android.support.v4.app.Fragment?=null
            when (item.itemId){

                R.id.menu_home -> {
                    //fragment = PerfilFragment()
                    gestorDeFragmentos=true
                }
                R.id.menu_emergency -> {
                  //  fragment = FotosFragment()
                    gestorDeFragmentos=true
                }
                R.id.menu_contact -> {
                   // fragment = EmailFragment()
                    gestorDeFragmentos=true
                }
                R.id.menu_olvidar -> {
                    Toast.makeText(this,"borrar", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_ayuda -> {
                    Toast.makeText(this,"Ayuda", Toast.LENGTH_SHORT).show()
                }

            }
            if(gestorDeFragmentos){
               // cambiarFragmento(fragment,item)
                drawerLayout!!.closeDrawers()
            }

            true
        }
        fun setvaluesRemoteConfig( context: Context){
            //recepcion de datos de firebase y actualizacion de base de datos
            var mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
            // will use fetch data from the Remote Config service, rather than cached parameter values,
            // if cached parameter values are more than cacheExpiration seconds old.
            // See Best Practices in the README for more information.
            mFirebaseRemoteConfig.fetch(5)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@MainActivity, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show()
                            // mFirebaseRemoteConfig.getString("tabbar_home")
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched()
                        } else {
                            Toast.makeText(this@MainActivity, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show()
                            mFirebaseRemoteConfig.setDefaults(R.xml.remoteconfig)
                        }

                    }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId){

            android.R.id.home->{

                drawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
