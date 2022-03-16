package ru.skillbranch.gameofthrones.presentation.base

open class BasePresenter<V : IBaseView>: IPresenter<V> {
    private var view: V? = null

    override fun attachView(view: V?) { this.view = view }

    override fun detachView() { view = null }

    override fun getView(): V? = view
}