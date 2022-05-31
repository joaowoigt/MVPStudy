package com.example.mvpstudy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpstudy.R
import com.example.mvpstudy.databinding.FragmentHomeBinding
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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
            presenter.pokedexFlow.collect { flowState ->
                when(flowState) {
                    is FlowState.Loading -> showLoading()
                    is FlowState.Success -> {
                        hideLoading()
                        pokedexAdapter.submitData(flowState.data)
                    }
                    is FlowState.Error -> {
                        hideLoading()
                        errorToast(flowState.message)
                    }
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

    private fun showLoading() {
        binding.loadingScreen.root.visibility = View.VISIBLE
        binding.loadingScreen.pokeballImageView.startAnimation(
            AnimationUtils.loadAnimation(context, R.anim.rotate_indefinetly)
        )
    }

    private fun hideLoading() {
        binding.loadingScreen.root.visibility = View.GONE
    }

    private fun errorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToPokemonDetail(pokemonNumber: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToPokemonDetailFragment(pokemonNumber)
        findNavController().navigate(action)
    }
}