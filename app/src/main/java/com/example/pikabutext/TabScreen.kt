package com.example.pikabutext

import androidx.fragment.app.Fragment
import com.example.pikabutext.presentation.TabFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class TabScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = TabFragment()
}
