package com.example.mvpstudy.utils

sealed class FlowState<out T>() {
    object Initial: FlowState<Nothing>()
    data class Success<out T>(val data: T): FlowState<T>()
    data class Error<out T>(val message: String): FlowState<T>()
    object Loading: FlowState<Nothing>()
}