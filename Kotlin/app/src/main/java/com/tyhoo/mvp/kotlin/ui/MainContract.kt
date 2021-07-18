package com.tyhoo.mvp.kotlin.ui

import com.tyhoo.mvp.kotlin.base.BasePresenter
import com.tyhoo.mvp.kotlin.base.BaseView
import com.tyhoo.mvp.kotlin.data.User

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showUser(user: String)

        fun hideUser()

        fun showParseJson(user: User)
    }

    interface Presenter : BasePresenter {

        fun parseJson()
    }
}