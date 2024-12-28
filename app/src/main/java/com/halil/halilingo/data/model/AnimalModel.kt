package com.halil.halilingo.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "animal_words")
data class AnimalModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "turkish_name")
    val turkishName: String? = "",
    @ColumnInfo(name = "english_name")
    val englishName: String? = "",
    @ColumnInfo(name = "is_learned")
    val isLearned: Boolean = false,
    @ColumnInfo(name = "image_url")
    val imageUrl: String = "",
): Parcelable