package com.example.mvpstudy.presentation.detail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.mvpstudy.data.remote.model.PokemonSpecies
import com.example.mvpstudy.databinding.DetailTabDescriptionBinding
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.utils.MEW_IMAGE
import com.example.mvpstudy.utils.createPokemonCard

class DescriptionAndEvolutionTabFragment : Fragment() {

    private val pokemonImageUrl: String? by lazy { arguments?.getString(POKEMON_SPECIES_IMAGE_URL) }
    private val pokemonDescription: String? by lazy {
        arguments?.getString(
            POKEMON_SPECIES_DESCRIPTIOM
        )
    }
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
            card.createPokemonCard(
                pokemonImageUrl ?: MEW_IMAGE,
                pokemonSpriteImageView
            )
            descriptionTextView.text = pokemonDescription?.replace("\n", " ")
        }
    }


    companion object {
        private const val POKEMON_SPECIES_IMAGE_URL = "POKEMON_SPECIES_IMAGE_URL"
        private const val POKEMON_SPECIES_DESCRIPTIOM = "POKEMON_SPECIES_DESCRIPTION"

        fun newInstance(pokemonSpeciesEntry: PokemonSpeciesEntry) =
            DescriptionAndEvolutionTabFragment().apply {
                arguments = Bundle().apply {
                    putString(POKEMON_SPECIES_IMAGE_URL, pokemonSpeciesEntry.evolutions?.imageUrl)
                    putString(POKEMON_SPECIES_DESCRIPTIOM, pokemonSpeciesEntry.description)
                }
            }
    }
}