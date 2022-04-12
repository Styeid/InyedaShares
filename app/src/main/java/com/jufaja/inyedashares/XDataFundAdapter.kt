package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.item_datafundx.view.*


class XDataFundAdapter (val context: Context, val datafundx: List<DataFund>) :
    RecyclerView.Adapter<XDataFundAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XDataFundAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datafundx, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = datafundx.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datafundx[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(datafundx: DataFund) {
            itemView.tvxname.text = datafundx.namez
            itemView.tvxvalue.text = datafundx.valuez
            itemView.tvxpartys.text = datafundx.partysz
            itemView.tvxinlay.text = datafundx.inlay
        }
    }
}