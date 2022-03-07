package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.item_dataposta.view.*
import kotlinx.android.synthetic.main.item_datapostc.view.*
import kotlinx.android.synthetic.main.item_datapostd.view.*

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
                if (postz.bdmultigrowc < 0.0)
                    itemView.tvcbd.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bdmultigrowc > 0.0)
                    itemView.tvcbd.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bdmultigrowc == 0.0)
                    itemView.tvcbd.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbe.text = postz.betotalgrowc.toString()
                if (postz.betotalgrowc < 0.0)
                    itemView.tvcbe.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.betotalgrowc > 0.0)
                    itemView.tvcbe.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.betotalgrowc == 0.0)
                    itemView.tvcbe.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbf.text = postz.bfmultipercc.toString()
                if (postz.bfmultipercc < 0.0)
                    itemView.tvcbf.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bfmultipercc > 0.0)
                    itemView.tvcbf.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bfmultipercc == 0.0)
                    itemView.tvcbf.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbg.text = postz.bgtotalpercc.toString()
                if (postz.bgtotalpercc < 0.0)
                    itemView.tvcbg.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bgtotalpercc > 0.0)
                    itemView.tvcbg.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bgtotalpercc == 0.0)
                    itemView.tvcbg.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbh.text = postz.bhtotalfundd.toString()
            itemView.tvcbi.text = postz.bimultigrowd.toString()
                if (postz.bimultigrowd < 0.0)
                    itemView.tvcbi.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bimultigrowd > 0.0)
                    itemView.tvcbi.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bimultigrowd == 0.0)
                    itemView.tvcbi.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbj.text = postz.bjtotalgrowd.toString()
                if (postz.bjtotalgrowd < 0.0)
                    itemView.tvcbj.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bjtotalgrowd > 0.0)
                    itemView.tvcbj.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bjtotalgrowd == 0.0)
                    itemView.tvcbj.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbk.text = postz.bkmultipercd.toString()
                if (postz.bkmultipercd < 0.0)
                    itemView.tvcbk.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bkmultipercd > 0.0)
                    itemView.tvcbk.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bkmultipercd == 0.0)
                    itemView.tvcbk.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvcbl.text = postz.bltotalpercd.toString()
                if (postz.bltotalpercd < 0.0)
                    itemView.tvcbl.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bltotalpercd > 0.0)
                    itemView.tvcbl.setTextColor(context.resources.getColor(R.color.color_above_zero))
                if (postz.bltotalpercd == 0.0)
                    itemView.tvcbl.setTextColor(context.resources.getColor(R.color.color_equal_zero))
        }
    }
}