package com.example.mvpstudy.presentation.detail.tabs

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvpstudy.presentation.detail.PokemonDetailFragment
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry

class DetailAdapter(fragment: PokemonDetailFragment): FragmentStateAdapter(fragment) {

    private var fragmentList = mutableListOf<DescriptionAndEvolutionTabFragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addNewItem(pokemonSpeciesEntry: PokemonSpeciesEntry) {
        Log.d("adapter teste", "passou")
        fragmentList.add(DescriptionAndEvolutionTabFragment.newInstance(pokemonSpeciesEntry))
    }
}