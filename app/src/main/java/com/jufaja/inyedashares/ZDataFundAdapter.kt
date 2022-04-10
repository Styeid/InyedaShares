package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.item_datafundz.view.*

class ZDataFundAdapter (val context: Context, val datafundz: List<DataFund>) :
    RecyclerView.Adapter<ZDataFundAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZDataFundAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datafundz, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = datafundz.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datafundz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(datafundz: DataFund) {
            itemView.tvzname.text = datafundz.namez
            itemView.tvzvalue.text = datafundz.valuez
            itemView.tvzpartys.text = datafundz.partysz
            itemView.tvzinlay.text = datafundz.inlay
        }
    }
}