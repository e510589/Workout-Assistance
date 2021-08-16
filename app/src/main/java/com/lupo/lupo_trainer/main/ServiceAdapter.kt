package com.lupo.lupo_trainer.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.lupo.lupo_trainer.R
import com.lupo.lupo_trainer.main.serviceItem.ServiceItem
import kotlin.collections.ArrayList

class ServiceAdapter(serviceList:ArrayList<ServiceItem>, onServiceViewClicked: OnServiceClicked) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>(){

    private var service_list : ArrayList<ServiceItem> = serviceList
    private var onServiceViewClicked:OnServiceClicked = onServiceViewClicked

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView:ImageView
        var textView:TextView
        var container:ConstraintLayout

        init {
            imageView = itemView.findViewById(R.id.imageview_service)
            textView = itemView.findViewById(R.id.textview_name_serivce)
            container = itemView.findViewById(R.id.constrainlayout_sservice)
            ViewCompat.setTransitionName(container,"constrainlayout_sservice")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service,
                parent,
                false))
    }

    override fun getItemCount(): Int {
        return service_list.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {

        holder.textView.setText(service_list.get(position).serviceName)
        holder.imageView.setImageResource(service_list.get(position).serviceIconSource)
        holder.container.setOnClickListener {
            onServiceViewClicked.onClicked(position,holder.container)
        }
    }
}

interface OnServiceClicked{

    fun onClicked(pos:Int, sharedView:View)

}

