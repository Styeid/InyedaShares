package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.item_dataposta.view.*
import kotlinx.android.synthetic.main.item_datapostd.view.*

class TriDataAdapter (val context: Context, val postz: List<DataPost>) :
    RecyclerView.Adapter<TriDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriDataAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datapostd, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postz.size


    override fun onBindViewHolder(holder: TriDataAdapter.ViewHolder, position: Int) {
        holder.bind(postz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postz: DataPost) {
            itemView.tvdaa.text = postz.aauser?.username
            itemView.tvdab.text = postz.abdate
            itemView.tvdac.text = postz.acpartyfunda.toString()
            itemView.tvdad.text = postz.adpartygrowa.toString()
            itemView.tvdae.text = postz.aepartyperca.toString()
            itemView.tvdaf.text = postz.afpartyvaluea.toString()
            itemView.tvdag.text = postz.agpartyfundb.toString()
            itemView.tvdah.text = postz.ahpartygrowb.toString()
            itemView.tvdai.text = postz.aipartypercb.toString()
            itemView.tvdaj.text = postz.ajpartyvalueb.toString()
            itemView.tvdak.text = postz.akpartyfundc.toString()
            itemView.tvdal.text = postz.alpartygrowc.toString()
            itemView.tvdam.text = postz.ampartypercc.toString()
            itemView.tvdan.text = postz.anpartyvaluec.toString()
            itemView.tvdao.text = postz.aopartyfundd.toString()
            itemView.tvdap.text = postz.appartygrowd.toString()
            itemView.tvdaq.text = postz.aqpartypercd.toString()
            itemView.tvdar.text = postz.arpartyvalued.toString()
        }
    }
}