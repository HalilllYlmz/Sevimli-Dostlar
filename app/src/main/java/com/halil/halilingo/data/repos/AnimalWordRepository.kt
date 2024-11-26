package com.halil.halilingo.data.repos

import com.halil.halilingo.data.model.AnimalWordModel
import com.halil.halilingo.data.room.AnimalWordDAO

class AnimalWordRepository(
    private val animalWordDao: AnimalWordDAO
) {
    fun getLearnedWords() = animalWordDao.getLearnedWords()
    fun getUnlearnedWords() = animalWordDao.getUnlearnedWords()
    fun addLearnedWord(word: AnimalWordModel) = animalWordDao.addLearnedWord(word)
    fun deleteLearnedWord(word: AnimalWordModel) = animalWordDao.deleteLearnedWord(word)
}