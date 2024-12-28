package com.halil.halilingo.ui.learned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.halil.halilingo.common.BaseFragment
import com.halil.halilingo.databinding.FragmentLearnedBinding
import com.halil.halilingo.ui.allwords.AnimalsViewModel

class LearnedFragment : BaseFragment<FragmentLearnedBinding>() {

    private lateinit var adapter: AllWordsAdapter
    private val viewModel: AnimalsViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLearnedBinding {
        return FragmentLearnedBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AllWordsAdapter(emptyList()) { animal ->
            val action = LearnedFragmentDirections.actionLearnedFragmentToDetailFragment(animal)
            findNavController().navigate(action)
        }

        binding.recyclerViewLearned.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewLearned.adapter = adapter

        viewModel.learnedAnimals.observe(viewLifecycleOwner) { animals ->
            println("Learned Animals: ${animals.size}")
            adapter.updateList(animals)
//            viewModel.addMissingAnimalsToDatabase()
        }
    }
}
