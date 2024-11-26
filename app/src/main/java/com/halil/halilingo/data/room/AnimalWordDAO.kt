package com.halil.halilingo.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halil.halilingo.data.model.AnimalWordModel

@Dao
interface AnimalWordDAO {

    @Query("SELECT * FROM animal_words WHERE is_learned = 1")
    fun getLearnedWords(): LiveData<List<AnimalWordModel>>

    @Query("SELECT * FROM animal_words WHERE is_learned = 0")
    fun getUnlearnedWords(): LiveData<List<AnimalWordModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLearnedWord(word: AnimalWordModel)

    @Delete
    fun deleteLearnedWord(word: AnimalWordModel)

}