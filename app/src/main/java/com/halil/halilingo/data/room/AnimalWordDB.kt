package com.halil.halilingo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halil.halilingo.data.model.AnimalWordModel

@Database(entities = [AnimalWordModel::class], version = 1, exportSchema = false)
abstract class AnimalWordDB: RoomDatabase() {
    abstract fun animalWordDao(): AnimalWordDAO
}