package com.example.gitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Pizza

class PizzaAdapter constructor(private val items: List<Pizza>,
                               private val clickPizza: OnClickPizza,
                               private val context:Context)
    : RecyclerView.Adapter<PizzaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        return PizzaViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val repository = items[position]
        holder.bind(repository, clickPizza)
    }

    override fun getItemCount(): Int = items.size

}
