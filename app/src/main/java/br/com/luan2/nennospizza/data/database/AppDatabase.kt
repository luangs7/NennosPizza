package br.com.luan2.nennospizza.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.luan2.nennospizza.data.dao.CartDao
import br.com.luan2.nennospizza.data.model.Cart

@Database(entities = [Cart::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDAO() : CartDao

}