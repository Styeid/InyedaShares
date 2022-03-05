package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.item_dataposta.view.*
import kotlinx.android.synthetic.main.item_datapostb.view.*

class FirstDataAdapter (val context: Context, val postz: List<DataPost>) :
    RecyclerView.Adapter<FirstDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstDataAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datapostb, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postz.size


    override fun onBindViewHolder(holder: FirstDataAdapter.ViewHolder, position: Int) {
        holder.bind(postz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postz: DataPost) {
            itemView.tvbaa.text = postz.aauser?.username
            itemView.tvbab.text = postz.abdate
            itemView.tvbas.text = postz.astotalfunda.toString()
            itemView.tvbat.text = postz.atmultigrowa.toString()
            itemView.tvbau.text = postz.autotalgrowa.toString()
            itemView.tvbav.text = postz.avmultiperca.toString()
            itemView.tvbaw.text = postz.awtotalperca.toString()
            itemView.tvbax.text = postz.axtotalfundb.toString()
            itemView.tvbay.text = postz.aymultigrowb.toString()
            itemView.tvbaz.text = postz.aztotalgrowb.toString()
            itemView.tvbba.text = postz.bamultipercb.toString()
            itemView.tvbbb.text = postz.bbtotalpercb.toString()
        }
    }
}