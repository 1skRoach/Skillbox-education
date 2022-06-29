package com.example.mvvm_homework_12

sealed class State {
    object Loading: State()
    object Success: State()
    object Started: State()
}
