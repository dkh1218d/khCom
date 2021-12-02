package com.example.khcom.contentsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.khcom.R

class ContentsAdapter(val context: Board_list_Fragment, val items: ArrayList<Board_Model>) : RecyclerView.Adapter<ContentsAdapter.Viewholder>() {
    interface ItemClick{
        fun onClick(view : View, position : Int)
    }
    var itemclick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsAdapter.Viewholder {
        // items를 가지고 각각의 레이아웃으로 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contentsitem, parent, false)

        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContentsAdapter.Viewholder, position: Int) {
        // onClickListener 구현
        if(itemclick != null){
            holder.itemView.findViewById<LinearLayout>(R.id.rv_container).setOnClickListener{
                itemclick?.onClick(it, position)
            }
        }

        // bindItem에서 각각의 items를 Binding 한다
        holder.bindItems(items[position])
    }

    // 각각의 레이아웃에 item을 연결하는 내부 클래스
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Board_Model){
            val b_text = itemView.findViewById<TextView>(R.id.board_text)
            val b_img = itemView.findViewById<ImageView>(R.id.board_img)
            b_text.text = item.title
            // Glide를 이용해 인터넷의 image를 적용
            Glide.with(context)
                .load(item.img) // item의 imageURL을
                .into(b_img)    // imageView에 집어넣는다
        }
    }
}