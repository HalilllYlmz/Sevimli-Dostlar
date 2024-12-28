package com.halil.halilingo.ui.allwords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.halil.halilingo.common.BaseFragment
import com.halil.halilingo.databinding.FragmentAllWordsBinding

class AllWordsFragment : BaseFragment<FragmentAllWordsBinding>() {

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

        adapter = AnimalsAdapter(emptyList()) { animal ->
            val action = AllWordsFragmentDirections.actionAllWordsFragmentToDetailFragment(animal)
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        animalViewModel.addMissingAnimalsToDatabase()

        animalViewModel.unlearnedAnimals.observe(viewLifecycleOwner) { animals ->
            adapter.updateList(animals)
        }

    }

}