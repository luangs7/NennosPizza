package br.com.luan2.nennospizza.core

import android.app.Application
import br.com.luan2.nennospizza.di.databaseModule
import br.com.luan2.nennospizza.di.repositoryModule
import br.com.luan2.nennospizza.di.rxThreadModule
import com.example.gitapi.di.appModule
import com.example.gitapi.di.networkModule
import org.koin.android.ext.android.startKoin


class App : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, networkModule,rxThreadModule, repositoryModule, databaseModule))
    }

}