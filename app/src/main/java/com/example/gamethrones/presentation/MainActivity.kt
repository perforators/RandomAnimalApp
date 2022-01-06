package com.example.gamethrones.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gamethrones.databinding.ActivityMainBinding
import com.example.gamethrones.domain.model.AnimalInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.getRandomAnimal()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when(state) {
                    is ScreenState.Success -> {
                        render(state.result)
                    }

                    else -> {
                        render(AnimalInfo(
                            "E",
                            "G",
                            "O",
                            "R",
                            "f"
                        ))
                    }
                }
            }
        }
    }

    private fun render(animalInfo: AnimalInfo?) {
        animalInfo?.let { info ->
            binding.apply {
                name.text = info.name
                animalType.text = info.animal_type
                diet.text = info.diet
                habitat.text = info.habitat
            }
        }
    }
}