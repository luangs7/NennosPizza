package com.example.gitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Drinks

class DrinkAdapter constructor(private val items: List<Drinks>,
                               private val onClick: OnClickDrink,
                               private val context:Context)
    : RecyclerView.Adapter<DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_drinks, parent, false)
        return DrinkViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val repository = items[position]
        holder.bind(repository, onClick)
    }

    override fun getItemCount(): Int = items.size

}
