package com.waka.dana.na.presentation.screen.demo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentMainBinding
import com.waka.dana.na.domain.model.DemoResult
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.presentation.base.MasterController
import com.waka.dana.na.presentation.base.MasterEpoxyBuilder
import com.waka.dana.na.util.visibleIf
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class DemoFragment : Fragment(), KoinComponent, MasterEpoxyBuilder {
    companion object {
        const val TAG = "FragmentDEMO"
        fun newInstance(): DemoFragment {
            return DemoFragment()
        }
    }

    private val controller = MasterController(this)
    private val mainViewModel: DemoViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        showContent(empty = true)
        binding.recyclerView.setController(controller)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )

        binding.buttonSave.setOnClickListener {
            val text = binding.editText.text.toString()
            mainViewModel.putNewType(text)
            binding.editText.setText("")

            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(binding.editText.windowToken, 0)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.loadData()
        mainViewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    showContent(loading = true)
                }
                is DataResult.Success<*> -> {
                    showContent(content = true)
                    controller.requestModelBuild()
                }
                is DataResult.Error -> {
                    showContent(error = true)
                    binding.error.text = it.e.message ?: getString(R.string.error_default)
                }
            }
        }
    }

    private fun showContent(
        content: Boolean = false,
        error: Boolean = false,
        empty: Boolean = false,
        loading: Boolean = false
    ) {
        binding.recyclerView.visibleIf(content)
        binding.error.visibleIf(error)
        binding.noResult.visibleIf(empty)
        binding.loading.visibleIf(loading)

    }

    override fun buildHolder(): List<EpoxyModelWithHolder<*>> {
        val data = mainViewModel.data.value as? DataResult.Success<*> ?: return emptyList()
        val list = data.data as? DemoResult ?: return emptyList()
        val items = list.types ?: return emptyList()
        return items.mapIndexed { index, type ->
            return@mapIndexed DemoEpoxyModel_()
                .id(type + "index$index")
                .text(type)
        }
    }
}