package com.bcan.arabamcomassignment.data.util

sealed class NetworkResult<out T> {

    data class OnSuccess<out T>(val data: T?) : NetworkResult<T>()

    data class OnError(val message: String?) : NetworkResult<Nothing>()

    object OnLoading : NetworkResult<Nothing>()
}
