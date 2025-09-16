package com.example.buzzwordsbottles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buzzwordsbottles.R
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.databinding.DescriptionLayoutBinding

class DescriptionAdapter : RecyclerView.Adapter<DescriptionAdapter.ViewHolder>() {
    var list = mutableListOf<Descriptions>()

    init {


    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val binding = DescriptionLayoutBinding.bind(itemView)

        // Click functionality
        init {
//            itemView.setOnClickListener{
//
//                listener.onClick(list[bindingAdapterPosition])
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.description_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.binding
        val item = list[position]

        list.add(Descriptions("asafegf"))
        list.add(Descriptions("dsrhjth"))
        list.add(Descriptions("kxvnodfjbn"))


        cardView.description.text = item.description
//        val normalPrice = item.normalPrice.toString()
//        val salePrice = item.salePrice.toString()
//
//        cardView.title.text = item.title
//        cardView.originalPrice.text = "£$normalPrice"
//        cardView.salePrice.text = "£$salePrice"
    }

    fun addItem(descriptions: Descriptions){
        list.add(descriptions)
        notifyItemInserted(list.lastIndex)
    }

}

