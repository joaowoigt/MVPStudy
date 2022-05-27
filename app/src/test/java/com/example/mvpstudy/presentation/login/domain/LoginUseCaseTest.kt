package com.example.mvpstudy.presentation.login.domain

import com.example.mvpstudy.presentation.login.domain.model.LoginEntries
import com.example.mvpstudy.presentation.login.domain.model.LoginSituations
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class LoginUseCaseTest {
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun initialize() {
        loginUseCase = LoginUseCase()
    }

    @Test
    fun login_RightCredentials_ReturnTrue() {
        val loginEntries = LoginEntries("Woigt", "1234")
        assertEquals(LoginSituations.CORRECT, loginUseCase.execute(loginEntries))
    }

    @Test
    fun login_WrongLoginRightPassword_ReturnFalse() {
        val loginEntries = LoginEntries("wrong", "1234" )
        assertEquals(LoginSituations.WRONG, loginUseCase.execute(loginEntries))
    }


    @Test
    fun login_RightLoginWrongPassword_ReturnFalse() {
        val loginEntries = LoginEntries("Woigt", "Wrong" )
        assertEquals(LoginSituations.WRONG, loginUseCase.execute(loginEntries))
    }

    @Test
    fun login_NullCredentials_ReturnFalse() {
        val loginEntries = LoginEntries(null, null)
        assertEquals(LoginSituations.NULL, loginUseCase.execute(loginEntries))
    }

    @Test
    fun login_EmptyCredentials_ReturnFalse() {
        val loginEntries = LoginEntries("", "")
        assertEquals(LoginSituations.EMPTY, loginUseCase.execute(loginEntries))
    }

}