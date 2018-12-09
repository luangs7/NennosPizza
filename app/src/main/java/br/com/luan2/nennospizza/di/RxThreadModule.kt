package br.com.luan2.nennospizza.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

open class RxThreadModule {

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
    }



}

 val rxThreadModule = module {
    single(RxThreadModule.mainThread) { AndroidSchedulers.mainThread() }
    single(RxThreadModule.ioThread) { Schedulers.io() }
}
