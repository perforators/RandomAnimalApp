package com.example.gamethrones.presentation

import com.example.gamethrones.domain.model.Animal

sealed class AnimalViewState {
    class AnimalLoaded(val result: Animal): AnimalViewState()
    class Failure(val message: String) : AnimalViewState()
    object Loading: AnimalViewState()
    object Default: AnimalViewState()
}
