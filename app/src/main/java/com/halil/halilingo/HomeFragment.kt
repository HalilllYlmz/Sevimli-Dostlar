package com.halil.halilingo

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.halil.halilingo.databinding.FragmentHomeBinding
import java.util.Collections.shuffle
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnInitListener {

    private lateinit var adapter: AllWordsAdapter
    private var allWordsList = mutableListOf<WordModel>()
    private lateinit var tts: TextToSpeech
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

        tts = TextToSpeech(requireContext(), this)

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
        unLearnedAnimals.shuffle()  // shuffle işlemini yap
        adapter.updateList(unLearnedAnimals)
        binding.swipeRefreshLayout.isRefreshing = false
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        } else {
            // Handle the initialization failure
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }
}