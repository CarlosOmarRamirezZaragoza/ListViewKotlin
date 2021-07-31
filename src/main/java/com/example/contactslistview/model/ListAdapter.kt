package com.example.contactslistview.model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.contactslistview.R

class ListAdapter(private val context: Activity, arrayList: ArrayList<String>) :
    ArrayAdapter<String> (context, R.layout.list_item, arrayList) {
    var items: ArrayList<String>? = null
    var itemsCopy: ArrayList<String>? = null

    init {
        this.items = ArrayList(arrayList)
        this.itemsCopy = arrayList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val userName : TextView = view.findViewById(R.id.person_name)

        userName.text = items!![position]

        return view
    }

    override fun getCount(): Int {
        return this.items?.count()!!
    }

    fun getUsers():ArrayList<String>?{
        return items
    }

    fun filter(str:String){
        items?.clear()
        if(str.isEmpty()){
            items = ArrayList(itemsCopy!!)
            notifyDataSetChanged()
            return
        }

        var search = str
        search = search.toLowerCase()

        for(item in itemsCopy!!){
            val name = item.toLowerCase()

            if(name.contains(search)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }
}