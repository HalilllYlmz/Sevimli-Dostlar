package com.halil.halilingo.ui.allwords

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.halil.halilingo.data.model.AnimalModel

@Dao
interface AnimalDao {

    // Tüm hayvanları getir
    @Query("SELECT * FROM animal_words")
    fun getAllAnimals(): LiveData<List<AnimalModel>>

    // Öğrenilmiş hayvanları getir
    @Query("SELECT * FROM animal_words WHERE is_learned = 1")
    fun getLearnedAnimals(): LiveData<List<AnimalModel>>

    @Query("SELECT * FROM animal_words WHERE is_learned = 0")
    fun getUnlearnedAnimals(): LiveData<List<AnimalModel>>

    // Yeni hayvan ekle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: AnimalModel)

    // Birden fazla hayvan ekle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimals(animals: List<AnimalModel>)

    // Hayvanı güncelle
    @Update
    suspend fun updateAnimal(animal: AnimalModel)

    @Query("UPDATE animal_words SET is_learned = :isLearned WHERE turkish_name = :turkishName")
    suspend fun updateByTurkishName(
        turkishName: String,
        isLearned: Boolean
    )
    // Hayvanı sil
    @Delete
    suspend fun deleteAnimal(animal: AnimalModel)

    // Tüm hayvanları sil
    @Query("DELETE FROM animal_words")
    suspend fun deleteAllAnimals()

}