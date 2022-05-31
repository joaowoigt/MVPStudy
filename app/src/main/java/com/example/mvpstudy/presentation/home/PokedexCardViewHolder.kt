package com.example.mvpstudy.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpstudy.databinding.ItemPokedexBinding
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.utils.createPokemonCard

class PokedexCardViewHolder(
    private val onPokemonClicked: (String) -> Unit,
    val binding: ItemPokedexBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pokedexEntry: PokedexEntry) {
        with(binding) {
            pokemonNameTextView.text = pokedexEntry.pokemonName.replaceFirstChar { it.uppercase() }
            pokemonNumberTextView.text = pokedexEntry.number
            card.createPokemonCard(pokedexEntry.imageUrl, pokemonSpriteImageView)
            card.setOnClickListener {
                onPokemonClicked.invoke(pokedexEntry.number)
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup, onPokemonClicked: (String) -> Unit): PokedexCardViewHolder =
            PokedexCardViewHolder(
                binding = ItemPokedexBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onPokemonClicked = onPokemonClicked
            )
    }

}