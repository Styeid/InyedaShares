package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.item_datafundw.view.*

class WDataFundAdapter (val context: Context, val datafundxw: List<DataFund>) :
    RecyclerView.Adapter<WDataFundAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WDataFundAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datafundw, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = datafundxw.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datafundxw[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(datafundw: DataFund) {
            itemView.tvwname.text = datafundw.namez
            itemView.tvwvalue.text = datafundw.valuez
            itemView.tvwpartys.text = datafundw.partysz
            itemView.tvwinlay.text = datafundw.inlay
        }
    }
}