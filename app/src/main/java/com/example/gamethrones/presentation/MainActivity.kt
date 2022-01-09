package com.example.gamethrones.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gamethrones.databinding.ActivityMainBinding
import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.util.loadImage
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
                    is ScreenState.Success -> showAnimalInfo(state.result)

                    is ScreenState.Failure -> {}

                    is ScreenState.Init -> {}

                    is ScreenState.Loading -> {}
                }
            }
        }
    }

    private fun showAnimalInfo(animal: Animal) {
        binding.apply {
            imageView.loadImage(animal.imageLink, baseContext)
            name.text = animal.name
            animalType.text = animal.animalType
            diet.text = animal.diet
            habitat.text = animal.habitat
        }
    }
}