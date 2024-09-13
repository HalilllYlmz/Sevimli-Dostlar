package com.halil.halilingo.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.halil.halilingo.common.BaseFragment
import com.halil.halilingo.data.model.WordModel
import com.halil.halilingo.data.model.loadWordModelsFromJson
import com.halil.halilingo.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: AllWordsAdapter
    private var allWordsList = mutableListOf<WordModel>()
    private lateinit var sharedPref: SharedPreferences


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("LearnedWords", MODE_PRIVATE)

        allWordsList = loadWordModelsFromJson(requireContext()).toMutableList()

        adapter = AllWordsAdapter(getUnlearnedAnimals()) { animal ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(animal, 0)
            findNavController().navigate(action)
        }
        binding.rvAllWords.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvAllWords.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshList()
        }

        getUnlearnedAnimals().forEach {
            println(it.english)
        }

    }

    private fun getUnlearnedAnimals(): MutableList<WordModel> {
        val learnedAnimals = sharedPref.getStringSet("learnedWords", emptySet()) ?: emptySet()
        return allWordsList.filter { it.english !in learnedAnimals }.toMutableList()
    }

    private fun refreshList() {
        val unLearnedAnimals = getUnlearnedAnimals()
        unLearnedAnimals.shuffle()
        adapter.updateList(unLearnedAnimals)
        binding.swipeRefreshLayout.isRefreshing = false
    }

}