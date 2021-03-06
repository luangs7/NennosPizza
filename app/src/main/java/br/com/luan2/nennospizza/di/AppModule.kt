package com.example.gitapi.di

import br.com.luan2.nennospizza.data.model.repositories.CartRepository
import br.com.luan2.nennospizza.data.model.repositories.DrinkRepository
import br.com.luan2.nennospizza.data.model.repositories.IngredientsRepository
import br.com.luan2.nennospizza.data.model.repositories.PizzaRepository
import br.com.luan2.nennospizza.di.RxThreadModule
import br.com.luan2.nennospizza.rx.RxThread
import br.com.luan2.nennospizza.view.activities.cart.CartActivityInteractor
import br.com.luan2.nennospizza.view.activities.cart.CartActivityPresenter
import br.com.luan2.nennospizza.view.activities.custompizza.CustomPizzaActivityInteractor
import br.com.luan2.nennospizza.view.activities.custompizza.CustomPizzaActivityPresenter
import br.com.luan2.nennospizza.view.activities.drinks.DrinksActivityInteractor
import br.com.luan2.nennospizza.view.activities.drinks.DrinksActivityPresenter
import br.com.luan2.nennospizza.view.activities.main.MainActivityInteractor
import br.com.luan2.nennospizza.view.activities.main.MainActivityPresenter
import br.com.luan2.nennospizza.view.activities.pizzadetails.PizzaDetailsActivityInteractor
import br.com.luan2.nennospizza.view.activities.pizzadetails.PizzaDetailsActivityPresenter
import org.koin.dsl.module.module


val appModule = module {
    single { MainActivityPresenter(get() as MainActivityInteractor) }
    single { MainActivityInteractor(get() as PizzaRepository, get() as CartRepository) }
    single { RxThread(get(RxThreadModule.mainThread), get(RxThreadModule.ioThread)) }

    //drink activity
    single { DrinksActivityPresenter(get() as DrinksActivityInteractor) }
    single { DrinksActivityInteractor(get() as DrinkRepository, get() as CartRepository) }

    //cart activity
    single { CartActivityPresenter(get() as CartActivityInteractor) }
    single { CartActivityInteractor(get() as CartRepository) }

    //pizza details
    single { PizzaDetailsActivityPresenter(get() as PizzaDetailsActivityInteractor) }
    single { PizzaDetailsActivityInteractor(get() as IngredientsRepository, get() as CartRepository) }

    //custom pizza
    single { CustomPizzaActivityPresenter(get() as CustomPizzaActivityInteractor) }
    single { CustomPizzaActivityInteractor(get() as IngredientsRepository, get() as CartRepository) }
}