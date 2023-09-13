package com.anju.hilt_mvvm_retro.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anju.hilt_mvvm_retro.character.CharacterFragment
import com.anju.hilt_mvvm_retro.staff.StaffFragment
import com.anju.hilt_mvvm_retro.student.StudentFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StaffFragment()
            1 -> StudentFragment()
            2 -> CharacterFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}