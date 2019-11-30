package ru.skillbranch.gameofthrones.presentation.base

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String, indefinite: Boolean = false)
    fun showError(message: String, indefinite: Boolean = false)
    fun showMessageWithAction(message: String, actionTitle: String, action: () -> Unit)
}