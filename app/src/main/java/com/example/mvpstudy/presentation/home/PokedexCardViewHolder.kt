package com.example.mvpstudy.presentation.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mvpstudy.databinding.ItemPokedexBinding
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

class PokedexCardViewHolder(
    private val binding: ItemPokedexBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pokedexEntry: PokedexEntry) {
        with(binding) {
            pokemonNameTextView.text = pokedexEntry.pokemonName
            pokemonNumberTextView.text = pokedexEntry.number
            pokemonSpriteImageView.load(pokedexEntry.imageUrl) {
                this.target {
                    calcDominantColor(it)
                        ?.let { dominantColor -> card.setCardBackgroundColor(dominantColor) }
                }
            }
            pokemonSpriteImageView.load(pokedexEntry.imageUrl)
        }
    }

    private fun calcDominantColor(drawable: Drawable): Int? {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        return Palette.from(bitmap).generate().dominantSwatch?.rgb
    }

    companion object {
        fun inflate(parent: ViewGroup): PokedexCardViewHolder =
            PokedexCardViewHolder(
                ItemPokedexBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

}