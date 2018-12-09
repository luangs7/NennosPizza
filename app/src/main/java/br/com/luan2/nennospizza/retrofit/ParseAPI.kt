package br.com.luan2.nennospizza.retrofit

import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.PizzaResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ParseAPI {

    @GET("dokm7")
    fun getPizzaMain(): Observable<PizzaResponse>

    @GET("ozt3z")
    fun getIngredients(): Observable<List<Ingredients>>

    @GET("150da7")
    fun getDrinks(): Observable<List<Drinks>>

}
