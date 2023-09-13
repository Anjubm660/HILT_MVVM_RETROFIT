package com.anju.hilt_mvvm_retro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anju.hilt_mvvm_retro.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TabAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Staff"
                1 -> tab.text = "Students"
                2 -> tab.text = "Characters"
            }
        }.attach()
    }
}