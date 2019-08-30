package com.example.burgerapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    val NUM_OF_ITEMS = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = NUM_OF_ITEMS

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val temp = position % 4

        holder.background.setBackgroundResource(
            when (temp) {
                0 -> R.color.backgroundGreen
                1 -> R.color.backgroundRed
                2 -> R.color.backgroundBrown
                3 -> R.color.backgroundCafa
                else -> 0
            }
        )
    }

    class ListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val background = itemView.rootLayout

    }

}