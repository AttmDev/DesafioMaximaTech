package com.attm.maximatech.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.attm.maximatech.R

class MenuAdapter(
    private val optionList: List<MenuOptionModel>,
    private val listener: Listener) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById<ImageView>(R.id.it_menu_img)
        val text: TextView = itemView.findViewById<TextView>(R.id.it_menu_text)
    }

    interface Listener {
        fun onItemClick(item: MenuOptionModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_options, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = optionList[position]
        holder.image.setImageResource(option.optionImage)
        holder.text.setText(option.optionTitle)
        holder.itemView.setOnClickListener { listener.onItemClick(option) }
    }

    override fun getItemCount(): Int {
        return optionList.size
    }
}