package com.example.expensetracker.data.response

sealed class Response<out T> {
    object Idle : Response<Nothing>()
    object Loading : Response<Nothing>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
}

suspend fun <T> safeCall(block: suspend () -> T): Response<T> {
    return try {
        Response.Success(block())
    } catch (e: Exception) {
        Response.Error(e.message ?: "Unknown error")
    }
}