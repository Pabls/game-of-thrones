package ru.skillbranch.gameofthrones.utils

fun List<String>.joinWithTabulation(): String = this.joinToString(separator = "\t")

fun String.getIdFromUrl(): String = this.substringAfterLast("/")