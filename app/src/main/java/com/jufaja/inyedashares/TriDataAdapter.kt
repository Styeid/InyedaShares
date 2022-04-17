package com.jufaja.inyedashares

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
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
            itemView.tvdab.text = DateUtils.formatDateTime(context, postz.abdate, 4)
            itemView.tvdac.text = postz.acpartyfunda.toString()
            itemView.tvdad.text = postz.adpartygrowa.toString()
                //if (postz.adpartygrowa <= 0.000.toString())
                //    itemView.tvdad.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.adpartygrowa > 0.0000.toString())
                //    itemView.tvdad.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.adpartygrowa == 0.0)
                //    itemView.tvdad.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdae.text = postz.aepartyperca.toString()
                //if (postz.aepartyperca <= 0.00.toString())
                //    itemView.tvdae.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.aepartyperca > 0.00.toString())
                //    itemView.tvdae.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.aepartyperca == 0.0)
                //    itemView.tvdae.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdaf.text = postz.afpartyvaluea.toString()
            itemView.tvdag.text = postz.agpartyfundb.toString()
            itemView.tvdah.text = postz.ahpartygrowb.toString()
                //if (postz.ahpartygrowb <= 0.0000.toString())
                //    itemView.tvdah.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.ahpartygrowb > 0.0000.toString())
                //    itemView.tvdah.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.ahpartygrowb == 0.0)
                //    itemView.tvdah.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdai.text = postz.aipartypercb.toString()
                //if (postz.aipartypercb <= 0.00.toString())
                //    itemView.tvdai.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.aipartypercb > 0.00.toString())
                //    itemView.tvdai.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.aipartypercb == 0.0)
                //    itemView.tvdai.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdaj.text = postz.ajpartyvalueb.toString()
            itemView.tvdak.text = postz.akpartyfundc.toString()
            itemView.tvdal.text = postz.alpartygrowc.toString()
                //if (postz.alpartygrowc <= 0.0000.toString())
                //    itemView.tvdal.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.alpartygrowc > 0.0000.toString())
                //    itemView.tvdal.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.alpartygrowc == 0.0)
                //    itemView.tvdal.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdam.text = postz.ampartypercc.toString()
                //if (postz.ampartypercc <= 0.00.toString())
                //    itemView.tvdam.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.ampartypercc > 0.00.toString())
                //    itemView.tvdam.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.ampartypercc == 0.0)
                //    itemView.tvdam.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdan.text = postz.anpartyvaluec.toString()
            itemView.tvdao.text = postz.aopartyfundd.toString()
            itemView.tvdap.text = postz.appartygrowd.toString()
                //if (postz.appartygrowd <= 0.0000.toString())
                //    itemView.tvdap.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.appartygrowd > 0.0000.toString())
                //    itemView.tvdap.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.appartygrowd == 0.0)
                //    itemView.tvdap.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdaq.text = postz.aqpartypercd.toString()
                //if (postz.aqpartypercd <= 0.00.toString())
                //    itemView.tvdaq.setTextColor(context.resources.getColor(R.color.color_below_zero))
                //if (postz.aqpartypercd > 0.00.toString())
                //    itemView.tvdaq.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.aqpartypercd == 0.0)
                //    itemView.tvdaq.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvdar.text = postz.arpartyvalued.toString()
        }
    }
}