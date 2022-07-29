package com.waka.dana.na.presentation.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waka.dana.na.domain.response.DataResult
import com.waka.dana.na.domain.usecase.GetListWeatherByNameUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by hvngoc on 7/29/22
 */
class MainViewModel : ViewModel(), KoinComponent {
    private val exceptionHandler = CoroutineExceptionHandler { context, error ->
    }

    private val useCase: GetListWeatherByNameUseCase by inject()

    var lastQuery: String? = null;

    private val _data = MutableLiveData<DataResult>()
    val data: LiveData<DataResult> = _data

    fun loadData(query: String?) {
        viewModelScope.launch(exceptionHandler) {
            useCase.execute(query, {
                _data.value = it
            }, {
                _data.value = it
            })
        }
    }
}