package br.com.luan2.nennospizza.data.dao

import androidx.room.*
import br.com.luan2.nennospizza.data.model.Cart
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CartDao {

    @Query("SELECT * FROM CartTable")
    fun getAll(): Single<List<Cart>>

    @Query("SELECT * FROM CartTable WHERE CartTable.id = :id")
    fun getId(id:Int): Maybe<Cart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg itens: Cart)

    @Delete
    fun delete(item: Cart)

    @Query("DELETE FROM CartTable")
    fun nukeTable()
}