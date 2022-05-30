package com.example.mvpstudy.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

class PokedexAdapter(
     val onPokemonClicked: (String) -> Unit
) :
    PagingDataAdapter<PokedexEntry, PokedexCardViewHolder>(PokemonDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexCardViewHolder =
        PokedexCardViewHolder.inflate(parent, onPokemonClicked)

    override fun onBindViewHolder(holder: PokedexCardViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}

class PokemonDiffCallback : DiffUtil.ItemCallback<PokedexEntry>() {
    override fun areItemsTheSame(oldItem: PokedexEntry, newItem: PokedexEntry): Boolean {
        return oldItem.pokemonName == newItem.pokemonName
    }

    override fun areContentsTheSame(oldItem: PokedexEntry, newItem: PokedexEntry): Boolean {
        return oldItem == newItem
    }
}