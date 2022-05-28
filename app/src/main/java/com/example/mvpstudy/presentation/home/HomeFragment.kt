package com.example.mvpstudy.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpstudy.databinding.FragmentHomeBinding
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment: Fragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokedexAdapter: PokedexAdapter

    private val presenter by inject<HomeContract.Presenter> { parametersOf(this@HomeFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupAdapter()
        presenter.retrieveData()
        observeFlow()
        return binding.root
    }

    override fun observeFlow() {
        lifecycleScope.launchWhenStarted {
            presenter.pokedexFlow.collect {
                it?.let { pokedex ->
                    pokedexAdapter.setPokedex(pokedex)
                }
            }
        }
    }

    override fun networkErrorToast() {
        Toast.makeText(context, "Problema de conexão com a internet", Toast.LENGTH_LONG).show()
    }

    override fun unknownErrorToast() {
        Toast.makeText(context, "Um erro desconhecido ocorreu", Toast.LENGTH_LONG).show()
    }

    private fun setupAdapter() {
        pokedexAdapter = PokedexAdapter()
        with(binding.pokedexRecyclerView) {
            adapter = pokedexAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}