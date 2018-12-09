package com.example.gitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Ingredients

class IngredienteAdapter constructor(var items: List<Ingredients>,
                                     private val onClick: OnIngredienteClick,
                                     private val context:Context)
    : RecyclerView.Adapter<IngredienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_ingredients, parent, false)
        return IngredienteViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: IngredienteViewHolder, position: Int) {
        val repository = items[position]
        holder.bind(repository, onClick)
    }

    override fun getItemCount(): Int = items.size

    fun refreshItem(items: List<Ingredients>){
        this.items = items
        notifyDataSetChanged()
    }


}
