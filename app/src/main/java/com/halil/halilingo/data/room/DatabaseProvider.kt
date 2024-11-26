package com.halil.halilingo.data.room

import android.content.Context
import androidx.room.Room

class DatabaseProvider {

    @Volatile
    private var INSTANCE: AnimalWordDB? = null

    fun getDatabase(context: Context): AnimalWordDB {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AnimalWordDB::class.java,
                "animal_word_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}