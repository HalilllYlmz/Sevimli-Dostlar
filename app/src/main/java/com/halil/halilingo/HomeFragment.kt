package com.halil.halilingo

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        binding.rvAllWords.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
        allWords.add(WordModel(1, "Alpaca"))
        allWords.add(WordModel(2, "Antelope"))
        allWords.add(WordModel(3, "Bat"))
        allWords.add(WordModel(4, "Buffalo"))
        allWords.add(WordModel(5, "Camel"))
        allWords.add(WordModel(6, "Cat"))
        allWords.add(WordModel(7, "Cheetah"))
        allWords.add(WordModel(8, "Chimpanzee"))
        allWords.add(WordModel(9, "Cow"))
        allWords.add(WordModel(10, "Crocodile"))
        allWords.add(WordModel(11, "Deer"))
        allWords.add(WordModel(12, "Dog"))
        allWords.add(WordModel(13, "Dolphin"))
        allWords.add(WordModel(14, "Donkey"))
        allWords.add(WordModel(15, "Eagle"))
        allWords.add(WordModel(16, "Elephant"))
        allWords.add(WordModel(17, "Flamingo"))
        allWords.add(WordModel(18, "Fox"))
        allWords.add(WordModel(19, "Giraffe"))
        allWords.add(WordModel(20, "Goat"))
        allWords.add(WordModel(21, "Gorilla"))
        allWords.add(WordModel(22, "Hedgehog"))
        allWords.add(WordModel(23, "Hippo"))
        allWords.add(WordModel(24, "Horse"))
        allWords.add(WordModel(25, "Jellyfish"))
        allWords.add(WordModel(26, "Kangaroo"))
        allWords.add(WordModel(27, "Koala"))
        allWords.add(WordModel(28, "Leopard"))
        allWords.add(WordModel(29, "Lion"))
        allWords.add(WordModel(30, "Llama"))
        allWords.add(WordModel(31, "Monkey"))
        allWords.add(WordModel(32, "Otter"))
        allWords.add(WordModel(33, "Owl"))
        allWords.add(WordModel(34, "Panda"))
        allWords.add(WordModel(35, "Parrot"))
        allWords.add(WordModel(36, "Peacock"))
        allWords.add(WordModel(37, "Penguin"))
        allWords.add(WordModel(38, "Pig"))
        allWords.add(WordModel(39, "Pigeon"))
        allWords.add(WordModel(40, "Rabbit"))
        allWords.add(WordModel(41, "Rhinoceros"))
        allWords.add(WordModel(42, "Scorpion"))
        allWords.add(WordModel(43, "Seal"))
        allWords.add(WordModel(44, "Sheep"))
        allWords.add(WordModel(45, "Snake"))
        allWords.add(WordModel(46, "Sparrow"))
        allWords.add(WordModel(47, "Squirrel"))
        allWords.add(WordModel(48, "Tiger"))
        allWords.add(WordModel(49, "Wolf"))
        allWords.add(WordModel(50, "Zebra"))

        return allWords
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }
}