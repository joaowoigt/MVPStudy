package com.example.mvpstudy.presentation.detail.tabs

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpstudy.databinding.DetailTabStatsBinding

class StatsTabFragment : Fragment() {

    private lateinit var binding: DetailTabStatsBinding
    private val spDefenseValues by lazy { arguments?.getInt(SPDEFENSEVALUES) }
    private val spAttackValues by lazy { arguments?.getInt(SPATTACKVALUES) }
    private val defenseValues by lazy { arguments?.getInt(DEFENSEVALUES) }
    private val attackValues by lazy { arguments?.getInt(ATTACKVALUES) }
    private val speedValues by lazy { arguments?.getInt(SPEEDVALUES) }
    private val hpValues by lazy { arguments?.getInt(HPVALUES) }
    private val color by lazy { arguments?.getInt(COLOR) }

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
        with(binding) {
            hpValues?.let { hpStatsIndicator.progress = it }
            attackValues?.let { attackStatsIndicator.progress = it }
            defenseValues?.let { defenseStatsIndicator.progress = it }
            spAttackValues?.let { spAttackStatsIndicator.progress = it }
            spDefenseValues?.let { spDefenseStatsIndicator.progress = it }
            speedValues?.let { speedStatsIndicator.progress = it }

            color?.run {
                hpStatsIndicator.setIndicatorColor(this)
                attackStatsIndicator.setIndicatorColor(this)
                defenseStatsIndicator.setIndicatorColor(this)
                spAttackStatsIndicator.setIndicatorColor(this)
                spDefenseStatsIndicator.setIndicatorColor(this)
                speedStatsIndicator.setIndicatorColor(this)
            }
        }
    }

    companion object {
        private const val SPDEFENSEVALUES = "SPDEFENSEVALUES"
        private const val SPATTACKVALUES = "SPATTACKVALUES"
        private const val DEFENSEVALUES = "DEFENSEVALUES"
        private const val ATTACKVALUES = "ATTACKVALUES"
        private const val SPEEDVALUES = "SPEEDVALUES"
        private const val HPVALUES = "HPVALUES"
        private const val COLOR = "COLOR"

        fun newInstance(
            hpValues: Int,
            attackValues: Int,
            defenseValues: Int,
            spAttackValues: Int,
            spDefenseValues: Int,
            speedValues: Int,
            color: Int?
        ) = StatsTabFragment().apply {
            arguments = Bundle().apply {
                putInt(HPVALUES, hpValues)
                putInt(ATTACKVALUES, attackValues)
                putInt(DEFENSEVALUES, defenseValues)
                putInt(SPATTACKVALUES, spAttackValues)
                putInt(SPDEFENSEVALUES, spDefenseValues)
                putInt(SPEEDVALUES, speedValues)
                color?.let { putInt(COLOR, it) }
            }
        }
    }
}