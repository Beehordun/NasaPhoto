package com.example.presentation.util

sealed class ViewState <T> {
    class Loading<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    sealed class Error<T> : ViewState<T>() {
        class NoConnectionError<T>: Error<T>()
        class ServerError<T>: Error<T>()
    }
}
