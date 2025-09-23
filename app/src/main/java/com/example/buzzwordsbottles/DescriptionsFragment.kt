package com.example.buzzwordsbottles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buzzwordsbottles.adapters.DescriptionAdapter
import com.example.buzzwordsbottles.classes.Descriptions
import com.example.buzzwordsbottles.databinding.FragmentDescriptionsBinding
import com.example.buzzwordsbottles.interfaces.ScannedTextListener
import com.example.buzzwordsbottles.classes.SharedViewModel


class DescriptionsFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        composeView.setContent {
            DescriptionsScreen()
        }


        // Adds the scanned data to the recycler view

        sharedViewModel.scannedText.forEach {
            //adapter.addItem(it)
        }


    }

}