package com.halil.halilingo.ui.allwords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalsViewModel : ViewModel() {

    private val repository = AnimalsRepository()

    val animalsLiveData: LiveData<List<AnimalsRepository.Animal>> get() = repository.animalsLiveData

    fun loadAnimals() {
        repository.fetchAnimals()
    }

    fun uploadImageUrlsToFireStore() {
        repository.uploadImageUrlsToFireStore()
    }

}
