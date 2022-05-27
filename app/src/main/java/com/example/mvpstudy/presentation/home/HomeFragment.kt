package com.example.mvpstudy.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvpstudy.databinding.FragmentHomeBinding
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment: Fragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding

    private val presenter by inject<HomeContract.Presenter> { parametersOf(this@HomeFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        presenter.retrieveData()
        return binding.root
    }

    override fun setupAdapter(pokedexListEntry: PokedexListEntry?) {
        pokedexListEntry?.pokedex?.forEach {
            Log.d("pokedexText", it.pokemonName)
        }
    }
}