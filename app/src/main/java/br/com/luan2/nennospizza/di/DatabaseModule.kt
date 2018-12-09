package br.com.luan2.nennospizza.di

import androidx.room.Room
import br.com.luan2.nennospizza.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module



val databaseModule = module {
    single { Room.databaseBuilder(androidApplication(),AppDatabase::class.java,"Item")
        .fallbackToDestructiveMigration()
        .build()
    }

    single{ get<AppDatabase>().getCartDAO() }


}