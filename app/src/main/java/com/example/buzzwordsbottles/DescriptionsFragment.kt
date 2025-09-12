package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buzzwordsbottles.adapters.DescriptionAdapter
import com.example.buzzwordsbottles.databinding.FragmentCameraBinding


class DescriptionsFragment : Fragment() {
    /**
     * Initialises binding for login fragment
     */
    private lateinit var binding: FragmentCameraBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: DescriptionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)

//        binding.
//
//        // Initialises the adapter and recycler view
//        binding.gamesRecyclerView.layoutManager = layoutManager
//        adapter = RecyclerAdapter(this)
//        binding.gamesRecyclerView.adapter = adapter
    }
}