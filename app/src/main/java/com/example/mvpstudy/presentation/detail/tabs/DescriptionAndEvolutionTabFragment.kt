package com.example.mvpstudy.presentation.detail.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpstudy.databinding.DetailTabDescriptionBinding
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry

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

            }
        }
    }
}