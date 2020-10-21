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
import com.example.pikabutext.feature.news.databinding.FragmentNewsBinding
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class NewsFragment : Fragment() {
    private val viewModel: NewsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.viewModel = viewModel
        val layoutManager = LinearLayoutManager(requireContext())
        binding.list.layoutManager = layoutManager
        binding.list.adapter = viewModel.adapter
        binding.list.itemAnimator = null
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openScope(BuildConfig.APP_SCOPE)
            .installModules(
                NewsModule()
            )
            .inject(this)
    }
}
