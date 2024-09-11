package com.halil.halilingo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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