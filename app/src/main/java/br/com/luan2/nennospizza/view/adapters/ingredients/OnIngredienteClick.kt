package com.example.gitapi.adapter

import br.com.luan2.nennospizza.data.model.Ingredients

interface OnIngredienteClick {
    fun onIngrediente(ingredients: Ingredients)
}