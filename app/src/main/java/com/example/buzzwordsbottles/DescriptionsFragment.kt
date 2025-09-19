package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buzzwordsbottles.adapters.DescriptionAdapter
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.databinding.FragmentDescriptionsBinding
import com.example.buzzwordsbottles.interfaces.ScannedTextListener
import com.example.buzzwordsbottles.classes.SharedViewModel


class DescriptionsFragment : Fragment() {
    /**
     * Initialises binding for login fragment
     */
    private lateinit var binding: FragmentDescriptionsBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: DescriptionAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialises type of layout
        layoutManager = LinearLayoutManager(context)

        // Initialises the adapter and recycler view
        binding.descriptionRv.layoutManager = layoutManager
        adapter = DescriptionAdapter()
        binding.descriptionRv.adapter = adapter


        // Adds the scanned data to the recycler view

        sharedViewModel.scannedText.forEach {
            adapter.addItem(it)
        }

        adapter.addItem(Descriptions("asfbgd"))


    }

}