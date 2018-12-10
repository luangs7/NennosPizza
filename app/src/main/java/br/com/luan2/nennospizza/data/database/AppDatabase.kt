package br.com.luan2.nennospizza.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.luan2.nennospizza.data.dao.CartDao
import br.com.luan2.nennospizza.data.model.Cart
import br.com.luan2.nennospizza.data.model.Drinks
import br.com.luan2.nennospizza.data.model.Pizza

@Database(entities = [Cart::class, Pizza::class, Drinks::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDAO() : CartDao

}