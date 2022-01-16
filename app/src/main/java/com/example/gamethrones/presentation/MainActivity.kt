package com.example.gamethrones.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gamethrones.databinding.ActivityMainBinding
import com.example.gamethrones.domain.model.Animal
import com.example.gamethrones.util.resize
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
                    is AnimalViewState.AnimalLoaded -> {
                        showAnimalInfo(state.result)
                        hideProgressBar()
                    }

                    is AnimalViewState.Failure -> {
                        showErrorText(state.message)
                        hideProgressBar()
                    }

                    is AnimalViewState.Default -> {
                        showAnimalInfo(Animal.EMPTY)
                    }

                    is AnimalViewState.Loading -> {
                        hideErrorText()
                        showProgressBar()
                    }
                }
            }
        }

        viewModel.getRandomAnimal()
    }

    private fun showAnimalInfo(animal: Animal) {
        binding.apply {
            imageView.setImageBitmap(animal.imageBitmap?.resize(IMAGE_WIDTH, IMAGE_HEIGHT))
            name.text = animal.name
            animalType.text = animal.animalType
            diet.text = animal.diet
            habitat.text = animal.habitat
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorText(message: String) {
        binding.errorView.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    private fun hideErrorText() {
        binding.errorView.apply {
            visibility = View.GONE
            text = ""
        }
    }

    companion object {
        private const val IMAGE_WIDTH = 900
        private const val IMAGE_HEIGHT = 800
    }
}