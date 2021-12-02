package com.example.khcom.contentsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.khcom.R
import com.example.khcom.databinding.FragmentBoardViewBinding

private lateinit var bv_binding : FragmentBoardViewBinding

class Board_view_Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bv_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_view_, container, false)



        return bv_binding.root
    }
}