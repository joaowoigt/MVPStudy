package com.example.mvpstudy.presentation.detail.tabs

import android.content.res.ColorStateList
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvpstudy.data.remote.model.Stat
import com.example.mvpstudy.presentation.detail.PokemonDetailFragment
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry

class DetailAdapter(fragment: PokemonDetailFragment) : FragmentStateAdapter(fragment) {

    private var fragmentList = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addNewItem(pokemonSpeciesEntry: PokemonSpeciesEntry) {
        fragmentList.add(DescriptionAndEvolutionTabFragment.newInstance(pokemonSpeciesEntry))
    }

    fun addNewItem(stats: List<Stat>, color: Int?) {
        fragmentList.add(
            StatsTabFragment.newInstance(
                stats[HPSTATINDEX].base_stat,
                stats[ATTACKSTATINDEX].base_stat,
                stats[DEFENSESTATSINDEX].base_stat,
                stats[SPATTACKSTATINDEX].base_stat,
                stats[SPDEFENSESTATINDEX].base_stat,
                stats[SPEEDSTATINDEX].base_stat,
                color,
            )
        )
    }

    companion object {
        private const val HPSTATINDEX = 0
        private const val ATTACKSTATINDEX = 1
        private const val DEFENSESTATSINDEX = 2
        private const val SPATTACKSTATINDEX = 3
        private const val SPDEFENSESTATINDEX = 4
        private const val SPEEDSTATINDEX = 5
    }
}