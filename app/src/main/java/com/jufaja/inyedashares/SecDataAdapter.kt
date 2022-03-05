package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.item_dataposta.view.*
import kotlinx.android.synthetic.main.item_datapostc.view.*

class SecDataAdapter (val context: Context, val postz: List<DataPost>) :
    RecyclerView.Adapter<SecDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecDataAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_datapostc, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postz.size


    override fun onBindViewHolder(holder: SecDataAdapter.ViewHolder, position: Int) {
        holder.bind(postz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postz: DataPost) {
            itemView.tvcaa.text = postz.aauser?.username
            itemView.tvcab.text = postz.abdate
            itemView.tvcbc.text = postz.bctotalfundc.toString()
            itemView.tvcbd.text = postz.bdmultigrowc.toString()
            itemView.tvcbe.text = postz.betotalgrowc.toString()
            itemView.tvcbf.text = postz.bfmultipercc.toString()
            itemView.tvcbg.text = postz.bgtotalpercc.toString()
            itemView.tvcbh.text = postz.bhtotalfundd.toString()
            itemView.tvcbi.text = postz.bimultigrowd.toString()
            itemView.tvcbj.text = postz.bjtotalgrowd.toString()
            itemView.tvcbk.text = postz.bkmultipercd.toString()
            itemView.tvcbl.text = postz.bltotalpercd.toString()
        }
    }
}