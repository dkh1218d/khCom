package com.example.khcom.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.khcom.R
import com.example.khcom.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var mainF_binding : FragmentMainBinding
private lateinit var auth : FirebaseAuth

val homef = HomeFragment()
val talkf = TalkFragment()
val boardf = BoardFragment()
val bookmarkf = BookmarkFragment()
val storef = StoreFragment()

val fraglist = arrayOf(
    homef, talkf, boardf, bookmarkf, storef
)

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainF_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        auth = Firebase.auth

        val mainAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fraglist.size
            }

            override fun createFragment(position: Int): Fragment {
                return fraglist[position]
            }

        }

        mainF_binding.mainPager.adapter = mainAdapter
        TabLayoutMediator(mainF_binding.mainTap, mainF_binding.mainPager){tab, position ->
        }.attach()

        val img1 = ImageView(context)
        img1.setImageResource(R.drawable.bottom_hometap)

        mainF_binding.mainTap.getTabAt(0)?.customView = img1
        mainF_binding.mainTap.getTabAt(1)?.setIcon(R.drawable.bottom_talk)
        mainF_binding.mainTap.getTabAt(2)?.setIcon(R.drawable.bottom_goodtip)
        mainF_binding.mainTap.getTabAt(3)?.setIcon(R.drawable.bottom_bookmark)
        mainF_binding.mainTap.getTabAt(4)?.setIcon(R.drawable.bottom_store)

        return mainF_binding.root
    }
}