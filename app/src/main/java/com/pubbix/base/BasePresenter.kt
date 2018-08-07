package com.pubbix.base

open class BasePresenter<V : BaseView, I : Any> {

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

}