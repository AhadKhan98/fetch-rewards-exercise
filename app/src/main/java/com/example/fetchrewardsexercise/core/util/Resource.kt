package com.example.fetchrewardsexercise.core.util

sealed class Resource<T>(val data: T?, val message: String?) {

    class Loading<T>(data: T? = null, message: String? = null) : Resource<T>(data, message)

    class Success<T>(data: T?) : Resource<T>(data, null)

    class Failure<T>(error: String?) : Resource<T>(null, error)

}