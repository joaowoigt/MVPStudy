package com.example.mvpstudy.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mvpstudy.R
import com.example.mvpstudy.databinding.FragmentDetailBinding
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.utils.FlowState
import com.example.mvpstudy.utils.calcDominantColor
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PokemonDetailFragment: Fragment(), PokemonDetailContract.View {

    private val presenter by inject<PokemonDetailContract.Presenter> { parametersOf(this) }
    private lateinit var binding: FragmentDetailBinding
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        presenter.retrievePokemonData(args.id)
        presenter.retrievePokemonEvolutionChain(args.id)
        insertListeners()
        return binding.root
    }

    private fun insertListeners() {
        binding.backIconImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.detailPokemon.collect { flowState ->
                when(flowState) {
                    is FlowState.Loading -> showLoading()
                    is FlowState.Success -> {
                        setupCard(flowState.data)
                        hideLoading()
                    }
                    is FlowState.Error -> {
                        showToastError(flowState.message)
                    }
                }

            }
        }
    }


    private fun setupCard(detailPokemon: DetailPokemon?) {
        with(binding) {
            pokemonNumberTextView.text = detailPokemon?.id.toString()
            pokemonSpriteImageView.load(detailPokemon?.sprites?.front_default) {
                this.target {
                    calcDominantColor(it)
                        ?.let { dominantColor -> card.setCardBackgroundColor(ColorUtils.setAlphaComponent(dominantColor, 99)) }
                }
            }
            pokemonSpriteImageView.load(detailPokemon?.sprites?.front_default)
        }
    }

    private fun showLoading() {
        binding.loadingScreen.root.visibility = View.VISIBLE
        binding.loadingScreen.pokeballImageView.startAnimation(
            AnimationUtils.loadAnimation(context, R.anim.rotate_indefinetly)
        )
    }

    private fun hideLoading() {
        binding.loadingScreen.root.visibility = View.GONE
    }


    private fun showToastError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}