package com.anju.hilt_mvvm_retro.character

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anju.hilt_mvvm_retro.R
import com.anju.hilt_mvvm_retro.databinding.FragmentListItemBinding
import com.anju.hilt_mvvm_retro.model.UsersLibraryItem
import com.bumptech.glide.Glide
import javax.inject.Inject

class CharacterAdapter @Inject constructor() : RecyclerView.Adapter<CharacterAdapter.UsersViewHolder>() {

    var charactersList = mutableListOf<UsersLibraryItem>()

    private var itemClickListener: ((UsersLibraryItem) -> Unit)? = null

    fun setCharacters(users: List<UsersLibraryItem>) {
        this.charactersList = users.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (UsersLibraryItem) -> Unit) {
        this.itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = FragmentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)

    }
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = charactersList[position]
        holder.bind(user)
    }
    override fun getItemCount(): Int {
        return charactersList.size
    }

    inner class UsersViewHolder(private var binding: FragmentListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType")
        fun bind(user: UsersLibraryItem) {
            Glide.with(itemView)
                .load(user.image)
                .placeholder(R.drawable.ic_person)
                .into(binding.actorImage)

            binding.actorName.text = user.name
            binding.actorDateOfBirth.text = user.dateOfBirth
            binding.actorGender.text = user.gender

        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val character = charactersList[position]
                    itemClickListener?.invoke(character)
                }
            }
        }
    }
}