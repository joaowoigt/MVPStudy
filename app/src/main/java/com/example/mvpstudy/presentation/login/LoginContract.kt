package com.example.mvpstudy.presentation.login

import com.example.mvpstudy.presentation.base.BasePresenter

object LoginContract {

    interface View {
        fun showSuccessToast()
        fun showWrongToast()
        fun showEmptyToast()
        fun showNullToast()
        fun insertListeners()
        fun navigateToHome()
    }

    interface Presenter: BasePresenter {
        fun handleLogin(login: String, password: String)
    }
}