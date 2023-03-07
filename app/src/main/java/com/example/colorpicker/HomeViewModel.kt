package com.example.colorpicker

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    val _colorUiState = MutableStateFlow(ColorUiState())
    val colorUiState: StateFlow<ColorUiState> = _colorUiState.asStateFlow()

    fun updateColor(col: Color) {
        _colorUiState.update {
            it.copy(
                color = col
            )
        }

    }
}