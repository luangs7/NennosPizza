package br.com.luan2.nennospizza.retrofit

import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Ingredients
import br.com.luan2.nennospizza.data.model.PizzaResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ParseAPI {

    @GET("dokm7")
    fun getPizzaMain(): Observable<PizzaResponse>

    @GET("ozt3z")
    fun getIngredients(): Observable<List<Ingredients>>

    @GET("150da7")
    fun getDrinks(): Observable<List<Drinks>>

    @POST
    fun checkout(@Url url: String, @Body json: RequestBody): Observable<retrofit2.Response<Void>>

}
