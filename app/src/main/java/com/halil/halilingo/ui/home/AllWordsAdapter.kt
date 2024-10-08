package com.halil.halilingo.ui.home

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.halil.halilingo.R
import com.halil.halilingo.data.model.WordModel
import com.halil.halilingo.databinding.ItemLayoutAllWordsBinding
import java.io.IOException
import java.util.Locale

class AllWordsAdapter(
    private var allWordsList: List<WordModel>,
    private val onItemClick: (WordModel) -> Unit,
) : RecyclerView.Adapter<AllWordsAdapter.AllWordsViewHolder>() {

    inner class AllWordsViewHolder(private val binding: ItemLayoutAllWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wordModel: WordModel) {
            with(binding) {
                textView.text = wordModel.english
                animalCard.setOnClickListener {
                    onItemClick(wordModel)
                }
                val assetManager = root.context.assets
                try {
                    val inputStream = assetManager.open("images/${wordModel.english.lowercase(Locale.getDefault())}.jpg")
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    imageView.setImageBitmap(bitmap)
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                    imageView.setImageResource(R.drawable.gorilla)
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

    fun updateList(newList: List<WordModel>) {
        allWordsList = newList
        notifyDataSetChanged()  // Listenin güncellendiğini adaptöre bildir
    }

}