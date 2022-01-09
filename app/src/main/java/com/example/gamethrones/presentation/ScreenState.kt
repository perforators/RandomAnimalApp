package com.example.gamethrones.presentation

import com.example.gamethrones.domain.model.Animal

sealed class ScreenState {
    class Success(val result: Animal): ScreenState()
    object Failure: ScreenState()
    object Loading: ScreenState()
    object Init: ScreenState()
}
