package com.example.gamethrones.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.domain.use_cases.GetRandomAnimalUseCase
import com.example.gamethrones.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getRandomAnimalUseCase: GetRandomAnimalUseCase,
): ViewModel() {

    private val _state = MutableStateFlow<ScreenState>(ScreenState.Init)
    val state = _state.asStateFlow()

    fun getRandomAnimal() {
        viewModelScope.launch(Dispatchers.Default) {
            getRandomAnimalUseCase().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = ScreenState.Success(result.data!!)
                    }

                    is Resource.Error -> {
                        _state.value = ScreenState.Failure
                    }

                    is Resource.Loading -> {
                        _state.value = ScreenState.Loading
                    }
                }
            }
        }
    }
}