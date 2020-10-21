package com.example.pikabutext

import android.app.Application
import com.example.pikabutext.feature.database.DataBaseModule
import toothpick.ktp.KTP

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KTP.openScope(BuildConfig.APP_SCOPE)
            .installModules(
                RouteModule(),
                DataBaseModule(applicationContext)
            )
            .inject(this)
    }
}
