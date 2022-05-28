package com.example.mvpstudy.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry

class PokedexAdapter:RecyclerView.Adapter<PokedexCardViewHolder>() {

    var pokedex: List<PokedexEntry> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexCardViewHolder =
        PokedexCardViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: PokedexCardViewHolder, position: Int) {
        holder.bind(pokedex[position])
    }

    override fun getItemCount(): Int = pokedex.size

    fun setPokedex(pokedexListEntry: PokedexListEntry) {
        pokedex = pokedexListEntry.pokedex
        notifyDataSetChanged()
    }

}