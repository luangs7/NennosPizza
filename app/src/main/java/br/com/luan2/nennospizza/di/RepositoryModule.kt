package br.com.luan2.nennospizza.di

import br.com.luan2.nennospizza.data.dao.CartDao
import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.data.model.repositories.DrinkRepository
import br.com.luan2.nennospizza.data.model.repositories.IngredientsRepository
import br.com.luan2.nennospizza.data.model.repositories.PizzaRepository
import br.com.luan2.nennospizza.retrofit.ParseAPI
import br.com.luan2.nennospizza.rx.RxThread
import org.koin.dsl.module.module

val repositoryModule = module {
    single { PizzaRepository(get() as RxThread, get() as ParseAPI) }
    single { DrinkRepository(get() as RxThread, get() as ParseAPI) }
    single { CartRepository(get() as CartDao, get() as ParseAPI, get() as RxThread) }
    single { IngredientsRepository( get() as ParseAPI, get() as RxThread) }
}