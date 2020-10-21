package com.example.pikabutext.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pikabutext.BuildConfig
import com.example.pikabutext.R
import com.example.pikabutext.RouteModule
import com.example.pikabutext.TabScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy

class MainActivity : AppCompatActivity() {
    private val router: Router by inject()

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator = SupportAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        KTP.openScope(BuildConfig.APP_SCOPE)
            .installModules(
                RouteModule()
            )
            .closeOnDestroy(this)
            .inject(this)
        router.newRootScreen(TabScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        KTP.closeScope(BuildConfig.APP_SCOPE)
        super.onDestroy()
    }
}