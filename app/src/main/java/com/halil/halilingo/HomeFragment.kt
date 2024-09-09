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


        adapter = AllWordsAdapter(getAllWords(), onWordClick = { id ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
            findNavController().navigate(action)
        }) {
            speak(it)
        }
        binding.rvAllWords.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllWords.adapter = adapter
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        } else {
            // Handle the initialization failure
        }
    }

    fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun getAllWords(): List<WordModel> {
        val allWords = mutableListOf<WordModel>()
        allWords.add(WordModel(1, "Eagle"))
        allWords.add(WordModel(1, "Lion"))
        allWords.add(WordModel(1, "Dolphin"))
        allWords.add(WordModel(1, "Dog"))
        allWords.add(WordModel(1, "Cat"))
        return allWords
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }
}