package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buzzwordsbottles.databinding.FragmentCameraBinding


class DescriptionsFragment : Fragment() {
    /**
     * Initialises binding for login fragment
     */
    private lateinit var binding: FragmentCameraBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }
}