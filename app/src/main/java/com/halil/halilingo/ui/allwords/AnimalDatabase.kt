package com.halil.halilingo.ui.allwords

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.halil.halilingo.data.model.AnimalModel

@Database(entities = [AnimalModel::class], version = 1, exportSchema = false)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    companion object {

        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getDatabase(context: Context): AnimalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    "animal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}