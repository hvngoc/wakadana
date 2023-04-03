package com.waka.dana.na.domain.usecase

import com.waka.dana.na.domain.model.DemoResult
import com.waka.dana.na.domain.repository.DemoRepository

/**
 * Created by hvngoc on 7/29/22
 */
class PutDemoUseCase(private val repository: DemoRepository) :
    BaseUseCase<String, DemoResult>() {

    override suspend fun loadData(params: String?): DemoResult? {
        val request = params ?: return null
        return repository.putNewTypes(request)
    }
}