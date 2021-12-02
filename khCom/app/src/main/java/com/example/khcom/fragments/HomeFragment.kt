package com.example.khcom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.khcom.R
import com.example.khcom.databinding.FragmentHomeBinding

private lateinit var home_binding : FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment Binding
        home_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        return home_binding.root
    }

}