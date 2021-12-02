package com.example.khcom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.example.khcom.R
import com.example.khcom.databinding.FragmentStoreBinding

private lateinit var store_binding : FragmentStoreBinding

class StoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        store_binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        val webView = store_binding.storeView
        val url = ""
        webView.loadUrl(url)

        return store_binding.root
    }

}