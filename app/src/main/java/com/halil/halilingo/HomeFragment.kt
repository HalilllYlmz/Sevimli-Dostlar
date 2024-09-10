package com.halil.halilingo

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.halil.halilingo.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnInitListener {

    private lateinit var adapter: AllWordsAdapter
    private var allWordsList = mutableListOf<WordModel>()
    private lateinit var tts: TextToSpeech

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tts = TextToSpeech(requireContext(), this)

        allWordsList = getAllWords().toMutableList()

        adapter = AllWordsAdapter(allWordsList, onWordClick = { id ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(action)
        }) {
            speak(it)
        }
        binding.rvAllWords.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllWords.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshList()
        }
    }

    private fun refreshList() {
        allWordsList.shuffle()
        adapter.notifyDataSetChanged()
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

    private fun getAllWords(): List<WordModel> {
        val allWords = mutableListOf<WordModel>()
        allWords.add(WordModel(1, "Eagle"))
        allWords.add(WordModel(2, "Lion"))
        allWords.add(WordModel(3, "Dolphin"))
        allWords.add(WordModel(4, "Dog"))
        allWords.add(WordModel(5, "Cat"))
        allWords.add(WordModel(6, "Elephant"))
        allWords.add(WordModel(7, "Tiger"))
        allWords.add(WordModel(8, "Giraffe"))
        allWords.add(WordModel(9, "Zebra"))
        allWords.add(WordModel(10, "Penguin"))
        allWords.add(WordModel(11, "Kangaroo"))
        allWords.add(WordModel(12, "Panda"))
        allWords.add(WordModel(13, "Horse"))
        allWords.add(WordModel(14, "Fox"))
        allWords.add(WordModel(15, "Wolf"))
        return allWords
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }
}