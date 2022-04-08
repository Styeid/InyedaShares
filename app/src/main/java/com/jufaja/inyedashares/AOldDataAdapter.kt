package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.jufaja.inyedashares.models.DataFund
import kotlinx.android.synthetic.main.item_olddata.view.*

class AOldDataAdapter (val context: Context, val olddatazz: List<DataFund>) :
    RecyclerView.Adapter<AOldDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AOldDataAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_olddata, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = olddatazz.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(olddatazz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(oldatazz: DataFund) {
            itemView.tvvaluea2.text = oldatazz.valuez
            itemView.tvamountaxyz2.text = oldatazz.partysz
            itemView.tvtotalfundaa.text = oldatazz.totalfundz
            itemView.tvmultigrowa2.text = oldatazz.multigrowz
            itemView.tvtotalgrowa2.text = oldatazz.totalgrowz
            itemView.tvmultiperca2.text = oldatazz.multipercz
            itemView.tvtotalperca2.text = oldatazz.totalpercz
            itemView.tvpartyfunda2.text = oldatazz.partyfundz
            itemView.tvpartygrowa2.text = oldatazz.partygrowz
            itemView.tvpartyperca2.text = oldatazz.partypercz
        }
    }
}