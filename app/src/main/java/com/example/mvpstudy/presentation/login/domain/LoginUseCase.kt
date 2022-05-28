package com.example.mvpstudy.presentation.login.domain

import com.example.mvpstudy.presentation.login.domain.model.LoginEntries
import com.example.mvpstudy.presentation.login.domain.model.LoginSituations

class LoginUseCase : ILoginUseCase {

    override fun execute(loginEntries: LoginEntries): LoginSituations {
        return when {
            loginEntries.login == "" || loginEntries.password == "" -> LoginSituations.EMPTY
            loginEntries.login == null || loginEntries.password == null -> LoginSituations.NULL
            loginEntries.login == "Woigt" && loginEntries.password == "1234" -> LoginSituations.CORRECT
            else -> LoginSituations.WRONG
        }


    }
}