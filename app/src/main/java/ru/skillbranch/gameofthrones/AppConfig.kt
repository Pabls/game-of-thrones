package ru.skillbranch.gameofthrones

object AppConfig {
    val NEED_HOUSES = arrayOf(
        "House Stark of Winterfell",
        "House Lannister of Casterly Rock",
        "House Targaryen of King's Landing",
        "House Greyjoy of Pyke",
        "House Tyrell of Highgarden",
        "House Baratheon of Dragonstone",
        "House Nymeros Martell of Sunspear"
    )

    val HOUSES_NAMES = arrayOf("Stark", "Lannister", "Targaryen", "Baratheon", "Greyjoy", "Martel", "Tyrell")
    const val BASE_URL = "https://www.anapioficeandfire.com/api/"

    fun getIconIdByHome(home: String, needLarge: Boolean = false): Int {
        return when (home) {
            AppConfig.HOUSES_NAMES[0] -> if(needLarge) R.drawable.stark_coast_of_arms else R.drawable.stark_icon
            AppConfig.HOUSES_NAMES[1] -> if(needLarge) R.drawable.lannister__coast_of_arms else R.drawable.lanister_icon
            AppConfig.HOUSES_NAMES[2] -> if(needLarge) R.drawable.targaryen_coast_of_arms else R.drawable.targaryen_icon
            AppConfig.HOUSES_NAMES[3] -> if(needLarge) R.drawable.baratheon else R.drawable.baratheon_icon
            AppConfig.HOUSES_NAMES[4] -> if(needLarge) R.drawable.greyjoy_coast_of_arms else R.drawable.greyjoy_icon
            AppConfig.HOUSES_NAMES[5] -> if(needLarge) R.drawable.martel_coast_of_arms else R.drawable.martel_icon
            AppConfig.HOUSES_NAMES[6] -> if(needLarge) R.drawable.tyrel_coast_of_arms else R.drawable.tyrel_icon
            else -> R.drawable.ic_stub
        }
    }

    fun getColorsByHome(home: String): Triple<Int, Int, Int> {
        return when (home) {
            AppConfig.HOUSES_NAMES[0] -> Triple(R.color.stark_dark, R.color.stark_primary, R.color.stark_accent)
            AppConfig.HOUSES_NAMES[1] -> Triple(R.color.lannister_dark, R.color.lannister_primary, R.color.lannister_accent)
            AppConfig.HOUSES_NAMES[2] -> Triple(R.color.targaryen_dark, R.color.targaryen_primary, R.color.targaryen_accent)
            AppConfig.HOUSES_NAMES[3] -> Triple(R.color.baratheon_dark, R.color.baratheon_primary, R.color.baratheon_accent)
            AppConfig.HOUSES_NAMES[4] -> Triple(R.color.greyjoy_dark, R.color.greyjoy_primary, R.color.greyjoy_accent)
            AppConfig.HOUSES_NAMES[5] -> Triple(R.color.martel_dark, R.color.martel_primary, R.color.martel_accent)
            AppConfig.HOUSES_NAMES[6] -> Triple(R.color.tyrel_dark, R.color.tyrel_primary, R.color.tyrel_accent)
            else -> Triple(R.color.color_primary_dark, R.color.color_primary, R.color.color_accent)
        }
    }
}