package com.example.gamethrones.presentation

import com.example.gamethrones.domain.model.AnimalInfo

sealed class ScreenState {
    class Success(val result: AnimalInfo): ScreenState()
    object Failure: ScreenState()
    object Loading: ScreenState()
    object Init: ScreenState()
}
