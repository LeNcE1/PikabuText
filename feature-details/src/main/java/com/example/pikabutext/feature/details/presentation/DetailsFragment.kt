package com.example.pikabutext.feature.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pikabutext.feature.details.BuildConfig
import com.example.pikabutext.feature.details.DetailsModule
import com.example.pikabutext.feature.details.R
import com.example.pikabutext.feature.details.databinding.FragmentDetailsBinding
import com.example.pikabutext.feature.details.presentation.model.DetailsModel
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject


class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel = viewModel
        val layoutManager = LinearLayoutManager(requireContext())
        binding.listBody.layoutManager = layoutManager
        binding.listBody.adapter = viewModel.adapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.listBody.context,
            layoutManager.orientation
        )
        binding.listBody.addItemDecoration(dividerItemDecoration)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments?.getParcelable<DetailsModel>(this.javaClass.name)
        KTP.openScope(BuildConfig.APP_SCOPE)
            .openSubScope(DETAILS_SCOPE)
            .installModules(
                DetailsModule(),
                Module().apply {
                    bind(DetailsModel::class.java).toInstance(args)
                }
            )
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        KTP.closeScope(DETAILS_SCOPE)
    }

    companion object {
        private const val DETAILS_SCOPE = "DETAILS_SCOPE"
    }
}
