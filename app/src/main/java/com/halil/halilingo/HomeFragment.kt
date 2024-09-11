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

        allWordsList = loadWordModelsFromJson(requireContext()).toMutableList()

        adapter = AllWordsAdapter(allWordsList, onWordClick = { animal ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(animal)
            findNavController().navigate(action)
        }) {
            speak(it)
        }
        binding.rvAllWords.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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

    override fun onDestroyView() {
        super.onDestroyView()
        tts.stop()
        tts.shutdown()
    }
}