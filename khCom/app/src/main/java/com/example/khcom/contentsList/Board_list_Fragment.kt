package com.example.khcom.contentsList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.khcom.R
import com.example.khcom.databinding.FragmentBoardListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var bl_binding : FragmentBoardListBinding

class Board_list_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bl_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_list_, container, false)
        val category = this.arguments?.getString("category")


        val list = ArrayList<Board_Model>()
        val rv_adapter = ContentsAdapter(this, list)

        // Read FireBase Database
        val database = Firebase.database
        val myRef = database.getReference("board")

    // 비동기 형태로 불러온다 (Database 호출이 완료되기 전에 View가 생성되어 버림)
    myRef.addValueEventListener(object : ValueEventListener {
        // dataSnapshot의 데이터 분류
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for(data in dataSnapshot.children){
                // database의 데이터를 Model의 형태로 추출
                list.add(data.getValue(Board_Model::class.java)!!)
            }
            rv_adapter.notifyDataSetChanged() // adapter 재동기화
        }
        override fun onCancelled(error: DatabaseError) {
        }
    })


    bl_binding.contentsContainer.adapter = rv_adapter
    bl_binding.contentsContainer.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)




    rv_adapter.itemclick = object : ContentsAdapter.ItemClick {
        override fun onClick(view: View, position: Int) {
            val main_board = activity as ContentsListActivity
            main_board.setBoard("view", category)
        }
    }

    return bl_binding.root
}
}