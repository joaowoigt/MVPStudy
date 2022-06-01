package com.example.mvpstudy.presentation.detail.tabs

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpstudy.databinding.DetailTabStatsBinding

class StatsTabFragment: Fragment() {

    private lateinit var binding: DetailTabStatsBinding
    private val hpValues by lazy { arguments?.getInt(HPVALUES) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailTabStatsBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        hpValues?.let {binding.hpStatsIndicator.progress = it }
    }

    companion object {
        private const val HPVALUES = "HPVALUES"
        private const val COLOR = "COLOR"

        fun newInstance(hpValues: Int) = StatsTabFragment().apply {
            arguments?.apply {
                putInt(HPVALUES, hpValues)
            }
        }
    }

}