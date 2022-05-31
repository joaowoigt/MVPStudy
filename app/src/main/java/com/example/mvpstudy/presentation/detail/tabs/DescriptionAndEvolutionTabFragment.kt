package com.example.mvpstudy.presentation.detail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.mvpstudy.databinding.DetailTabDescriptionBinding
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.utils.createPokemonCard

class DescriptionAndEvolutionTabFragment : Fragment() {

    private val pokemonSpeciesEntry: PokemonSpeciesEntry? = null
    private lateinit var binding: DetailTabDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailTabDescriptionBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        with(binding) {
            pokemonSpeciesEntry?.let {
                card.createPokemonCard(pokemonSpeciesEntry.evolutions.imageUrl, pokemonSpriteImageView)
            }
        }
    }
}