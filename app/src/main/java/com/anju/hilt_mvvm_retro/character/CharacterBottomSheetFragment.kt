package com.anju.hilt_mvvm_retro.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anju.hilt_mvvm_retro.databinding.FragmentCharacterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val actor = arguments?.getString("actor")
        val dateOfBirth = arguments?.getString("dateOfBirth")
        val gender = arguments?.getString("gender")
        val alive = arguments?.getBoolean("alive")



        // Set the retrieved character details to your UI elements, e.g., TextViews
        binding.nameTextView.text = name
        binding.actorTextView.text = actor
        binding.dateOfBirthTextView.text = dateOfBirth
        binding.genderTextView.text = gender
        binding.aliveTextView.text = alive.toString()


        }
    }
