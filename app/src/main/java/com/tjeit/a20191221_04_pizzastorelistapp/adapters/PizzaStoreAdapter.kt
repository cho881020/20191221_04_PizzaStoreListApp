package com.tjeit.a20191221_04_pizzastorelistapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjeit.a20191221_04_pizzastorelistapp.R
import com.tjeit.a20191221_04_pizzastorelistapp.datas.PizzaStore

class PizzaStoreAdapter(context:Context, resId:Int, list: ArrayList<PizzaStore>) : ArrayAdapter<PizzaStore>(context, resId, list) {

    val mContext = context
    val mList = list
    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.pizza_store_list_item, null)
        }
        val row = tempRow!!

        val data = mList.get(position)

        val logoImg = row.findViewById<ImageView>(R.id.logoImg)
        val nameTxt = row.findViewById<TextView>(R.id.nameTxt)

        Glide.with(mContext).load(data.logoUrl).into(logoImg)

        nameTxt.text = data.storeName

        return row
    }


}