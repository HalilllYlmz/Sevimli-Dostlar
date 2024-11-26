package com.halil.halilingo.ui.allwords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.halil.halilingo.R
import com.halil.halilingo.databinding.ItemLayoutAllWordsBinding
import com.halil.halilingo.ui.allwords.AnimalsRepository.Animal

class AnimalsAdapter(
    private var allWordsList: List<Animal>,
    private val onItemClick: (Animal) -> Unit,
) : RecyclerView.Adapter<AnimalsAdapter.AllWordsViewHolder>() {

    inner class AllWordsViewHolder(private val binding: ItemLayoutAllWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            with(binding) {
                textView.text = animal.english
                animalCard.setOnClickListener {
                    onItemClick(animal)
                }
                Glide.with(binding.imageView.context)
                    .load(animal.imageUrl)
                    .placeholder(R.drawable.gorilla)
                    .error(R.drawable.gorilla)
                    .into(binding.imageView)

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

    fun updateList(newList: List<Animal>) {
        allWordsList = newList
        notifyDataSetChanged()  // Listenin güncellendiğini adaptöre bildir
    }

}