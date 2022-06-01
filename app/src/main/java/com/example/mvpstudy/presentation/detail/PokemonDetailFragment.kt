package com.example.mvpstudy.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvpstudy.R
import com.example.mvpstudy.databinding.FragmentDetailBinding
import com.example.mvpstudy.databinding.LoadingScreenBinding
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.presentation.detail.tabs.DetailAdapter
import com.example.mvpstudy.utils.FlowState
import com.example.mvpstudy.utils.createPokemonCard
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PokemonDetailFragment : Fragment(), PokemonDetailContract.View {

    private val presenter by inject<PokemonDetailContract.Presenter> { parametersOf(this) }
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: DetailAdapter
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        presenter.retrievePokemonData(args.id)
        presenter.retrievePokemonDescriptionAndEvolution(args.id)
        insertListeners()
        return binding.root
    }

    private fun insertListeners() {
        binding.backIconImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeDetailFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.detailPokemon.collect { flowState ->
                when (flowState) {
                    is FlowState.Loading -> showLoading(binding.pageLoadingScreen)
                    is FlowState.Success -> {
                        setupCard(flowState.data)
                        hideLoading(binding.pageLoadingScreen)
                    }
                    is FlowState.Error -> {
                        showToastError(flowState.message)
                    }
                }

            }
        }
    }

    override fun observeDescriptionAndEvolutionFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.descriptionAndEvolution.collect { flowState ->
                when (flowState) {
                    is FlowState.Loading -> showLoading(binding.moreDetailLoading)
                    is FlowState.Success -> {
                        hideLoading(binding.moreDetailLoading)
                        setupAdapter(flowState.data)
                    }
                    is FlowState.Error -> showToastError(flowState.message)
                }
            }
        }
    }


    private fun setupCard(detailPokemon: DetailPokemon?) {
        with(binding) {
            detailPokemon?.let {
                pokemonNameTextView.text = detailPokemon.name.replaceFirstChar { it.uppercase() }
                pokemonNumberTextView.text = detailPokemon.id.toString()
                card.createPokemonCard(detailPokemon.sprites.front_default, pokemonSpriteImageView)
                pokemonSpriteImageView.startAnimation(
                    AnimationUtils.loadAnimation(context, R.anim.pokemon_movement)
                )
            }
        }
    }

    private fun setupAdapter(pokemonSpeciesEntry: PokemonSpeciesEntry) {
        adapter = DetailAdapter(this)
        binding.viewPager.adapter = adapter
        adapter.addNewItem(pokemonSpeciesEntry)
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
            tab.text = when (position) {
                0 -> "Description"
                else -> "fail"
            }
        }.attach()
    }

    private fun showLoading(loadingView: LoadingScreenBinding) {
        loadingView.apply {
            root.visibility = View.VISIBLE
            pokeballImageView.startAnimation(
                AnimationUtils.loadAnimation(context, R.anim.rotate_indefinetly)
            )
        }
    }

    private fun hideLoading(loadingView: LoadingScreenBinding) {
        loadingView.root.visibility = View.GONE
    }

    private fun showToastError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}