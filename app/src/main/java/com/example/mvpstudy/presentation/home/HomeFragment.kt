package com.example.mvpstudy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpstudy.R
import com.example.mvpstudy.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokedexAdapter: PokedexAdapter

    private val presenter by inject<HomeContract.Presenter> { parametersOf(this@HomeFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAdapter()
        presenter.retrieveData()
        return binding.root
    }

    override fun onDestroy() {
        this.presenter.onDestroy()
        super.onDestroy()
    }

    override fun observeFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.pokedexFlow.collect {
                it?.let { pokedex ->
                    pokedexAdapter.submitData(pokedex)
                }
            }
        }
    }

    private fun setupAdapter() {
        pokedexAdapter = PokedexAdapter() { navigateToPokemonDetail(it) }
        with(binding.pokedexRecyclerView) {
            adapter = pokedexAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun navigateToPokemonDetail(pokemonNumber: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToPokemonDetailFragment(pokemonNumber)
        findNavController().navigate(action)
    }
}