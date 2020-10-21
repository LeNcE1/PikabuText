package com.example.pikabutext

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class RouteModule : Module() {
    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(Cicerone::class.java).toInstance(cicerone)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}