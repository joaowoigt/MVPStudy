package com.example.mvpstudy.presentation.login

import com.example.mvpstudy.presentation.login.domain.ILoginUseCase
import com.example.mvpstudy.presentation.login.domain.model.LoginEntries
import com.example.mvpstudy.presentation.login.domain.model.LoginSituations

class LoginPresenter(
    private val view: LoginContract.View,
    private val loginUseCase: ILoginUseCase
): LoginContract.Presenter {

    override fun handleLogin(login: String, password: String) {
         when (loginUseCase.execute(LoginEntries(login = login, password = password))) {
              LoginSituations.CORRECT ->  {
                  view.showSuccessToast()
                  view.navigateToHome()
              }
             LoginSituations.EMPTY -> view.showEmptyToast()
             LoginSituations.WRONG -> view.showWrongToast()
             LoginSituations.NULL -> view.showNullToast()
         }
    }

}