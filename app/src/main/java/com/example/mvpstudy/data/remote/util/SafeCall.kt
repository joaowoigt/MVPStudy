package com.example.mvpstudy.data.remote.util

import retrofit2.Response
import java.lang.Exception


suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return Resource.Success(body)
            }
        }
        Resource.Error(message = "${response.code()} ${response.message()}")
    } catch (e: Exception) {
        Resource.Error(message = "An unknown error occurred")
    }
}



