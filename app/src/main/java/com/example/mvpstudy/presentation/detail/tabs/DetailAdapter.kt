package com.example.mvpstudy.presentation.detail.tabs

import android.content.res.ColorStateList
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvpstudy.data.remote.model.Stat
import com.example.mvpstudy.presentation.detail.PokemonDetailFragment
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry

class DetailAdapter(fragment: PokemonDetailFragment): FragmentStateAdapter(fragment) {

    private var fragmentList = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addNewItem(pokemonSpeciesEntry: PokemonSpeciesEntry) {
        fragmentList.add(DescriptionAndEvolutionTabFragment.newInstance(pokemonSpeciesEntry))
    }

    fun addNewItem(stats: List<Stat>) {
        fragmentList.add(StatsTabFragment.newInstance(stats[HPSTATINDEX].base_stat))
    }

    companion object {
        const val HPSTATINDEX = 0
    }
}