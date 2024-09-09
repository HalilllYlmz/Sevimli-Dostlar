package com.halil.halilingo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.halil.halilingo.databinding.ItemLayoutAllWordsBinding

class AllWordsAdapter(
    private val allWordsList: List<WordModel>,
    private val onWordClick: (Int) -> Unit
) : RecyclerView.Adapter<AllWordsAdapter.AllWordsViewHolder>() {

    inner class AllWordsViewHolder(private val binding: ItemLayoutAllWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wordModel: WordModel) {
            with(binding) {
                textView.text = wordModel.word
                textView.setOnClickListener {
                    onWordClick(wordModel.id)
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