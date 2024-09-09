package com.halil.halilingo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.halil.halilingo.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var adapter: AllWordsAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AllWordsAdapter(getAllWords()) { id ->
            Toast.makeText(requireContext(), "$id", Toast.LENGTH_SHORT).show()
        }
        binding.rvAllWords.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllWords.adapter = adapter
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

}