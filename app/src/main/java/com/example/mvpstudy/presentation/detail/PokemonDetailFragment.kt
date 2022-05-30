package com.example.mvpstudy.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.mvpstudy.databinding.FragmentDetailBinding
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
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
        return binding.root
    }

    override fun observeFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.detailPokemon.collect {
                setupCard(it)
            }
        }
    }

    override fun showError() {
        Toast.makeText(context, "deu erro", Toast.LENGTH_LONG).show()
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

}