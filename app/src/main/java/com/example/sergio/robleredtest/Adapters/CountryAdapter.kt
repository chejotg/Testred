package com.example.sergio.robleredtest.Adapters

import io.realm.RealmBaseAdapter
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import com.example.sergio.robleredtest.ModelRealm.Country
import com.example.sergio.robleredtest.R
import io.realm.RealmResults
import kotlinx.android.synthetic.main.content_login.*


class CountryAdapter(val activity: Activity, val realmCollection: RealmResults<Country>): RealmBaseAdapter<Country>(realmCollection) {


    var countries = realmCollection
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getItem(position: Int): Country? {
        return super.getItem(position)

    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(activity).inflate(R.layout.country_item, parent, false)
        //val row = inflater.inflate(R.xml.row, parent, false)
        val icon = inflater.findViewById(R.id.Country_img) as ImageView
        for (country in realmCollection) {
            icon.setImageResource(R.drawable.gt)
            inflater.tag=country.code
        }


        return inflater

    }
}

