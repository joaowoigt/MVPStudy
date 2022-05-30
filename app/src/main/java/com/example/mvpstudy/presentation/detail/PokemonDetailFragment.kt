package com.example.mvpstudy.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvpstudy.databinding.FragmentDetailBinding
import com.example.mvpstudy.presentation.home.HomeContract
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
        presenter.retrievePokemonData()
        return binding.root
    }

    override fun setupUI() {
        TODO("Not yet implemented")
    }

}