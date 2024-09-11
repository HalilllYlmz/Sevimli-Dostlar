package com.halil.halilingo

import android.content.Context
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.InputStreamReader

@Parcelize
data class WordModel(
    val id: Int,
    val english: String,
    val turkish: String
): Parcelable

fun loadWordModelsFromJson(context: Context): List<WordModel> {
    val gson = Gson()
    val type = object : TypeToken<List<WordModel>>() {}.type
    val inputStream = context.assets.open("animals.json")
    val reader = InputStreamReader(inputStream)
    return gson.fromJson(reader, type)
}