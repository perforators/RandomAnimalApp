package com.example.gamethrones.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamethrones.domain.use_cases.GetRandomAnimalUseCase
import com.example.gamethrones.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.Timeout
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getRandomAnimalUseCase: GetRandomAnimalUseCase,
): ViewModel() {

    private val _state = MutableStateFlow<AnimalViewState>(AnimalViewState.Default)
    val state = _state.asStateFlow()

    private var job: Job? = null

    fun getRandomAnimal() {
        _state.value = AnimalViewState.Default
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.Default) {
            getRandomAnimalUseCase().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = AnimalViewState.AnimalLoaded(result.data!!)
                    }

                    is Resource.Error -> {
                        _state.value = AnimalViewState.Failure(result.message!!)
                    }

                    is Resource.Loading -> {
                        _state.value = AnimalViewState.Loading
                    }
                }
            }
        }
    }
}