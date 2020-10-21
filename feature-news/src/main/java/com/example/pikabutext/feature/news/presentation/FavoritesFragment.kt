package com.example.pikabutext.feature.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pikabutext.feature.news.BuildConfig
import com.example.pikabutext.feature.news.NewsModule
import com.example.pikabutext.feature.news.R
import com.example.pikabutext.feature.news.databinding.FragmentFavoritesBinding
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class FavoritesFragment : Fragment() {
    private val viewModel: FavoritesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavoritesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        binding.viewModel = viewModel
        val layoutManager = LinearLayoutManager(requireContext())
        binding.list.layoutManager = layoutManager
        binding.list.adapter = viewModel.adapter
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openScope(BuildConfig.APP_SCOPE)
            .openSubScope(NEWS_SCOPE)
            .installModules(
                NewsModule()
            )
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        KTP.closeScope(NEWS_SCOPE)
    }

    companion object {
        private const val NEWS_SCOPE = "NEWS_SCOPE"
    }
}
