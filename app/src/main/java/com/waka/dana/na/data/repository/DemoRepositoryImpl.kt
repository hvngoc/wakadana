package com.waka.dana.na.data.repository

import com.google.gson.Gson
import com.waka.dana.na.data.services.DemoServices
import com.waka.dana.na.domain.model.DemoResult
import com.waka.dana.na.domain.repository.DemoRepository

/**
 * Created by hvngoc on 7/29/22
 */
class DemoRepositoryImpl(
    gson: Gson,
    private val demoServices: DemoServices,
) :
    BaseRepository(gson), DemoRepository {

    @Throws(Throwable::class)
    override fun getListDemo(): DemoResult {
        val raw = demoServices.getListTypes().execute()
        val data = networkTransform(raw)
        return data
    }
}