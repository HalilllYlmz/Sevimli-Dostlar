package com.halil.halilingo.ui.allwords

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.halil.halilingo.data.model.AnimalModel
import kotlinx.coroutines.launch

class AnimalsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AnimalDatabase.getDatabase(application)
    private val repository = AnimalsRepository(database.animalDao())

    val learnedAnimals: LiveData<List<AnimalModel>> = repository.learnedAnimals
    val unlearnedAnimals: LiveData<List<AnimalModel>> = repository.unlearnedAnimals
    private val allAnimals: LiveData<List<AnimalModel>> = repository.roomDBAllAnimals
    private val firebaseAnimalsLiveData: LiveData<List<AnimalsRepository.Animal>> get() = repository.firebaseAnimalsLiveData

    fun updateByTurkishName(turkishName: String, isLearned: Boolean) {
        viewModelScope.launch {
            repository.updateByTurkishName(turkishName, isLearned)
        }
    }

    fun addMissingAnimalsToDatabase() {
        val fetchedAnimals = firebaseAnimalsLiveData.value ?: emptyList()
        val existingAnimals = allAnimals.value ?: emptyList()

        fetchedAnimals.forEach {
            Log.e("Fetched Animals", it.english)
        }

        /*
        fetchedAnimals.forEach {
            Log.e("Fetched Animals", it.toString())
        }

        existingAnimals.forEach {
            Log.e("Existing Animals", it.toString())
        }

        val missingAnimals = fetchedAnimals.filter { fetchedAnimal ->
            existingAnimals.none { existingAnimal ->
                existingAnimal.englishName == fetchedAnimal.english &&
                        existingAnimal.turkishName == fetchedAnimal.turkish
            }
        }

        viewModelScope.launch {
            for (animal in missingAnimals) {
                val animalModel = AnimalModel(
                    id = 0,
                    turkishName = animal.turkish,
                    englishName = animal.english,
                    isLearned = false,
                    imageUrl = animal.imageUrl
                )
                repository.insertAnimal(animalModel)
            }
        }
         */

        if (existingAnimals.isEmpty()) {
            Log.e("Existing Animals", "Empty")

            viewModelScope.launch {
                for (animal in fetchedAnimals) {
                    val animalModel = AnimalModel(
                        id = 0,
                        turkishName = animal.turkish,
                        englishName = animal.english,
                        isLearned = false,
                        imageUrl = animal.imageUrl
                    )
                    repository.insertAnimal(animalModel)
                }
            }
        }

    }

    // Verileri yükle
    fun loadAnimals() {
        repository.fetchAnimals()
    }

    // Firestore'a resim URL'lerini yükle
    fun uploadImageUrlsToFireStore() {
        repository.uploadImageUrlsToFireStore()
    }

    // Veritabanı işlemleri
    fun insertAnimal(animal: AnimalModel) {
        viewModelScope.launch {
            repository.insertAnimal(animal)
        }
    }

    fun updateAnimal(animal: AnimalModel) {
        viewModelScope.launch {
            repository.updateAnimal(animal)
        }
    }

    fun deleteAnimal(animal: AnimalModel) {
        viewModelScope.launch {
            repository.deleteAnimal(animal)
        }
    }

    fun deleteAllAnimals() {
        viewModelScope.launch {
            repository.deleteAllAnimals()
        }
    }
}

