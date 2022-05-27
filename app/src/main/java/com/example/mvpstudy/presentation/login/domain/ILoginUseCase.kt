package com.example.mvpstudy.presentation.login.domain

import com.example.mvpstudy.presentation.login.domain.model.LoginEntries
import com.example.mvpstudy.presentation.login.domain.model.LoginSituations

interface ILoginUseCase {
    fun execute(loginEntries: LoginEntries): LoginSituations
}