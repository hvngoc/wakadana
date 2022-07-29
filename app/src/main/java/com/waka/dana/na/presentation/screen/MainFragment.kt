package com.waka.dana.na.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waka.dana.na.domain.response.DataResult
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class MainFragment : Fragment(), KoinComponent {

    private val mainViewModel: MainViewModel by viewModel()

    companion object {
        const val TAG = "FragmentMain"
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {

                }
                is DataResult.Error -> {


                }
            }
        }
    }
}