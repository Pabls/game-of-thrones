package ru.skillbranch.gameofthrones.presentation.characters.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class CharactersViewHolder(view: View, private val listener: ItemClickListener) : RecyclerView.ViewHolder(view) {

    private var clContainer: ConstraintLayout
    private var tvName: TextView
    private var tvTitles: TextView
    private var ivLogo: ImageView
    private var unknown: String
    private var separator: String

    init {
        clContainer = view.findViewById(R.id.cl_container)
        tvName = view.findViewById(R.id.tv_name)
        tvTitles = view.findViewById(R.id.tv_titles)
        ivLogo = view.findViewById(R.id.iv_logo)
        unknown = view.resources.getString(R.string.item_character_text_unknown)
        separator = view.resources.getString(R.string.item_character_text_separator)
    }

    fun bind(characterItem: CharacterItem) {
        ivLogo.setImageResource(AppConfig.getIconIdByHome(characterItem.house))
        tvName.text = if (characterItem.name.isNullOrEmpty()) unknown else characterItem.name
        tvTitles.text =
            if (characterItem.titles.isNullOrEmpty()) unknown else characterItem.titles.joinToString(separator = separator)
        clContainer.setOnClickListener { listener.onClick(characterItem.id) }
    }
}