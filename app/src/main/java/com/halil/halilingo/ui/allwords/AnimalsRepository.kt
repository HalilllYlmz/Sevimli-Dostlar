package com.halil.halilingo.ui.allwords

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AnimalsRepository {

    private val db = FirebaseFirestore.getInstance()
    private val _animalsLiveData = MutableLiveData<List<Animal>>()

    val animalsLiveData: LiveData<List<Animal>> get() = _animalsLiveData

    fun fetchAnimals() {
        db.collection("animals")
            .get()
            .addOnSuccessListener { result ->
                val animalsList = mutableListOf<Animal>()
                for (document in result) {
                    val turkish = document.getString("turkish") ?: ""
                    val english = document.getString("english") ?: ""
                    val imageUrl = document.getString("imageUrl") ?: ""
                    animalsList.add(Animal(turkish, english, imageUrl))
                }
                _animalsLiveData.postValue(animalsList)
            }
            .addOnFailureListener { exception ->
                _animalsLiveData.postValue(emptyList())
                exception.printStackTrace()
            }
    }

    fun uploadImageUrlsToFireStore() {

        val storage = FirebaseStorage.getInstance()

        val storageRef = storage.reference.child("Animals")

        storageRef.listAll()
            .addOnSuccessListener { listResult ->
                for(fileRef in listResult.items) {
                    fileRef.downloadUrl
                        .addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            val fileName = fileRef.name

                            val animalData = mapOf(
                                "turkish" to "",
                                "english" to fileName.substringBefore("."),
                                "imageUrl" to imageUrl
                            )

                            db.collection("animals")
                                .add(animalData)
                                .addOnSuccessListener {
                                    Log.e("Repository Animals","$fileName's image URL uploaded to Firestore")
                                }
                                .addOnFailureListener { e->
                                    Log.e("Repository Animals","Error uploading $fileName's image URL to Firestore: $e")
                                }
                        }
                        .addOnFailureListener { e->
                            println("Storage Error: $e")
                        }
                }
            }

    }

    data class Animal(
        val turkish: String,
        val english: String,
        val imageUrl: String,
    )
}