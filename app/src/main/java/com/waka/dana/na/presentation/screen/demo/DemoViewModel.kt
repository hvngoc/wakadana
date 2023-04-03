package com.waka.dana.na.presentation.screen.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.GetListDemoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Created by hvngoc on 7/29/22
 */
class DemoViewModel(private val useCase: GetListDemoUseCase) : ViewModel(), KoinComponent {

    private val exceptionHandler = CoroutineExceptionHandler { _, error ->
        _data.value = DataResult.Error(error)
    }

    private val _data = MutableLiveData<DataResult>()
    val data: LiveData<DataResult> = _data

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            useCase.execute(null, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}