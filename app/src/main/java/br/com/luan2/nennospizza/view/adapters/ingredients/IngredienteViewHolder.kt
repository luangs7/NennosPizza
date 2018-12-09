package com.example.gitapi.adapter


import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.nennospizza.R
import br.com.luan2.nennospizza.data.model.Ingredients

class IngredienteViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    val nameIngrediente by lazy { view.findViewById(R.id.nameIngredient) as TextView }
    val check by lazy { view.findViewById(R.id.check) as ImageView }
    val price by lazy { view.findViewById(R.id.price) as TextView }
    val itemLayout by lazy { view.findViewById(R.id.item) as RelativeLayout }

    fun bind(item: Ingredients, onItemClicked: OnIngredienteClick) {

        nameIngrediente.text = item.name
        price.text = "$ ${item.price}"

        if(item.isSelected) check.visibility = View.VISIBLE else check.visibility = View.INVISIBLE

        itemLayout.setOnClickListener { onItemClicked.onIngrediente(item) }

    }
}
