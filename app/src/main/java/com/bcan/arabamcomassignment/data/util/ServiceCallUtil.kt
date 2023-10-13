package com.bcan.arabamcomassignment.data.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> sendRequest(serviceCall: suspend () -> Response<T>) =
    flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { serviceCall() })
    }.flowOn(Dispatchers.IO)

private suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val result = apiCall()
        when (result.isSuccessful) {
            true -> NetworkResult.OnSuccess(result.body())
            false -> NetworkResult.OnError(result.message())
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> NetworkResult.OnError(throwable.message)
            is HttpException -> NetworkResult.OnError(throwable.message)
            else -> NetworkResult.OnError(throwable.message)
        }
    }
}