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
    private val hpValues by lazy { arguments?.getInt(HPVALUES) }
    private val attackValues by lazy { arguments?.getInt(ATTACKVALUES) }
    private val defenseValues by lazy { arguments?.getInt(DEFENSEVALUES) }
    private val spAttackValues by lazy { arguments?.getInt(SPATTACKVALUES) }
    private val spDefenseValues by lazy { arguments?.getInt(SPDEFENSEVALUES) }
    private val speedValues by lazy { arguments?.getInt(SPEEDVALUES) }

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
        hpValues?.let { binding.hpStatsIndicator.progress = it }
        attackValues?.let { binding.attackStatsIndicator.progress = it }
        defenseValues?.let { binding.defenseStatsIndicator.progress = it }
        spAttackValues?.let { binding.spAttackStatsIndicator.progress = it }
        spDefenseValues?.let { binding.spDefenseStatsIndicator.progress = it }
        speedValues?.let { binding.speedStatsIndicator.progress = it }
    }

    companion object {
        private const val HPVALUES = "HPVALUES"
        private const val ATTACKVALUES = "ATTACKVALUES"
        private const val DEFENSEVALUES = "DEFENSEVALUES"
        private const val SPATTACKVALUES = "SPATTACKVALUES"
        private const val SPDEFENSEVALUES = "SPDEFENSEVALUES"
        private const val SPEEDVALUES = "SPEEDVALUES"
        private const val COLOR = "COLOR"

        fun newInstance(
            hpValues: Int,
            attackValues: Int,
            defenseValues: Int,
            spAttackValues: Int,
            spDefenseValues: Int,
            speedValues: Int,
        ) = StatsTabFragment().apply {
            arguments = Bundle().apply {
                putInt(HPVALUES, hpValues)
                putInt(ATTACKVALUES, attackValues)
                putInt(DEFENSEVALUES, defenseValues)
                putInt(SPATTACKVALUES, spAttackValues)
                putInt(SPDEFENSEVALUES, spDefenseValues)
                putInt(SPEEDVALUES, speedValues)
            }
        }
    }

}