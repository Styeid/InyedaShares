package com.jufaja.inyedashares

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jufaja.inyedashares.models.DataPost
import kotlinx.android.synthetic.main.item_dataposta.view.*

class HomeDataAdapter (val context: Context, val postz: List<DataPost>) :
    RecyclerView.Adapter<HomeDataAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dataposta, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postz.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postz[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private lateinit var tvabo: TextView

        fun bind(postz: DataPost) {



            itemView.tvaaa.text = postz.aauser?.username
            itemView.tvaab.text = postz.abdate
            itemView.tvabm.text = postz.bmtotalabcd.toString()
            itemView.tvabn.text = postz.bnmultiprofi.toString()
            itemView.tvabo.text = postz.bototalprofi.toString()
            itemView.tvabp.text = postz.bppercentagex.toString()
            itemView.tvabq.text = postz.bqpercentagey.toString()
            //colorDice(postz.bnmultiprofi)
            //colorDice(postz.bototalprofi)

            if (postz.bnmultiprofi < 0.0)
                itemView.tvabn.setTextColor(context.resources.getColor(R.color.color_below_zero))
            //    //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_below_zero))
            if (postz.bnmultiprofi > 0.0)
                itemView.tvabn.setTextColor(context.resources.getColor(R.color.color_above_zero))
            //    //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_above_zero))
            if (postz.bnmultiprofi == 0.0)
               itemView.tvabn.setTextColor(context.resources.getColor(R.color.color_equal_zero))
            //    //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_equal_zero))
        }

        //private fun colorDice(Datapotx: Double) {
        //    val opo = itemView.tvabn
        //    if (Datapotx > 0.0)
        //        opo.setTextColor(context.resources.getColor(R.color.color_below_zero))
        //        //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_below_zero))
        //    if (Datapotx < 0.0)
        //        itemView.tvabn.setTextColor(context.resources.getColor(R.color.color_above_zero))
        //        //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_above_zero))
        //    if (Datapotx == 0.0)
        //        itemView.tvabn.setTextColor(context.resources.getColor(R.color.color_equal_zero))
        //        //itemView.tvabo.setTextColor(context.resources.getColor(R.color.color_equal_zero))

        //}


    }
}