package com.quehacerenzaragoza.soydezaragoza.presentation.components

sealed class ObtainDataState<out T> {
    data object Idle : ObtainDataState<Nothing>()
    data object Loading : ObtainDataState<Nothing>()
    data class Success<out T>(val data: T) : ObtainDataState<T>()
    data class Error(val message: String) : ObtainDataState<Nothing>()
}