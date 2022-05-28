package com.example.mvpstudy.presentation.login

object LoginContract {

    interface View {
        fun showSuccessToast()
        fun showWrongToast()
        fun showEmptyToast()
        fun showNullToast()
        fun insertListeners()
        fun navigateToHome()
    }

    interface Presenter {
        fun handleLogin(login: String, password: String)
    }
}