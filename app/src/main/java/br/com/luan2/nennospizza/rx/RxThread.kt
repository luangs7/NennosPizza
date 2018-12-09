package br.com.luan2.nennospizza.rx

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler


class RxThread  constructor(private val mainSchedulers: Scheduler, private val ioScheduler: Scheduler) {

    fun <T> applyAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(ioScheduler)
                    .observeOn(mainSchedulers)
        }
    }
}