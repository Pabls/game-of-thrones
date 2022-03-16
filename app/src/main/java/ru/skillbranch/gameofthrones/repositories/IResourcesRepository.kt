package ru.skillbranch.gameofthrones.repositories

interface IResourcesRepository {
    fun getStringById(id: Int) : String
}