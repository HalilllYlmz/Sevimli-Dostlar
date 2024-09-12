package com.halil.halilingo

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.stringResource
import androidx.navigation.fragment.findNavController
import com.halil.halilingo.databinding.FragmentDetailBinding
import java.io.IOException
import java.util.Locale

class DetailFragment : BaseFragment<FragmentDetailBinding>(), OnInitListener {

    private lateinit var tts: TextToSpeech

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animal = DetailFragmentArgs.fromBundle(requireArguments()).animal
        val id = DetailFragmentArgs.fromBundle(requireArguments()).screenId

        val sharedPreferences = requireContext().getSharedPreferences("LearnedWords", Context.MODE_PRIVATE)

        val learnedAnimals = sharedPreferences.getStringSet("learnedWords", mutableSetOf())?.toMutableSet()

        if(id == 1) {
            binding.learnedSwitch.isChecked = true
            binding.txtLearned.text = getString(R.string.learned)
        }else {
            binding.learnedSwitch.isChecked = false
            binding.txtLearned.text = getString(R.string.unlearned)
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        tts = TextToSpeech(requireContext(), this)

        binding.animalText.text = animal.turkish
        binding.animalEnglishText.text = animal.english
        val assetManager = requireContext().assets
        val imageFileName = "${animal.english.lowercase(Locale.getDefault())}.jpg"

        try {
            val inputStream = assetManager.open("images/$imageFileName")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.ivAnimal.setImageBitmap(bitmap)
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            binding.ivAnimal.setImageResource(R.drawable.all)
        }
        binding.btnVolume.setOnClickListener {
            speak(animal.english)
        }
        binding.learnedSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                binding.txtLearned.text = getString(R.string.learned)
                learnedAnimals?.add(animal.english)
                sharedPreferences.edit().putStringSet("learnedWords", learnedAnimals).apply()
            }else {
                binding.txtLearned.text = getString(R.string.unlearned)
                learnedAnimals?.remove(animal.english)
                sharedPreferences.edit().putStringSet("learnedWords", learnedAnimals).apply()
            }

        }

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