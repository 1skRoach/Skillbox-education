package com.example.mvvm_homework_12.ui.main

import android.text.Editable
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_homework_12.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Started)
    val state = _state.asStateFlow()

    fun onSearchClick() {
        viewModelScope.launch {
            _state.value = State.Started
            _state.value = State.Loading
            delay(5000)
            _state.value = State.Success
        }
    }
}