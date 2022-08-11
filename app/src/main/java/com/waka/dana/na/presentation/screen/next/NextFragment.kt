package com.waka.dana.na.presentation.screen.next

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentNextBinding

class NextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNextBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_next, container, false)
        return binding.root
    }
}