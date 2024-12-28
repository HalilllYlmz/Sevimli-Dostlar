package com.halil.halilingo.ui.detail

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.halil.halilingo.R
import com.halil.halilingo.common.BaseFragment
import com.halil.halilingo.data.model.AnimalModel
import com.halil.halilingo.databinding.FragmentDetailBinding
import com.halil.halilingo.ui.allwords.AnimalsViewModel
import java.util.Locale

class DetailFragment : BaseFragment<FragmentDetailBinding>(), OnInitListener {

    private lateinit var tts: TextToSpeech
    private val animalViewModel: AnimalsViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animal = DetailFragmentArgs.fromBundle(requireArguments()).animal

        Glide.with(binding.ivAnimal.context)
            .load(animal.imageUrl)
            .placeholder(R.drawable.gorilla)
            .error(R.drawable.gorilla)
            .into(binding.ivAnimal)


        if (animal.isLearned) {
            binding.learnedSwitch.isChecked = true
            binding.txtLearned.text = getString(R.string.learned)

        } else {
            binding.learnedSwitch.isChecked = false
            binding.txtLearned.text = getString(R.string.unlearned)
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        tts = TextToSpeech(requireContext(), this)

        binding.animalText.text = animal.turkishName
        binding.animalEnglishText.text = animal.englishName

        binding.btnVolume.setOnClickListener {
            animal.englishName?.let { it1 -> speak(it1) }
        }
        binding.learnedSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.txtLearned.text = getString(R.string.learned)
                animal.turkishName?.let {
                    animalViewModel.updateByTurkishName(it, true)
                }
            } else {
                binding.txtLearned.text = getString(R.string.unlearned)
                animal.turkishName?.let {
                    animalViewModel.updateByTurkishName(it, false)
                }
            }

        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        } else {
            // TODO
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