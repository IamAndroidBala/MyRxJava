package com.android.myrxjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myrxjava.R
import kotlinx.android.synthetic.main.item_string.view.*

/**
 * Adapter to populate the data into Recyclerview
 */
class MyAdapter(private val mContext: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var mList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_string,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onItemBind(mList.get(position))
    }

    override fun getItemCount() = mList.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun onItemBind(stringMsg : String) {
            itemView.tvNameOfContentHolder.text = stringMsg
        }

    }

    fun setResults(result: ArrayList<String>) {
        mList = result
        notifyDataSetChanged()
    }

}