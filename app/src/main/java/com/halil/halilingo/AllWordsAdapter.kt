package com.halil.halilingo

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.halil.halilingo.databinding.ItemLayoutAllWordsBinding
import java.io.IOException
import java.util.Locale

class AllWordsAdapter(
    private val allWordsList: List<WordModel>,
    private val onWordClick: (WordModel) -> Unit,
    private val onImageClicked: (String) -> Unit
) : RecyclerView.Adapter<AllWordsAdapter.AllWordsViewHolder>() {

    inner class AllWordsViewHolder(private val binding: ItemLayoutAllWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wordModel: WordModel) {
            with(binding) {
                textView.text = wordModel.english
                textView.setOnClickListener {
                    onWordClick(wordModel)
                }
                val assetManager = root.context.assets
                try {
                    val inputStream = assetManager.open("images/${wordModel.english.lowercase(Locale.getDefault())}.jpg")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    imageView.setImageBitmap(bitmap)
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                    imageView.setImageResource(R.drawable.all)
                }
                imageView.setOnClickListener {
                    onImageClicked(wordModel.english)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWordsViewHolder {
        val binding =
            ItemLayoutAllWordsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllWordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllWordsViewHolder, position: Int) {
        holder.bind(allWordsList[position])
    }

    override fun getItemCount() = allWordsList.size
}