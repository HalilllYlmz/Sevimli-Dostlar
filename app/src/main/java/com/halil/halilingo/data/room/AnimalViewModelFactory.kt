package com.halil.halilingo.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.halil.halilingo.data.repos.AnimalWordRepository
import com.halil.halilingo.ui.allwords.AllWordsViewModel

class AnimalWordViewModelFactory(private val repository: AnimalWordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllWordsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllWordsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
