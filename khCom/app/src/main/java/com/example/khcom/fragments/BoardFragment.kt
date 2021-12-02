package com.example.khcom.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.khcom.R
import com.example.khcom.contentsList.ContentsListActivity
import com.example.khcom.databinding.FragmentBoardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var board_binding : FragmentBoardBinding
private lateinit var auth : FirebaseAuth

class BoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        board_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)
        auth = Firebase.auth

        board_binding.boardAll.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category", "All")
            startActivity(intent)
        }
        board_binding.boardFood.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category", "Food")
            startActivity(intent)
        }
        board_binding.boardHobby.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category", "Hobby")
            startActivity(intent)
        }

        return board_binding.root
    }

}