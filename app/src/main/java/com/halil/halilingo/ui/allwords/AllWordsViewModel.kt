package com.halil.halilingo.ui.allwords

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halil.halilingo.data.model.AnimalWordModel
import com.halil.halilingo.data.repos.AnimalWordRepository
import kotlinx.coroutines.launch

class AllWordsViewModel(private val animalWordRepository: AnimalWordRepository): ViewModel() {

    val learnedWords: LiveData<List<AnimalWordModel>> = animalWordRepository.getLearnedWords()
    val unlearnedWords: LiveData<List<AnimalWordModel>> = animalWordRepository.getUnlearnedWords()

    fun addLearnedWord(word: AnimalWordModel) = viewModelScope.launch {
        animalWordRepository.addLearnedWord(word)
    }

    fun deleteLearnedWord(word: AnimalWordModel) = viewModelScope.launch {
        animalWordRepository.deleteLearnedWord(word)
    }
}