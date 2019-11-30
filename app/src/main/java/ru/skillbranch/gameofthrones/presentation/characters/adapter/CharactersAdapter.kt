package ru.skillbranch.gameofthrones.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class CharactersAdapter(private val listener: ItemClickListener) : RecyclerView.Adapter<CharactersViewHolder>() {

    private var items: List<CharacterItem> = ArrayList()

    fun setData(items: List<CharacterItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharactersViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) = holder.bind(items[position])

}