package br.com.luan2.nennospizza.data.dao

import androidx.room.*
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Pizza
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CartDao {

    @Query("SELECT * FROM CartTable")
    fun getAll(): Single<List<Cart>>

    @Query("SELECT * FROM Pizza WHERE Pizza.idCart = :id")
    fun getAllPizzas(id:Int): Single<List<Pizza>>

    @Query("SELECT * FROM Drinks WHERE Drinks.idCart = :id")
    fun getAllDrinks(id:Int): Single<List<Drinks>>

    @Query("SELECT * FROM CartTable WHERE CartTable.id = :id")
    fun getId(id:Int): Maybe<Cart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: Cart): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPizza(item: Pizza): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(item: Drinks): Long

    @Delete
    fun delete(item: Cart)

    @Delete
    fun delete(item: Pizza)

    @Delete
    fun delete(item: Drinks)

    @Query("DELETE FROM Pizza")
    fun nukeTablePizzas()

    @Query("DELETE FROM Drinks")
    fun nukeTableDrinks()
}