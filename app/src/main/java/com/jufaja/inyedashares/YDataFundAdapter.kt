package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.item_datafundy.view.*

class YDataFundAdapter (val context: Context, val datafundy: List<DataFund>) :
    RecyclerView.Adapter<YDataFundAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YDataFundAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datafundy, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = datafundy.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datafundy[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(datafundy: DataFund) {
            itemView.tvyname.text = datafundy.namez
            itemView.tvyvalue.text = datafundy.valuez
            itemView.tvypartys.text = datafundy.partysz
            itemView.tvyinlay.text = datafundy.inlay
        }
    }
}