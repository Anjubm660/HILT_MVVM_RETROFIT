package com.anju.hilt_mvvm_retro.staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anju.hilt_mvvm_retro.databinding.FragmentStaffFragmentBinding
import com.anju.hilt_mvvm_retro.staff.StaffAdapter
import com.anju.hilt_mvvm_retro.staff.StaffBottomSheetFragment
import com.anju.hilt_mvvm_retro.viewmodel.UsersDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StaffFragment : Fragment() {

    private val viewModel: UsersDetailsViewModel by viewModels()
    private lateinit var binding: FragmentStaffFragmentBinding

    @Inject
    lateinit var adapter: StaffAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStaffFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.staffList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setUsers(it)
                updateLoadMoreButtonVisibility()
            }
        }
        viewModel.fetchInitialStaff()
        adapter.setOnItemClickListener { staff ->
            // Create and show the bottom sheet fragment
            val bottomSheetFragment = StaffBottomSheetFragment()
            val bundle = Bundle()
            bundle.putString("name", staff.name)
            bundle.putString("dateOfBirth", staff.dateOfBirth)
            bundle.putString("gender", staff.gender)
            bundle.putString("actor", staff.actor)
            bundle.putBoolean("alive", staff.alive)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                // Check if the last item is visible and not already fetching data
                if (lastVisibleItemPosition == totalItemCount - 1 && !viewModel.isFetchingData) {
                    // Show the "Load More" button if there are more items to load
                    if (viewModel.hasMoreItemsToLoad()) {
                        binding.loadMoreButton.visibility = View.VISIBLE
                    } else {
                        binding.loadMoreButton.visibility = View.GONE
                    }
                }
            }
        })

        binding.loadMoreButton.setOnClickListener {
            // Load the next batch of items (e.g., next 20 items)
            viewModel.loadMoreStaff()
        }

        // Initial button visibility setup
        updateLoadMoreButtonVisibility()
    }

    private fun updateLoadMoreButtonVisibility() {
        // Determine whether to show or hide the "Load More" button
        if (viewModel.hasMoreItemsToLoad()) {
            // All items are visible, hide the button
            binding.loadMoreButton.visibility = View.GONE
        } else {
            // Some items are not visible, show the button
            binding.loadMoreButton.visibility = View.VISIBLE
        }
    }
}








