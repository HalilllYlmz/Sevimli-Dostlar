package com.halil.halilingo.ui.learned

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.halil.halilingo.R
import com.halil.halilingo.data.model.AnimalModel
import com.halil.halilingo.databinding.ItemLayoutAllWordsBinding

class AllWordsAdapter(
    private var allWordsList: List<AnimalModel>,
    private val onItemClick: (AnimalModel) -> Unit,
) : RecyclerView.Adapter<AllWordsAdapter.AllWordsViewHolder>() {

    inner class AllWordsViewHolder(private val binding: ItemLayoutAllWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: AnimalModel) {
            with(binding) {
                textView.text = animal.englishName
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

    fun updateList(newList: List<AnimalModel>) {
        val diffUtilCallback = AnimalDiffUtilCallback(allWordsList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        allWordsList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

class AnimalDiffUtilCallback(
    private val oldList: List<AnimalModel>,
    private val newList: List<AnimalModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
