package com.halil.halilingo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.halil.halilingo.databinding.FragmentLearnedBinding

class LearnedFragment : BaseFragment<FragmentLearnedBinding>() {

    private lateinit var sharedPreferences: SharedPreferences
    private var learnedAnimals = mutableListOf<WordModel>()
    private lateinit var adapter: AllWordsAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLearnedBinding {
        return FragmentLearnedBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SharedPreferences'ten learnedWords setini al
        sharedPreferences =
            requireContext().getSharedPreferences("LearnedWords", Context.MODE_PRIVATE)
        val learnedSet = sharedPreferences.getStringSet("learnedWords", emptySet()) ?: emptySet()

        // Öğrenilen hayvanların listesini yükle
        learnedAnimals = getLearnedAnimals(learnedSet).toMutableList()

        // RecyclerView için adapter'ı ayarla
        adapter = AllWordsAdapter(learnedAnimals){ animal ->
            val action = LearnedFragmentDirections.actionLearnedFragmentToDetailFragment(animal, 1)
            findNavController().navigate(action)
        }

        binding.recyclerViewLearned.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewLearned.adapter = adapter
    }

    // SharedPreferences'ten alınan set ile tüm hayvan modellerini eşleştir
    private fun getLearnedAnimals(learnedSet: Set<String>): List<WordModel> {
        val allWordsList = loadWordModelsFromJson(requireContext()) // Tüm kelimeler JSON'dan okunur
        return allWordsList.filter { it.english in learnedSet } // Sadece learned olanlar filtrelenir
    }
}
