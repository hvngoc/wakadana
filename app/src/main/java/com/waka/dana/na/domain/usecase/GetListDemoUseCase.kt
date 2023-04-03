package com.waka.dana.na.domain.usecase

import com.waka.dana.na.domain.model.DemoResult
import com.waka.dana.na.domain.model.WeatherList
import com.waka.dana.na.domain.repository.DemoRepository
import com.waka.dana.na.domain.repository.WeatherRepository

/**
 * Created by hvngoc on 7/29/22
 */
class GetListDemoUseCase(private val repository: DemoRepository) :
    BaseUseCase<String, DemoResult>() {

    override suspend fun loadData(params: String?): DemoResult? {
        return repository.getListDemo()
    }
}