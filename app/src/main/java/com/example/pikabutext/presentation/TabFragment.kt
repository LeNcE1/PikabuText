package com.example.pikabutext.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pikabutext.R
import com.example.pikabutext.feature.news.presentation.FavoritesFragment
import com.example.pikabutext.feature.news.presentation.NewsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : Fragment(R.layout.fragment_tab) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = TAB_COUNT

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    FIRST_TAB -> NewsFragment()
                    else -> FavoritesFragment()
                }
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(
                when (position) {
                    FIRST_TAB -> R.string.news
                    else -> R.string.favorites
                }
            )
        }.attach()
    }

    companion object {
        private const val TAB_COUNT = 2
        private const val FIRST_TAB = 0
    }
}