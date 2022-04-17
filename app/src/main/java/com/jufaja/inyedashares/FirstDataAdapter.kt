package com.jufaja.inyedashares

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
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
            itemView.tvbab.text = DateUtils.formatDateTime(context, postz.abdate, 4)
            itemView.tvbas.text = postz.astotalfunda.toString()
            itemView.tvbat.text = postz.atmultigrowa.toString()
                if (postz.atmultigrowa < 0.0.toString())
                    itemView.tvbat.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.atmultigrowa > 0.0.toString())
                    itemView.tvbat.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.atmultigrowa == 0.0)
                //    itemView.tvbat.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbau.text = postz.autotalgrowa.toString()
                if (postz.autotalgrowa < 0.0.toString())
                    itemView.tvbau.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.autotalgrowa > 0.0.toString())
                    itemView.tvbau.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.autotalgrowa == 0.0)
            itemView.tvbav.text = postz.avmultiperca.toString()
                if (postz.avmultiperca < 0.0.toString())
                    itemView.tvbav.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.avmultiperca > 0.0.toString())
                    itemView.tvbav.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.avmultiperca == 0.0)
                //    itemView.tvbav.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbaw.text = postz.awtotalperca.toString()
                if (postz.awtotalperca < 0.0.toString())
                    itemView.tvbaw.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.awtotalperca > 0.0.toString())
                    itemView.tvbaw.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.awtotalperca == 0.0)
                //    itemView.tvbaw.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbax.text = postz.axtotalfundb.toString()
            itemView.tvbay.text = postz.aymultigrowb.toString()
                if (postz.aymultigrowb < 0.0.toString())
                    itemView.tvbay.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.aymultigrowb > 0.0.toString())
                    itemView.tvbay.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.aymultigrowb == 0.0)
                //    itemView.tvbay.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbaz.text = postz.aztotalgrowb.toString()
                if (postz.aztotalgrowb < 0.0.toString())
                    itemView.tvbaz.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.aztotalgrowb > 0.0.toString())
                    itemView.tvbaz.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.aztotalgrowb == 0.0)
                //    itemView.tvbaz.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbba.text = postz.bamultipercb.toString()
                if (postz.bamultipercb < 0.0.toString())
                    itemView.tvbba.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bamultipercb > 0.0.toString())
                    itemView.tvbba.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.bamultipercb == 0.0)
                //    itemView.tvbba.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            itemView.tvbbb.text = postz.bbtotalpercb.toString()
                if (postz.bbtotalpercb < 0.0.toString())
                    itemView.tvbbb.setTextColor(context.resources.getColor(R.color.color_below_zero))
                if (postz.bbtotalpercb > 0.0.toString())
                    itemView.tvbbb.setTextColor(context.resources.getColor(R.color.color_above_zero))
                //if (postz.bbtotalpercb == 0.0)
                //    itemView.tvbbb.setTextColor(context.resources.getColor(R.color.color_equal_zero))
        }
    }
}