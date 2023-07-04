package com.miiladshabanii.news.ui.base

interface BaseState {
    enum class State {
        SUCCESS,
        LOADING,
        ERROR
    }
    val state: State
    val errorMsg: String?
    val data: Any
}