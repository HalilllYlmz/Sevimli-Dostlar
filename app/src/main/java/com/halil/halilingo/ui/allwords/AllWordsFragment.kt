package com.halil.halilingo.ui.allwords

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.halil.halilingo.R
import com.halil.halilingo.common.BaseFragment
import com.halil.halilingo.data.model.AnimalWordModel
import com.halil.halilingo.data.repos.AnimalWordRepository
import com.halil.halilingo.data.room.AnimalWordViewModelFactory
import com.halil.halilingo.data.room.DatabaseProvider
import com.halil.halilingo.databinding.FragmentAllWordsBinding

class AllWordsFragment : BaseFragment<FragmentAllWordsBinding>() {

    private lateinit var viewModel: AllWordsViewModel
    private val animalViewModel: AnimalsViewModel by viewModels()
    private lateinit var adapter: AnimalsAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllWordsBinding {
        return FragmentAllWordsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animalViewModel.loadAnimals()

        val dao = DatabaseProvider().getDatabase(requireContext()).animalWordDao()
        val repository = AnimalWordRepository(dao)
        viewModel = AllWordsViewModel(repository)
        val factory = AnimalWordViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AllWordsViewModel::class.java)

        viewModel.learnedWords.observe(viewLifecycleOwner) { words ->
            Log.e("AllWordsFragment", "Learned Words: $words")
        }
        viewModel.unlearnedWords.observe(viewLifecycleOwner) { words ->
            Log.e("AllWordsFragment", "Learned Words: $words")
        }

        adapter = AnimalsAdapter(emptyList()) {}

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        animalViewModel.animalsLiveData.observe(viewLifecycleOwner) { animals ->

            adapter.updateList(animals)

            animals.forEach {
                Log.e("AllWordsFragment", "Animal: ${it.turkish}")
                Log.e("AllWordsFragment", "Animal: ${it.english}")
                Log.e("AllWordsFragment", "Animal: ${it.imageUrl}")
            }
        }

    }

}